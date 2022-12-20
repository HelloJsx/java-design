package org.example;

import org.example.dao.Director;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        for (int i = 0; i < 10; i++) {
            director.getABenzModel().run();
        }

        for (int i = 0; i < 10; i++) {
            director.getBBenzModel().run();
        }

        for (int i = 0; i < 10; i++) {
            director.getCBmwModel().run();
        }
    }
}