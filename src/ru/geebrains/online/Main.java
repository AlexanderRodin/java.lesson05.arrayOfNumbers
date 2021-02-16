package ru.geebrains.online;

import java.util.concurrent.*;

public class Main {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static void main(String[] args) {
        float[] arr = new float[SIZE];

        timing(arr);
        timingTwo(arr);

    }


    public static void timing(float[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long time = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long timeTwo = System.currentTimeMillis();
        long operationTime = timeTwo - time;
        System.out.println("The operation was performed in: "+operationTime + " milliseconds");
    }

    public static void timingTwo(float[] arr) {
        float[] a1 = new float[HALF];
        float[] a2= new float[HALF];


        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long time = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, HALF-1);
        System.arraycopy(arr, HALF-1, a2, 0, HALF-1);

        ExecutorService e1 = Executors.newFixedThreadPool(2);
        e1.submit(()->{
            for (int i = 0;i<a1.length;i++) {
                a1[i] = (float) (i * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            return a1;
        });
        e1.submit(()->{
            for (int i = 0;i<a2.length;i++) {
                a2[i] = (float) (i * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            return a2;
        });
        e1.shutdown();

        System.arraycopy(a1, 0, arr, 0, HALF-1);
        System.arraycopy(a2, 0, arr, HALF-1, HALF-1);

        long timeTwo = System.currentTimeMillis();
        long operationTime = timeTwo - time;
        System.out.println("The operation was performed in: "+operationTime + " milliseconds");



    }


}