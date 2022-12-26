package org.example.real;

import org.example.dao.IGamePlayer;

public class RealPlayer implements RealPlayerProxy{
    private String name = "";

    private RealPlayerProxy proxy = null;

    public RealPlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String password) {

    }

    @Override
    public void killBoss() {

    }

    @Override
    public void upgrade() {

    }

    @Override
    public RealPlayerProxy getProxy() {
        this.proxy = new RealPlayerProxyImpl(this.proxy);

        return this.proxy;
    }
}
