package org.example.dao;

public class GamePlayer implements IGamePlayer{

    private String name = "";

    public GamePlayer(String _name) {
        this.name = _name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println(user + this.name);
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "kill the boss");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + "level up");
    }
}
