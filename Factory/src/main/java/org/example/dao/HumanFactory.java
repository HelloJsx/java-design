package org.example.dao;

import org.example.service.Human;

public class HumanFactory extends AbstractHumanFactory{
    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        //定义人种
        Human human = null;

        try {
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

        return (T) human;
    }
}
