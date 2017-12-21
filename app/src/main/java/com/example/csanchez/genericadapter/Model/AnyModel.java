package com.example.csanchez.genericadapter.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csanchez on 20/12/2017.
 */

public class AnyModel {
    private String name;

    public AnyModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<AnyModel> getItens(){
        List<AnyModel> result = new ArrayList<>();
        result.add(new AnyModel("Joao"));
        result.add(new AnyModel("Maria"));
        result.add(new AnyModel("Fernando"));
        result.add(new AnyModel("Caio"));
        result.add(new AnyModel("Victor"));
        result.add(new AnyModel("Fabio"));
        result.add(new AnyModel("André"));
        result.add(new AnyModel("Charlie"));
        result.add(new AnyModel("Eduardo"));
        result.add(new AnyModel("Joel"));
        result.add(new AnyModel("Isaias"));
        result.add(new AnyModel("Vinicius"));
        result.add(new AnyModel("Thiago"));
        result.add(new AnyModel("Joana"));
        result.add(new AnyModel("Carla"));
        result.add(new AnyModel("Monalisa"));
        result.add(new AnyModel("Any"));

        return result;
    }
}
