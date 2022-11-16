package org.example.service.impl;

import org.example.service.Human;

public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("白色的肤色");
    }

    @Override
    public void talk() {
        System.out.println("白种人的语言");
    }
}
