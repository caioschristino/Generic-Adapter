package com.example.csanchez.genericadapter.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csanchez on 20/12/2017.
 */

public class AnyModel extends SectionOrRow {
    private String name;

    public AnyModel(String name) {
        super(false);

        this.section = name;
        this.name = name;
    }

    public AnyModel(String name, boolean isRow) {
        super(isRow);

        this.section = name;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<AnyModel> getItens() {
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

    public static List<AnyModel> getItensWithSection() {
        List<AnyModel> result = new ArrayList<>();
        result.add(new AnyModel("Joao"));
        result.add(new AnyModel("Section 1", true));
        result.add(new AnyModel("Maria"));
        result.add(new AnyModel("Section 2", true));
        result.add(new AnyModel("Fernando"));
        result.add(new AnyModel("Section 3", true));
        result.add(new AnyModel("Caio"));
        result.add(new AnyModel("Section 4", true));
        result.add(new AnyModel("Victor"));
        result.add(new AnyModel("Section 5", true));
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
