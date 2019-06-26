package ru.sber.seq.graph.steps.impl;

import ru.sber.seq.graph.steps.СonsistentSteps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
    There are four variables in this class:
        + number - the most common var which means number of our step(task),
        + checkAtTheEnd - depending on its value we can understand, it's needed to check task's result or not,
        + parallelWith - defined var indicate what step(task) run together with its,
        + wellDone - this var says to program that step was run successfully.
    Also there are two methods:
        + doSome - this method execute something for each step(task)
        + returnTrue - this method check condition out with var wellDone if say shortly
 */

public class Step implements СonsistentSteps, Serializable {
    private Integer number;
    private Boolean checkAtTheEnd;
    private Integer parallelWith;
    private Boolean wellDone;

    private static Integer num = 0;

    public Step() {
    }

    public Step(Integer number, Boolean checkAtTheEnd) {
        this.number = number;
        this.checkAtTheEnd = checkAtTheEnd;
    }

    public Step(Integer number, Boolean checkAtTheEnd, Integer parallelWith) {
        this.number = number;
        this.checkAtTheEnd = checkAtTheEnd;
        this.parallelWith = parallelWith;
    }

    public Boolean getCheckAtTheEnd() {
        return checkAtTheEnd;
    }

    public void setCheckAtTheEnd(Boolean checkAtTheEnd) {
        this.checkAtTheEnd = checkAtTheEnd;
    }

    public Boolean getWellDone() {
        return wellDone;
    }

    public void setWellDone(Boolean wellDone) {
        this.wellDone = wellDone;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getParallelWith() {
        return parallelWith;
    }

    public void setParallelWith(Integer parallelWith) {
        this.parallelWith = parallelWith;
    }

    @Override
    public Boolean doSome() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        perform();

        if (this.getNumber() == 3|| this.getNumber() == 5){
            this.setWellDone(false);                        //the var wellDone is false already for all steps but
        } else{                                             //i need to see number of steps which will be finished unsuccessfully
            this.setWellDone(true);
        }

        return this.returnTrue();
    }

    private void perform() {

        if (!Objects.isNull(this.getParallelWith())){
            if (num == 0){
                num = this.getParallelWith();
            }
            if (this.getNumber() == num){
                System.out.println("Step " + this.getNumber() + " is parallel with " + this.getParallelWith() + " and they did something.");
                num = 0;
            }
        } else {
            System.out.println("Step " + this.getNumber() + " did something.");
        }
    }

    private Boolean returnTrue(){
        if (this.getCheckAtTheEnd()){
            if (this.getWellDone()){
                return true;
            }
        } else {
            return true;
        }

        System.out.println("Step " + this.getNumber() + " have some problem");
        return false;
    }
}
