package org.example.dao;

public class AudiCarModel extends CarModel{
    @Override
    public void start() {
        System.out.println("Audi is starting");
    }

    @Override
    public void stop() {
        System.out.println("Audi is stopping");
    }

    @Override
    public void alarm() {
        System.out.println("Audi's alarm");
    }

    @Override
    public void engineBoom() {
        System.out.println("Audi's engineBoom");
    }
}
