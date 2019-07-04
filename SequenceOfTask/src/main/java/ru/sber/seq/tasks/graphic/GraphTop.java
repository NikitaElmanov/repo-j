package ru.sber.seq.tasks.graphic;

import java.util.List;

public class GraphTop {

    private Integer number;
    private Boolean isPrinted;
    private Integer x;
    private Integer y;
    private List<Integer> matrix;

    public GraphTop(Integer number, Boolean isPrinted, Integer x, Integer y, List<Integer> matrix) {
        this.number = number;
        this.isPrinted = isPrinted;
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getPrinted() {
        return isPrinted;
    }

    public void setPrinted(Boolean printed) {
        isPrinted = printed;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public List<Integer> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<Integer> matrix) {
        this.matrix = matrix;
    }
}
