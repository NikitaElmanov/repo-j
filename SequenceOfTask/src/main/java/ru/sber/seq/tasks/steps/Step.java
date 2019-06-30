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
    private Boolean checkPreviousStepRes;
    private List<Integer> goThen;

    public Step(Integer number, String command, Boolean checkPreviousStepRes){
        this.number = number;
        this.command = command;
        this.checkPreviousStepRes = checkPreviousStepRes;
    }

    public Step(Integer number, String command, Boolean checkPreviousStepRes,List<Integer> goThen){
        this.number = number;
        this.command = command;
        this.checkPreviousStepRes = checkPreviousStepRes;

        this.goThen = goThen;
    }

    public void doSome() {
        System.out.println(this.getCommand());
    }
}
