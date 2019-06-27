package ru.sber.seq.tasks.steps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Step implements Serializable {
    private Integer number;
    private String command;
    private Boolean checkPreviousStepRes;
    private Boolean isDone;
    private Boolean hasError;

    private List<Integer> parallelWith;
    private List<Integer> goThen;
    private List<Integer> previousSteps;

    private Step(StepBuilder builder){
        this.number = builder.number;
        this.command = builder.command;
        this.checkPreviousStepRes = builder.checkPreviousStepRes;
        this.isDone = builder.isDone;
        this.hasError = builder.hasError;

        this.parallelWith = builder.parallelWith;
        this.goThen = builder.goThen;
        this.previousSteps = builder.previousSteps;
    }

    public static class StepBuilder{
        private Integer number;
        private String command;
        private Boolean checkPreviousStepRes;
        private Boolean isDone;
        private Boolean hasError;

        private List<Integer> parallelWith;
        private List<Integer> goThen;
        private List<Integer> previousSteps;

        public StepBuilder(Integer number, String command, Boolean checkPreviousStepRes, Boolean hasError){
            this.number = number;
            this.command = command;
            this.checkPreviousStepRes = checkPreviousStepRes;
            this.hasError = hasError;
            this.isDone = false;
        }

        public StepBuilder setParallelWith(List<Integer> parallelWith) {
            this.parallelWith = parallelWith;
            return this;
        }

        public StepBuilder setGoThen(List<Integer> goThen) {
            this.goThen = goThen;
            return this;
        }

        public StepBuilder setPreviousSteps(List<Integer> previousSteps) {
            this.previousSteps = previousSteps;
            return this;
        }

        public Step build(){
            return new Step(this);
        }
    }

    public static void doSome(List<Step> steps) {

        for (Step step : steps) {

            if (!step.getIsDone()) {

                if (Objects.nonNull(step.getParallelWith())) {
                    for (Integer num : step.getParallelWith()) {
                        if (!steps.get(num).getIsDone()) {

                            System.out.println(steps.get(num).getCommand());
                            steps.get(num).setIsDone(true);
                        }
                    }
                }

                if (Objects.nonNull(step.getGoThen())) {

                    System.out.println(step.getCommand());
                    step.setIsDone(true);

                    for (Integer num : step.getGoThen()) {
                        if (!steps.get(num).getIsDone()) {

                            System.out.println(steps.get(num).getCommand());
                            steps.get(num).setIsDone(true);
                        }
                    }
                }
            }
        }
    }
}
