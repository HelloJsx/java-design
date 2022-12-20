package org.example.dao;

import java.util.ArrayList;

public abstract class CarModel {

    //设置方法执行顺序
    private ArrayList<String> sequence = new ArrayList<>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    final public void run() {
        //循环一边，谁在前就执行谁
        for (int i = 0; i < this.sequence.size(); i++) {
            String actionName = this.sequence.get(i);
            if ("start".equalsIgnoreCase(actionName)) {
                this.start();
            } else if ("stop".equalsIgnoreCase(actionName)) {
                this.stop();
            } else if ("alarm".equalsIgnoreCase(actionName)) {
                this.alarm();
            } else if ("engine boom".equalsIgnoreCase(actionName)) {
                this.engineBoom();
            }
        }
    }

    //将传递过来的之传递到类内
    final public void setSequence(ArrayList<String> sequence) {
        this.sequence = sequence;
    }
}
