package ru.liber.lab.mas.element;

import java.util.ArrayList;
import java.util.List;

public class Mask {
    private String name;
    private String type;
    private List<String> values = new ArrayList<String>();

    public Mask(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                "}\n";
    }
}
