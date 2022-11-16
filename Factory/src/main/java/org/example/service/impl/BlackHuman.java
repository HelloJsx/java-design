package org.example.service.impl;

import org.example.service.Human;

public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黑色的肤色");
    }

    @Override
    public void talk() {
        System.out.println("黑种人的语言");
    }
}
