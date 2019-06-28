package ru.sber.seq.tasks;

import ru.sber.seq.tasks.convert.ConvertBasic;
import ru.sber.seq.tasks.convert.ConvertMapper;
import ru.sber.seq.tasks.steps.Step;
import ru.sber.seq.tasks.steps.executor.StepExecutor;

import java.util.List;

public class MainFromFile {

    public static void main(String[] args) {

//        List<Step> steps = ConvertMapper.convertFrom("..\\file.json");
        List<Step> steps = ConvertBasic.convertRead("..\\fileBasic.json");

        StepExecutor.execute(steps);
    }
}
