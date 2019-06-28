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

        for (int i = 0; i < steps.size(); i++){
            hasErrorList.add(false);
            isDoneList.add(false);
        }

        for (int i = 0 ; i < steps.size(); i++) {
            Step step = steps.get(i);
            if (!isDoneList.get(i)){
                step.doSome();
                isDoneList.set(i, true) ;

                if (step.getNumber() == 0){      //for testing in our json file step 1 has var checkPreviousStepRes as true
                    hasErrorList.set(0, true);   //and then in this part of the code we set flag error for step 0
                }

                if (Objects.nonNull(step.getGoThen())) {
                    for (Integer num : step.getGoThen()) {
                        if (!isDoneList.get(num)) {
                            if (steps.get(num).getCheckPreviousStepRes()) {
                                if (!hasErrorList.get(i)) {
                                    steps.get(num).doSome();
                                    isDoneList.set(num, true);
                                } else {
                                    System.out.println(steps.get(num).getNumber() + " step can not be executed because step " + step.getNumber() + " fell down.");
                                    isDoneList.set(num, true);
                                }
                            } else {
                                steps.get(num).doSome();
                                isDoneList.set(num, true);
                            }
                        }
                    }
                }
            }
        }
    }
}
