package org.example.real;

public class RealPlayerProxyImpl implements RealPlayerProxy{

    private RealPlayerProxy realPlayerProxy = null;

    public RealPlayerProxyImpl(RealPlayerProxy realPlayerProxy) {
        this.realPlayerProxy = realPlayerProxy;
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
        return this;
    }
}
