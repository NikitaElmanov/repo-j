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

    private List<Integer> parallelWith;
    private List<Integer> goThen;

    private Step(StepBuilder builder){
        this.number = builder.number;
        this.command = builder.command;

        this.parallelWith = builder.parallelWith;
        this.goThen = builder.goThen;
    }

    public static class StepBuilder{
        private Integer number;
        private String command;

        private List<Integer> parallelWith;
        private List<Integer> goThen;

        public StepBuilder(Integer number, String command){
            this.number = number;
            this.command = command;
        }

        public StepBuilder setParallelWith(List<Integer> parallelWith) {
            this.parallelWith = parallelWith;
            return this;
        }

        public StepBuilder setGoThen(List<Integer> goThen) {
            this.goThen = goThen;
            return this;
        }

        public Step build(){
            return new Step(this);
        }
    }

    public static void doSome(List<Step> steps) {

        for (Step step : steps){

            System.out.println(step.getCommand());
        }
    }
}
