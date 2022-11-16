package org.example.service.impl;

import org.example.service.Human;

public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黄色的肤色");
    }

    @Override
    public void talk() {
        System.out.println("黄种人的语言");
    }
}
