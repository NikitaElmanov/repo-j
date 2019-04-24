package ru.file.dir.rec.processer;

import java.io.*;

public class DirAndFileHandler {

    private static BufferedWriter bw;
    private static BufferedReader br;

    public DirAndFileHandler(File resFile) {
        initReaderAndWriter(resFile);
    }

    private void initReaderAndWriter(File resFile) {
        try {
            if (!resFile.exists()){
                resFile.createNewFile();
            }
            this.bw = new BufferedWriter(new FileWriter(resFile));
            this.br = new BufferedReader(new FileReader(resFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeIntoFile(File entry){
        try {
            bw.write(entry.getAbsolutePath());
            bw.newLine();
            bw.flush();
        } catch (IOException e){
            e.fillInStackTrace();
        }
    }

    public static void process(String path) {
        try {
            File dir = new File(path);
            File[] list = dir.listFiles();

            for (File entry : list)
            {
                if (entry.isDirectory() && !entry.isHidden())
                {
                    writeIntoFile(entry);
                    process(entry.getCanonicalPath());
                    continue;
                } else if (entry.isFile() && !entry.isHidden()){
                    writeIntoFile(entry);
                }
            }
        } catch (IOException e){
            e.fillInStackTrace();
        }
    }

    public static void closeBW(){
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
