package org.example.real;

import org.example.dao.IGamePlayer;

public interface RealPlayerProxy {

    public void login(String user,String password);

    public void killBoss();

    public void upgrade();

    RealPlayerProxy getProxy();
}
