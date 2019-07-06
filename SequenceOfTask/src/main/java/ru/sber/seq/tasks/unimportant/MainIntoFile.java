package ru.sber.seq.tasks.unimportant;

import ru.sber.seq.tasks.convert.ConvertBasic;
import ru.sber.seq.tasks.convert.ConvertMapper;
import ru.sber.seq.tasks.steps.Step;
import sun.invoke.empty.Empty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainIntoFile {

    public static void main(String[] args) {

        List<Step> steps = new ArrayList<>();

//        steps.add(new Step(3,"This is Step 3", true));
//        steps.add(new Step(4,"This is Step 4", true, Arrays.asList(3)));
//        steps.add(new Step(5,"This is Step 5", true, Arrays.asList(3)));
//        steps.add(new Step(6,"This is Step 6", true, Arrays.asList(4)));
//        steps.add(new Step(7,"This is Step 7", true, Arrays.asList(4)));
//        steps.add(new Step(8,"This is Step 8", true, Arrays.asList(5)));
//        steps.add(new Step(9,"This is Step 9", true, Arrays.asList(6,7,8)));

//        steps.add(new Step(3,"This is Step 3", true));
//        steps.add(new Step(4,"This is Step 4", true, Arrays.asList(3)));
//        steps.add(new Step(5,"This is Step 5", true, Arrays.asList(3)));
//        steps.add(new Step(6,"This is Step 6", true, Arrays.asList(4,9)));
//        steps.add(new Step(7,"This is Step 7", true, Arrays.asList(5)));
//        steps.add(new Step(8,"This is Step 8", true, Arrays.asList(6,7)));
//        steps.add(new Step(9,"This is Step 9", true, Arrays.asList(3)));

        steps.add(new Step(3,"This is Step 3", true));
        steps.add(new Step(4,"This is Step 4", true, Arrays.asList(3)));
        steps.add(new Step(5,"This is Step 5", true, Arrays.asList(3)));
        steps.add(new Step(6,"This is Step 6", true, Arrays.asList(4)));
        steps.add(new Step(7,"This is Step 7", true, Arrays.asList(5)));
        steps.add(new Step(8,"This is Step 8", true, Arrays.asList(6)));
        steps.add(new Step(9,"This is Step 9", true, Arrays.asList(7)));
        steps.add(new Step(10,"This is Step 10", true, Arrays.asList(3,8,9)));
        steps.add(new Step(11,"This is Step 11", true, Arrays.asList(10)));

        ConvertMapper.convertInto(steps, "src\\main\\resources\\file.json");
//        ConvertBasic.convertWrite(steps, "..\\file.json");
    }
}
