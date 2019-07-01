package ru.sber.seq.tasks.steps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class Step implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer number;
    private String command;
    private Boolean conditionTransition;
    private List<Integer> relatedSteps;

    public Step(Integer number, String command, Boolean conditionTransition){
        this.number = number;
        this.command = command;
        this.conditionTransition = conditionTransition;
    }

    public Step(Integer number, String command, Boolean conditionTransition,List<Integer> relatedSteps){
        this.number = number;
        this.command = command;
        this.conditionTransition = conditionTransition;

        this.relatedSteps = relatedSteps;
    }

    public Step() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Boolean getConditionTransition() {
        return conditionTransition;
    }

    public void setConditionTransition(Boolean conditionTransition) {
        this.conditionTransition = conditionTransition;
    }

    public List<Integer> getRelatedSteps() {
        return relatedSteps;
    }

    public void setRelatedSteps(List<Integer> relatedSteps) {
        this.relatedSteps = relatedSteps;
    }

    public void doSome() {
        System.out.println(this.getCommand());
    }
}
