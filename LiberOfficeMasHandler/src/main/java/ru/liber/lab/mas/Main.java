package ru.liber.lab.mas;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.liber.lab.mas.element.Mask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    List<String> headerMass = new ArrayList<>();

    List<String> tmpMaskName = new ArrayList<>();
    List<String> tmpMaskType = new ArrayList<>();

    List<Integer> tmpMassIndex = new ArrayList<>();

    List<Mask> masks = new ArrayList<>();

    int maskNameIndex= -1;
    int maskTypeIndex= -1;

    Workbook workbook = null;
//    ---------------------------------------------

    public static void main(String[] args) {

        new Main().getUniqueMasks();

    }

    private void getUniqueMasks() {
        try {
            workbook = new XSSFWorkbook(new BufferedInputStream(Main.class.getClassLoader().getResourceAsStream("test.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workbook.getSheet("testSheet");

        for (Row row : sheet){
            for (Cell cell : row){
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals("Mask name") && maskNameIndex == -1){
                        maskNameIndex = cell.getColumnIndex();
                    }
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals("Mask type") && maskTypeIndex == -1){
                        maskTypeIndex = cell.getColumnIndex();
                    }
                    headerMass.add(cell.getStringCellValue());
                }
            }

            if (headerMass.containsAll(Arrays.asList("Mask name","Mask type"))){
                break;
            }

            if (row.getRowNum() > 1){
                throw new RuntimeException("There is no required titles");
            }
        }

        for (Row row : sheet) {
            for (Cell cell : row) {

                if (cell.getColumnIndex() == maskNameIndex && row.getRowNum() != 0){
                    fill(cell, tmpMaskName);
                }

                if (cell.getColumnIndex() == maskTypeIndex && row.getRowNum() != 0){
                    fill(cell, tmpMaskType);
                }
            }
        }

        for (int i = 0; i < tmpMaskName.size(); i++) {
            for (int j = i; j < tmpMaskName.size(); j++) {
                if (tmpMaskName.get(i).equals(tmpMaskName.get(j))
                        && tmpMaskType.get(i).equals(tmpMaskType.get(j)) && i != j){
                    tmpMassIndex.add(j);
                }
            }
        }

        handlerOfMassIndex(tmpMassIndex);

        for (Integer index : tmpMassIndex) {
            tmpMaskName.remove(tmpMaskName.get(index));
            tmpMaskType.remove(tmpMaskType.get(index));
        }

        for (int i = 0; i < tmpMaskName.size(); i++) {
            masks.add(new Mask(tmpMaskName.get(i), tmpMaskType.get(i)));
        }

//        for (Mask mask : masks){
//            System.out.println(mask);
//        }
    }

    private static void handlerOfMassIndex(List<Integer> tmpMassIndex) {
        tmpMassIndex.sort(null);
        for (int i = 1; i < tmpMassIndex.size(); i++) {
            tmpMassIndex.set(i, tmpMassIndex.get(i)-i);
        }
    }

    private static void fill(Cell cell, List<String> tmpMaskNameOrType) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                tmpMaskNameOrType.add(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                tmpMaskNameOrType.add(String.valueOf(cell.getNumericCellValue()));
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                tmpMaskNameOrType.add(String.valueOf(cell.getBooleanCellValue()));
                break;
            default:
        }
    }
}
