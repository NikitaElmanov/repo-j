package ru.sber.seq.graph.tasks.impl;

import ru.sber.seq.graph.tasks.СonsistentSteps;

import java.io.Serializable;
import java.util.Objects;

public class Step implements СonsistentSteps, Serializable {
    private Integer numberOfStep;
    private Boolean forward;
    private Integer parallelWith;

    public Step() {
    }

    public Step(Integer numberOfStep, Boolean forward, Integer parallelWith) {
        this.numberOfStep = numberOfStep;
        this.forward = forward;
        this.parallelWith = parallelWith;
    }

    public Step(Integer numberOfStep, Boolean forward) {
        this.numberOfStep = numberOfStep;
        this.forward = forward;
    }

    public Integer getNumberOfStep() {
        return numberOfStep;
    }

    public void setNumberOfStep(Integer numberOfStep) {
        this.numberOfStep = numberOfStep;
    }

    public Boolean getForward() {
        return forward;
    }

    public void setForward(Boolean forward) {
        this.forward = forward;
    }

    public Integer getParallelWith() {
        return parallelWith;
    }

    public void setParallelWith(Integer parallelWith) {
        this.parallelWith = parallelWith;
    }

    @Override
    public void doSome() {
        if (Objects.isNull(this.parallelWith)){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Step "+ this.getNumberOfStep() +" is doing something!");
        } else {
            System.out.println("some parallel step");
        }
    }
}
