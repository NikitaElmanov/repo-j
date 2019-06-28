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

//    public static class StepBuilder{
//        private Integer number;
//        private String command;
//        private Boolean checkPreviousStepRes;
//
//        private Boolean isDone;
//        private Boolean hasError;
//
//        private List<Integer> goThen;
////        private List<Integer> parallelWith;
////        private List<Integer> previousSteps;
//
//        public StepBuilder(Integer number, String command, Boolean checkPreviousStepRes, Boolean hasError){
//            this.number = number;
//            this.command = command;
//            this.checkPreviousStepRes = checkPreviousStepRes;
//
//            this.hasError = hasError;
//            this.isDone = false;
//        }
//
//        public StepBuilder setGoThen(List<Integer> goThen) {
//            this.goThen = goThen;
//            return this;
//        }
//
////        public StepBuilder setParallelWith(List<Integer> parallelWith) {
////            this.parallelWith = parallelWith;
////            return this;
////        }
//
//
////        public StepBuilder setPreviousSteps(List<Integer> previousSteps) {
////            this.previousSteps = previousSteps;
////            return this;
////        }
//
//        public Step build(){
//            return new Step(this);
//        }
//    }

    public void doSome() {
        System.out.println(this.getCommand());
    }
}
