package org.example.dao;

public abstract class CarModel {
    /**
     * 首先，这个模型要能发动起来，无论怎么发动，反正要是能够发动起来，那这个实现要在实现类里面了
     */
    public abstract void start();
    //刹车
    public abstract void stop();
    //喇叭
    public abstract void alarm();
    //引擎声
    public abstract void engineBoom();
    //启动
    public void run(){
        //先发动汽车
        this.start();
        //引擎开始轰鸣
        this.engineBoom();
        //摁喇叭
        this.alarm();
        //刹车
        this.stop();
    }
}
