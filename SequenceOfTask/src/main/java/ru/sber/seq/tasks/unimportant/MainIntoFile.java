package ru.sber.seq.tasks.unimportant;

import ru.sber.seq.tasks.convert.ConvertMapper;
import ru.sber.seq.tasks.steps.impl.Step;

import java.util.ArrayList;
import java.util.List;

public class MainIntoFile {

    public static void main(String[] args) {

        List<Step> steps = new ArrayList<>();
        steps.add(new Step(1, true));
        steps.add(new Step(2, true, 3));
        steps.add(new Step(3, true, 2));
        steps.add(new Step(4, false));
        steps.add(new Step(5, false));

        ConvertMapper.convertInto(steps);

        for (Step step : steps) {
            step.doSome();
        }

    }
}
