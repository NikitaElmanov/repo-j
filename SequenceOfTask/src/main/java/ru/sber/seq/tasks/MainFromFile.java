package ru.sber.seq.tasks;

import ru.sber.seq.tasks.convert.ConvertMapper;
import ru.sber.seq.tasks.steps.Step;

import java.util.List;

public class MainFromFile {

    public static void main(String[] args) {

        List<Step> steps = ConvertMapper.convertFrom("src\\main\\resources\\file.json");

        Step.doSome(steps);
    }
}
