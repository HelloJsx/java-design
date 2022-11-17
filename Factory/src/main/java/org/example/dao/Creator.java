package org.example.dao;

public abstract class Creator {

    //创建一个制造产品的对象
    public abstract <T extends Product> T createProduct(Class<T> c);
}
