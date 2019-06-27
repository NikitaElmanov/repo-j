package ru.sber.seq.tasks.unimportant;

import ru.sber.seq.tasks.convert.ConvertMapper;
import ru.sber.seq.tasks.steps.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainIntoFile {

    public static void main(String[] args) {

        List<Step> steps = new ArrayList<>();

        steps.add(
                new Step.StepBuilder(0, "This is Step 0", false, true)
                        .setGoThen(Arrays.asList(1, 2, 6))
                        .build());
        steps.add(
                new Step.StepBuilder(1, "This is Step 1", true, false)
                        .setGoThen(Arrays.asList(3))
                        .setParallelWith(Arrays.asList(2, 6))
                        .setPreviousSteps(Arrays.asList(0))
                        .build());
        steps.add(
                new Step.StepBuilder(2, "This is Step 2", false, false)
                        .setGoThen(Arrays.asList(4))
                        .setParallelWith(Arrays.asList(1, 6))
                        .setPreviousSteps(Arrays.asList(0))
                        .build());
        steps.add(
                new Step.StepBuilder(3, "This is Step 3", false, false)
                        .setGoThen(Arrays.asList(5))
                        .setParallelWith(Arrays.asList(4))
                        .setPreviousSteps(Arrays.asList(1, 6))
                        .build());
        steps.add(
                new Step.StepBuilder(4,"This is Step 4", false, false)
                        .setGoThen(Arrays.asList(5))
                        .setParallelWith(Arrays.asList(3))
                        .setPreviousSteps(Arrays.asList(2))
                        .build());
        steps.add(
                new Step.StepBuilder(5,"This is Step 5", false, false)
                        .setPreviousSteps(Arrays.asList(3, 4))
                        .build());
        steps.add(
                new Step.StepBuilder(6, "This is Step 6", false, false)
                        .setGoThen(Arrays.asList(3))
                        .setParallelWith(Arrays.asList(1, 2))
                        .setPreviousSteps(Arrays.asList(0))
                        .build());

        ConvertMapper.convertInto(steps);
    }
}
