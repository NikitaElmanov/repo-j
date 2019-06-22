package ru.pro.test.calc;

public enum TypeOperation {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/");

    private String stringValue;

    TypeOperation(String s) {
        this.stringValue = s;
    }

    public String getStringValue(){
        return this.stringValue;
    }
}
