package main;

import java.util.Random;

public class AddToTable implements Runnable {

    private Counter counter;

    public static Random rand = new Random();
    private String[] array;

    public AddToTable(String[] array, Counter counter) {
        this.array = array;
        this.counter = counter;
    }

    @Override
    public void run() {

        while(counter.getValue() < 30) {
            synchronized (array) {
                // I. Véletlenszerűen előállít egy háromelemű,
                // egyforma nagybetűkből([A-Z])álló karakterláncot.
                char character = (char) ('A' + rand.nextInt('Z' - 'A' + 1));
                String newWord = "" + character + character + character;
                // II. Az így előállított karakterlánccal felülírja
                // a sharedTable tömb legkisebb elemét.
                array[getMinIndex()] = newWord;
                counter.increase();
                // IV. Kiirja a tombot
                printArray();
            }

            // III. Varakozik
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println( Thread.currentThread().getName() + " DONE ");
    }

    private int getMinIndex() {
        String minValue = array[0];
        int minIndex = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(minValue) < 0) {
                minValue = array[i];
                minIndex = i;
            }
        }
        return minIndex;
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