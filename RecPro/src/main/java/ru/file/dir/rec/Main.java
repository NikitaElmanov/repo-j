package ru.file.dir.rec;

import ru.file.dir.rec.processer.DirAndFileHandler;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        new DirAndFileHandler(
                new File("D:\\GitHub\\repository\\repo\\RecPro\\src\\main\\resources\\resFile.txt"))
                .process("D:\\GitHub\\repository\\repo\\RecPro");

        DirAndFileHandler.closeBW();
    }
}
