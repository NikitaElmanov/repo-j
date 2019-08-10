package ru.sber.seq.tasks.steps;

import java.io.Serializable;
import java.util.List;

public class Step implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer number;
    private String command;
    private Boolean conditionTransition;
    private List<Integer> parent;

    public Step(Integer number, String command, Boolean conditionTransition){
        this.number = number;
        this.command = command;
        this.conditionTransition = conditionTransition;
    }

    public Step(Integer number, String command, Boolean conditionTransition, List<Integer> parent){
        this.number = number;
        this.command = command;
        this.conditionTransition = conditionTransition;

        this.parent = parent;
    }

    public Step() {}

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

    public List<Integer> getParent() {
        return parent;
    }

    public void setParent(List<Integer> parent) {
        this.parent = parent;
    }

    public void doSome() {
        System.out.println(this.getCommand());
    }
}
