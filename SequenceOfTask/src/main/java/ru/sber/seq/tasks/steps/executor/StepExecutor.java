package ru.sber.seq.tasks.steps.executor;
import ru.sber.seq.tasks.graphic.MainFrame;
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

        for (Step step : steps){
            if (!isFallen.get(step.getNumber()) && !isDoneList.get(step.getNumber())) {
                isFallen.put(4, true);
                isFallen.put(1, true);
                runCurrentStepAndPrevious(step, steps);
            }
        }

        new MainFrame(isFallen, steps);
    }

    private void runCurrentStepAndPrevious(Step step, List<Step> steps) {
        if (!step.getRelatedSteps().contains(-1)) {
            performStep(step, true);
        } else {
             for (int i = 0; i < step.getRelatedSteps().size(); i++) {
                if (step.getRelatedSteps().get(i) == -1) {

                    runStepsByTheirIndexes(steps, step, i);
                    break;
                }
            }
        }
    }

    private void runStepsByTheirIndexes(List<Step> steps, Step step, int i) {
        for (int j = 0; j < steps.size(); j++){
            if (steps.get(i).getNumber() == steps.get(j).getNumber()) {
                if (!isFallen.get(steps.get(i).getNumber()) && steps.get(i).getConditionTransition()) {
                    performStep(step, true);
                    break;
                } else {
                    notPerformStep(steps, j, step, true);
                    break;
                }
            }
        }
    }

    private void notPerformStep(List<Step> steps, Integer index, Step step, Boolean b) {
        System.out.println(step.getNumber() + " step can not be executed because step " + steps.get(index).getNumber() + " fell down.");
        isFallen.put(step.getNumber(), b);
    }

    private void performStep(Step step, boolean b) {
        step.doSome();

        isDoneList.put(step.getNumber(), b);
    }

    private void prepareSupportArrays(List<Step> steps) {
        for (int i = 0; i < steps.size(); i++){
            isFallen.put(steps.get(i).getNumber(), false);
            isDoneList.put(steps.get(i).getNumber(), false);
        }
    }
}
