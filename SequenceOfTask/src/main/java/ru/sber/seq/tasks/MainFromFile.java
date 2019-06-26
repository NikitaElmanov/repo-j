package ru.sber.seq.tasks;

import ru.sber.seq.tasks.convert.ConvertMapper;
import ru.sber.seq.tasks.steps.impl.Step;

import java.util.List;

public class MainFromFile {

    public static void main(String[] args) {

        List<Step> steps = ConvertMapper.convertFrom("src\\main\\resources\\file.json");

        runAllStep(steps);
    }

    private static void runAllStep(List<Step> steps) {
        for (Step step : steps){
            if (!step.doSome()){
                continue;
            }
        }
    }
}
