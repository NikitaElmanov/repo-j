package ru.sber.seq.tasks.convert;

import ru.sber.seq.tasks.steps.Step;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertBasic {
    public static void convertWrite(List<Step> steps, String path){
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(steps);

            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Step> convertRead(String path){
        List<Step> steps = null;
        FileInputStream fis;
        ObjectInputStream ois;

        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            
            steps = (ArrayList<Step>) ois.readObject();

            fis.close();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return steps;
    }
}
