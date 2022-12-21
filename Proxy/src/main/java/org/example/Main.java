package org.example;

import org.example.dao.GamePlayer;
import org.example.dao.GamePlayerProxy;
import org.example.dao.IGamePlayer;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("wc");

        IGamePlayer proxy = new GamePlayerProxy(player);

        System.out.println("start time:" + new Date());

        proxy.login("wc","123456");
        proxy.killBoss();
        proxy.upgrade();

        System.out.println("end time:" + new Date());
    }
}