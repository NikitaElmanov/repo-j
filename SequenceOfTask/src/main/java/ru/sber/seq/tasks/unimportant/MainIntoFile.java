package ru.sber.seq.tasks.unimportant;

import ru.sber.seq.tasks.convert.ConvertBasic;
import ru.sber.seq.tasks.convert.ConvertMapper;
import ru.sber.seq.tasks.steps.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainIntoFile {

    public static void main(String[] args) {

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(0,"This is Step 0", true, Arrays.asList(0,1,1,0,0,0,0)));
        steps.add(new Step(1,"This is Step 1", true, Arrays.asList(-1,0,0,1,1,0,0)));
        steps.add(new Step(2,"This is Step 2", true, Arrays.asList(-1,0,0,0,0,1,0)));
        steps.add(new Step(3,"This is Step 3", true, Arrays.asList(0,-1,0,0,0,0,1)));
        steps.add(new Step(4,"This is Step 4", true, Arrays.asList(0,-1,0,0,0,0,1)));
        steps.add(new Step(5,"This is Step 5", true, Arrays.asList(0,0,-1,0,0,0,1)));
        steps.add(new Step(6,"This is Step 6", true, Arrays.asList(0,0,0,-1,-1,-1,0)));

//        steps.add(new Step(0,"This is Step 0", true, Arrays.asList(1, 2)));
//        steps.add(new Step(1,"This is Step 1", true, Arrays.asList(3, 4)));
//        steps.add(new Step(2,"This is Step 2", true, Arrays.asList(5)));
//        steps.add(new Step(3,"This is Step 3", true, Arrays.asList(6)));
//        steps.add(new Step(4,"This is Step 4", true, Arrays.asList(6)));
//        steps.add(new Step(5,"This is Step 5", true));
//        steps.add(new Step(6,"This is Step 6", true));

        ConvertMapper.convertInto(steps, "src\\main\\resources\\file.json");
//        ConvertBasic.convertWrite(steps, "..\\file.json");
    }
}
