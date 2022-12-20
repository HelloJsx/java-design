package org.example.dao;

public class BmwModel extends CarModel{
    @Override
    protected void start() {
        System.out.println("BMW's start");
    }

    @Override
    protected void stop() {
        System.out.println("BMW's stop");
    }

    @Override
    protected void alarm() {
        System.out.println("BMW's alarm");
    }

    @Override
    protected void engineBoom() {
        System.out.println("BMW's engineBoom");
    }
}
