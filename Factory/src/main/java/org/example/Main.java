package org.example;

import org.example.dao.AbstractHumanFactory;
import org.example.dao.HumanFactory;
import org.example.service.Human;
import org.example.service.impl.BlackHuman;
import org.example.service.impl.WhiteHuman;
import org.example.service.impl.YellowHuman;

public class Main {
    public static void main(String[] args) {
        AbstractHumanFactory humanFactory = new HumanFactory();

        System.out.println("--create WriteHuman---");
        Human writeHuman = humanFactory.createHuman(WhiteHuman.class);
        writeHuman.getColor();
        writeHuman.talk();

        System.out.println("--create BlackHuman");
        Human blackHuman = humanFactory.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();

        System.out.println("--create YellowHuman");
        Human yellowHuman = humanFactory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}