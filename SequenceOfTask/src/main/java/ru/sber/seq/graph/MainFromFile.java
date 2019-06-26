package ru.sber.seq.graph;

import ru.sber.seq.graph.convert.ConvertMapper;
import ru.sber.seq.graph.tasks.impl.Step;

import java.util.List;

public class MainFromFile {

    public static void main(String[] args) {

        List<Step> steps = ConvertMapper.convertFrom("src\\main\\resources\\file.json");

//        for (Step step : steps){
//            System.out.println(step.getNumberOfStep() + " "
//            + step.getForward() + " "
//            + step.getParallelWith());
//        }

        for (Step step : steps){
            step.doSome();
        }
    }
}
