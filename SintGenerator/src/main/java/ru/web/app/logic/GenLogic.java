package ru.web.app.logic;

import java.util.List;

public class GenLogic {
    private String insertStr;

    List<String> resParams;
    List<String> fieldNames;
    List<String> fieldTypes;
    List<String> fieldPrecisions;
    List<String> fieldPK;
    String tableName;

    public GenLogic(List<String> resParams,
                    List<String> fieldNames,
                    List<String> fieldTypes,
                    List<String> fieldPrecisions,
                    List<String> fieldPK,
                    String tableName) {
        this.resParams = resParams;
        this.fieldNames = fieldNames;
        this.fieldTypes = fieldTypes;
        this.fieldPrecisions = fieldPrecisions;
        this.fieldPK = fieldPK;
        this.tableName = tableName;
    }

    public String generateInsertScript(){

        StringBuilder insertString = new StringBuilder("insert into ");


        return null;
    }
}
