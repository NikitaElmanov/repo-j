package ru.sber.seq.tasks;

import ru.sber.seq.tasks.convert.ConvertMapper;
import ru.sber.seq.tasks.steps.Step;
import ru.sber.seq.tasks.steps.executor.StepExecutor;

import java.util.List;

public class MainFromFile{

    public static void main(String[] args) {

        String pathToJson = MainFromFile.class.getClassLoader().getResource("file.json").getPath();
        List<Step> steps = ConvertMapper.convertFrom(pathToJson);
//        List<Step> steps = ConvertBasic.convertRead("..\\fileBasic.json");

        StepExecutor stepExecutor1 = new StepExecutor();
        stepExecutor1.execute(steps);
    }
}
