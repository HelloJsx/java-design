package org.example.dao;

public class BenzModel extends CarModel{
    @Override
    protected void start() {
        System.out.println("Benz's start");
    }

    @Override
    protected void stop() {
        System.out.println("Benz's stop");
    }

    @Override
    protected void alarm() {
        System.out.println("Benz's alarm");
    }

    @Override
    protected void engineBoom() {
        System.out.println("Benz's engineBoom");
    }
}
