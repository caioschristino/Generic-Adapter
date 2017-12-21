package com.example.csanchez.genericadapter.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csanchez on 20/12/2017.
 */

public class Menu extends SectionOrRow {
    private String name;

    public Menu(String name, boolean isRow) {
        super(isRow);

        this.section = name;
        this.name = name;
    }

    public Menu(String name){
        super(false);

        this.section = name;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<Menu> getItens(){
        List<Menu> result = new ArrayList<>();
        result.add(new Menu("Lista simples"));
        result.add(new Menu("Lista infinita"));
        result.add(new Menu("Lista com sessões"));
        result.add(new Menu("Filtrando uma lista"));

        return result;
    }
}
