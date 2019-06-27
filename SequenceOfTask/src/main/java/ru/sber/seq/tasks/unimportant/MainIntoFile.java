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
                new Step.StepBuilder(0, "This is Step 0", false)
                        .setGoThen(Arrays.asList(1, 2, 6))
                        .build());
        steps.add(
                new Step.StepBuilder(1, "This is Step 1", false)
                        .setGoThen(Arrays.asList(3))
                        .setParallelWith(Arrays.asList(2, 6))
                        .build());
        steps.add(
                new Step.StepBuilder(2, "This is Step 2", false)
                        .setGoThen(Arrays.asList(4))
                        .setParallelWith(Arrays.asList(1, 6))
                        .build());
        steps.add(
                new Step.StepBuilder(3, "This is Step 3", false)
                        .setGoThen(Arrays.asList(5))
                        .setParallelWith(Arrays.asList(4))
                        .build());
        steps.add(
                new Step.StepBuilder(4,"This is Step 4", false)
                        .setGoThen(Arrays.asList(5))
                        .setParallelWith(Arrays.asList(3))
                        .build());
        steps.add(
                new Step.StepBuilder(5,"This is Step 5", false)
                        .build());
        steps.add(
                new Step.StepBuilder(6, "This is Step 6", false)
                        .setGoThen(Arrays.asList(3))
                        .setParallelWith(Arrays.asList(1, 2))
                        .build());

        ConvertMapper.convertInto(steps);
    }
}
