package org.example.dao;

import java.util.ArrayList;

public abstract class CarBuilder {
    //建造一个模型
    public abstract void setSequence(ArrayList<String> sequence);
    //获取模型
    public abstract CarModel getCarModel();
}
