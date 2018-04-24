package com.kelfan.textfiler.depreciate;

public class Attribute {
    private String name;
    private String[] data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public Attribute(String name, String[] data) {

        this.name = name;
        this.data = data;
    }
}
