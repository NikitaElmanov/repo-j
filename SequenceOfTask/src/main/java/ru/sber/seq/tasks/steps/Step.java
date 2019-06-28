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
public class Step implements Externalizable {

    private static final long serialVersionUID = 1L;

    private Integer number;
    private String command;
    private Boolean checkPreviousStepRes;
    private List<Integer> goThen;

    private Boolean isDone;
    private Boolean hasError;

    public Step(Integer number, String command, Boolean checkPreviousStepRes, Boolean hasError){
        this.number = number;
        this.command = command;
        this.checkPreviousStepRes = checkPreviousStepRes;

        this.hasError = hasError;

        this.isDone = false;
    }

    public Step(Integer number, String command, Boolean checkPreviousStepRes, Boolean hasError, List<Integer> goThen){
        this.number = number;
        this.command = command;
        this.checkPreviousStepRes = checkPreviousStepRes;

        this.hasError = hasError;

        this.goThen = goThen;

        this.isDone = false;
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

    public static void doSome(List<Step> steps) {

        for (Step step : steps) {

            if (!step.getIsDone()) {

                if (Objects.nonNull(step.getGoThen())) {

                    System.out.println(step.getCommand());
                    step.setIsDone(true);
//
//                    if (Objects.nonNull(step.getParallelWith())) {
//                        for (Integer num : step.getParallelWith()) {
//                            if (!steps.get(num).getIsDone()) {
//
//                                System.out.println(steps.get(num).getCommand());
//                                steps.get(num).setIsDone(true);
//                            }
//                        }
//                    }

                    for (Integer num : step.getGoThen()) {
                        if (!steps.get(num).getIsDone()) {
                            if (steps.get(num).getCheckPreviousStepRes()){
                                if (!step.getHasError()) {
                                    perform(steps, num);
                                } else {
                                    System.out.println(steps.get(num).getNumber() + " step can not be executed because step " + step.getNumber() + " fell down.");
                                    steps.get(num).setIsDone(true);
                                }
                            } else {
                                perform(steps, num);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void perform(List<Step> steps, Integer num){
        System.out.println(steps.get(num).getCommand());
        steps.get(num).setIsDone(true);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(number);
        out.writeObject(command);
        out.writeBoolean(checkPreviousStepRes);
        out.writeObject(goThen);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.number = in.readInt();
        this.command = (String) in.readObject();
        this.checkPreviousStepRes = in.readBoolean();
        this.goThen = (List<Integer>) in.readObject();
    }
}
