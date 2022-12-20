package org.example.dao;

import java.util.ArrayList;

public class Director {

    private ArrayList<String> sequence = new ArrayList<>();

    private BenzBuilder benzBuilder = new BenzBuilder();

    private BmwBuilder bmwBuilder = new BmwBuilder();

     public BenzModel getABenzModel() {
         this.sequence.clear();
         this.sequence.add("start");
         this.sequence.add("stop");
         //按照顺序返回
         this.benzBuilder.setSequence(sequence);
         return (BenzModel) this.benzBuilder.getCarModel();
     }

     public BenzModel getBBenzModel() {
         this.sequence.clear();
         this.sequence.add("engine boom");
         this.sequence.add("start");
         this.sequence.add("stop");
         this.benzBuilder.setSequence(sequence);
         return (BenzModel) this.benzBuilder.getCarModel();
     }

     public BmwModel getCBmwModel() {
         this.sequence.clear();
         this.sequence.add("alarm");
         this.sequence.add("start");
         this.sequence.add("stop");
         this.bmwBuilder.setSequence(sequence);
         return (BmwModel) this.bmwBuilder.getCarModel();
     }
}
