package ru.sber.seq.tasks.steps.executor;
import ru.sber.seq.tasks.graphic.MainFrame;
import ru.sber.seq.tasks.steps.Step;

import java.util.*;

public class StepExecutor {

    private Map<Integer, Boolean> isFallen;
    private Map<Integer, Boolean> isDoneList;

    private List<List<Integer>> relatedSteps;

    public StepExecutor() {
        this.isFallen = new HashMap<>();
        this.isDoneList = new HashMap<>();
        this.relatedSteps = new ArrayList<>();
    }

    public void execute(List<Step> steps){
        checkInputGraph(steps);

        prepareSupportArrays(steps);

        for (int i = 0; i < steps.size(); i++){
            List<Integer> subRelatedSteps = relatedSteps.get(i);
            Step step = steps.get(i);

            if (!isFallen.get(step.getNumber()) && !isDoneList.get(step.getNumber())) {
//                isFallen.put(4, true);
//                isFallen.put(9, true);
                isFallen.put(7, true);

                for (int j = 0; j < subRelatedSteps.size(); j++) {
                    if (subRelatedSteps.get(j) == -1) {

                        runStepsByTheirIndexes(steps, step, j);
                        break;
                    }
                }
            } else {
                notPerformStep(steps, i, step, true);
            }
        }

        new MainFrame(isFallen, steps, relatedSteps);
    }

    private void checkInputGraph(List<Step> steps) {
        int counter = 0;
        for (Step step : steps){
            if (Objects.isNull(step.getParent())){
                counter++;
            }
        }

        if (counter == 1){
            return;
        }

        System.out.println("Error input value, Only one step (top) must not have parent.");
        System.exit(0);
    }

    private boolean checkParent(Step step) {
        boolean tempCheck = false;

        if (Objects.nonNull(step.getParent())) {
            for (int i = 0; i < step.getParent().size(); i++) {
                if (!isFallen.get(step.getParent().get(i))) {
                    tempCheck = true;
                }
            }
        }

        return tempCheck;
    }

    private void runStepsByTheirIndexes(List<Step> steps, Step step, int i) {
        if (steps.get(i).getConditionTransition() && checkParent(step)) {
            performStep(step, true);
        } else {
            notPerformStep(steps, i, step, true);
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

            fillRelatedStepsByZero(steps);
        }

        getRelatedStepsFromUser(steps);
    }

    private void fillRelatedStepsByZero(List<Step> steps) {
        List<Integer> row = new ArrayList<>();
        for (int j = 0; j < steps.size(); j++){
            row.add(j, 0);
        }
        relatedSteps.add(row);
    }

    private void getRelatedStepsFromUser(List<Step> steps) {

        for (int i = 0; i < steps.size(); i++){
            if (Objects.nonNull(steps.get(i).getParent())){

                for (int j = 0; j < steps.get(i).getParent().size(); j++){

                    relatedSteps.get(i).set(getStepIndex(steps, i, j), -1);
                    relatedSteps.get(getStepIndex(steps, i, j)).set(i, 1);
                }
            }
        }
    }

    private int getStepIndex(List<Step> steps, int i, int j) {
        Step tempStep = null;

        for (Step step : steps){
            if (step.getNumber() == steps.get(i).getParent().get(j)){
                tempStep = step;
            }
        }

        return steps.indexOf(tempStep);
    }
}
