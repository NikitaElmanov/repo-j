package ru.file.dir.rec;

import ru.file.dir.rec.processer.DirAndFileHandler;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        new DirAndFileHandler(
                new File("D:\\GitHub\\repository\\repo\\RecPro\\src\\main\\resources\\resFile.txt"));

        final long startTime = System.currentTimeMillis();

        DirAndFileHandler.process("D:\\GitHub\\repository\\repo\\RecPro\\root\\");

        DirAndFileHandler.closeBW();

        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime));

    }
}
