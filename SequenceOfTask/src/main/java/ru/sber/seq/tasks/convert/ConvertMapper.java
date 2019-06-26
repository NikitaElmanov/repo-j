package ru.sber.seq.tasks.convert;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import ru.sber.seq.tasks.steps.impl.Step;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConvertMapper {

    private static final ObjectMapper mapper = new ObjectMapper();;

    public static void convertInto(List<Step> steps){
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(
                            new File("src\\main\\resources\\file.json"), steps);
        } catch (
                JsonGenerationException e) {
            e.printStackTrace();
        } catch (
                JsonMappingException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Step> convertFrom(String path){
        List<Step> steps = null;

        try {
            File file = new File(path);
            steps = mapper.readValue(file, new TypeReference<List<Step>>(){});

        } catch (
                JsonGenerationException e) {
            e.printStackTrace();
        } catch (
                JsonMappingException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return steps;
    }
}
