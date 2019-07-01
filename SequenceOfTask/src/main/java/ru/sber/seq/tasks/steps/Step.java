package ru.sber.seq.tasks.steps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
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

    public void doSome() {
        System.out.println(this.getCommand());
    }
}
