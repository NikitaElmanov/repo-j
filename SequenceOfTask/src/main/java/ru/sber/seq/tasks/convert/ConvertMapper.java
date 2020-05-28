package ru.sber.seq.tasks.convert;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import ru.sber.seq.tasks.steps.Step;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConvertMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void convertInto(List<Step> steps, String path){
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(
                            new File(path), steps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Step> convertFrom(String path){
        List<Step> steps = null;

        try {
            File file = new File(path);
            steps = mapper.readValue(file, new TypeReference<List<Step>>(){});

        } catch (IOException e) {
            e.printStackTrace();
        }

        return steps;
    }
}