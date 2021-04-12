package main;

public class TakeSomeFromTable extends Thread {

    private String[] array;
    private String word;
    private Counter counter;

    public TakeSomeFromTable(String[] array, String word, int number, Counter counter){
        super();
        this.array = array;
        this.word = word;
        this.counter = counter;
    }

    @Override
    public void run() {
        while(counter.getValue() < 30) {
            synchronized (array) {
                replaceWithSpace();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(currentThread().getName() + " DONE ");
    }

    private void replaceWithSpace() {
        for (int i = 0; i < array.length; i++) {
            if(word.equals(array[i])){
                array[i] = "   ";
                counter.increase();
                System.out.println( currentThread().getName() + " replaced with space "+ "counter " + counter.getValue());
                printArray();
            }
        }
    }


    private void printArray(){
        System.out.println("Print started..." + Thread.currentThread().getName());
        for(String e : array){
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println("print ended... ->" + Thread.currentThread().getName() );
        System.out.println("Counter:" + counter.getValue() + "\n");
    }


}
