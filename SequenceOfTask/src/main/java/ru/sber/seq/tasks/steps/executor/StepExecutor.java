package ru.sber.seq.tasks.steps.executor;

import ru.sber.seq.tasks.steps.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepExecutor {

    private Map<Integer, Boolean> isFallen;
    private Map<Integer, Boolean> isDoneList;

    public StepExecutor() {
        this.isFallen = new HashMap<>();
        this.isDoneList = new HashMap<>();
    }

    public void execute(List<Step> steps){
        prepareSupportArrays(steps);

        isFallen.put(0, true);

        for (Step step : steps){
            if (!isFallen.get(step.getNumber()) && !isDoneList.get(step.getNumber())) {

                runCurrentStepAndPrevious(step, steps);

                runNextStepsOfCurrent(step, steps);
            }
        }

    }

    private void runCurrentStepAndPrevious(Step step, List<Step> steps) {
        if (!step.getRelatedSteps().contains(-1)) {
            performStep(steps, step.getNumber(), true);
        } else {
            for (int i = 0; i < step.getRelatedSteps().size(); i++) {
                if (step.getRelatedSteps().get(i) == -1) {
                    if (!isFallen.get(steps.get(i).getNumber())) {
                        performStep(steps, step.getNumber(), true);
                    } else {
                        notPerformStep(steps, step.getNumber(), steps.get(i).getNumber(), true);
                        break;
                    }
                }
            }
        }
    }

    private void killNextSteps(Step step, List<Step> steps) {
        for (int i = 0; i < step.getRelatedSteps().size(); i++) {
            if (step.getRelatedSteps().get(i) == 1) {
                if (!isFallen.get(step.getNumber())) {
                    notPerformStep(steps, i, step.getNumber(), true);
                }
            }
        }
    }

    private void runNextStepsOfCurrent(Step step, List<Step> steps) {
        List<Integer> nextStepsOfCurrent = step.getRelatedSteps();

        if (!isFallen.get(step.getNumber())) {
            for (int i = 0; i < nextStepsOfCurrent.size(); i++) {
                if (nextStepsOfCurrent.get(steps.get(i).getNumber()) == 1) {
                    if (!isDoneList.get(steps.get(i).getNumber())) {
                        performStep(steps, i, true);
                    }
                }
            }
        } else {
            killNextSteps(step, steps);
        }
    }

    private void notPerformStep(List<Step> steps, Integer index, Integer currentIndex, Boolean b) {
        System.out.println(steps.get(index).getNumber() + " step can not be executed because step " + steps.get(currentIndex).getNumber() + " fell down.");
        isFallen.put(index, b);
    }

    private void performStep(List<Step> steps, int index, boolean b) {
        steps.get(index).doSome();
        isDoneList.put(index, b);
    }

    private void prepareSupportArrays(List<Step> steps) {
        for (int i = 0; i < steps.size(); i++){
            isFallen.put(steps.get(i).getNumber(), false);
            isDoneList.put(steps.get(i).getNumber(), false);
        }
    }
}
