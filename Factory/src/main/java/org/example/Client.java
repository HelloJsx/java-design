package org.example;

import org.example.dao.ConcreteCreator;
import org.example.dao.ConcreteProduct;
import org.example.dao.Creator;
import org.example.dao.Product;

public class Client {

    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();

        Product product = creator.createProduct(ConcreteProduct.class);

        //继续业务处理
    }
}
