package org.example.dao;

import org.example.service.Human;

public abstract class AbstractHumanFactory {

    //抽象工厂类
    public abstract <T extends Human> T createHuman(Class<T> c);
}
