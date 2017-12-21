package com.example.csanchez.genericadapter.Model;

/**
 * Created by csanchez on 21/12/2017.
 */

public class SectionOrRow {
    protected String section;
    protected boolean isRow;

    public SectionOrRow(boolean isRow){
        this.isRow = isRow;
    }

    public boolean isRow() {
        return isRow;
    }
}
