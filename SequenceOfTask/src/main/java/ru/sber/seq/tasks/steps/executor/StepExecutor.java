package ru.sber.seq.tasks.steps.executor;

import ru.sber.seq.tasks.steps.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StepExecutor {

    private static List<Boolean> hasErrorList;
    private static List<Boolean> isDoneList;

    static {
        hasErrorList = new ArrayList<>();
        isDoneList = new ArrayList<>();
    }

    public static void execute(List<Step> steps){

        prepareArrays(steps);

        for (int i = 0 ; i < steps.size(); i++) {
            Step step = steps.get(i);
            if (!isDoneList.get(i)){

                if (step.getNumber() == 0){      //for testing in our json file step 1 has var checkPreviousStepRes as true
                    hasErrorList.set(0, true);   //and then in this part of the code we set flag error for step 0
                }

                if (!isDoneList.get(step.getNumber())){

                    for (int g = 0 ; g < step.getNumber(); g++){
                        if (Objects.nonNull(steps.get(g).getGoThen())){
                            for (Integer num : steps.get(g).getGoThen()){
                                if (step.getNumber() == num){
                                    if (isDoneList.get(g)){
                                        if (step.getCheckPreviousStepRes()){
                                            if (!hasErrorList.get(g)){
                                                performStep(steps, i, true);
                                            } else {
                                                notPerfromStep(steps, num, g, true);
                                            }
                                        } else {
                                            performStep(steps, i, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (Objects.nonNull(step.getGoThen())) {

                    if (!isDoneList.get(i)){
                        performStep(steps, i, true);
                    }

                    for (Integer num : step.getGoThen()) {
                        if (!isDoneList.get(num)) {
                            if (steps.get(num).getCheckPreviousStepRes()) {
                                if (!hasErrorList.get(i)) {
                                    performStep(steps, num, true);
                                } else {
                                    notPerfromStep(steps, num, i, true);
                                }
                            } else {
                                performStep(steps, num, true);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void notPerfromStep(List<Step> steps, Integer index, Integer currentIndex, boolean b) {
        System.out.println(steps.get(index).getNumber() + " step can not be executed because step " + steps.get(currentIndex).getNumber() + " fell down.");
        isDoneList.set(index, b);
        hasErrorList.set(index, b);
    }

    private static void performStep(List<Step> steps, int index, boolean b) {
        steps.get(index).doSome();
        isDoneList.set(index, true);
    }

    private static void prepareArrays(List<Step> steps) {
        for (int i = 0; i < steps.size(); i++){
            hasErrorList.add(false);
            isDoneList.add(false);
        }
    }
}
