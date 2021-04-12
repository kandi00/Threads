package main;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] sharedTable = new String[10];
        Arrays.fill(sharedTable, " ");
        Counter counter  = new Counter();

        //System.out.println(Thread.currentThread());

        Runnable runnable = new AddToTable(sharedTable, counter);
        //Addig elements to table
        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);

        //Taking elements from table
        Thread thread2= new TakeSomeFromTable( sharedTable, "AAA", 10, counter);
        Thread thread3= new TakeSomeFromTable( sharedTable, "TTT", 10, counter);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
