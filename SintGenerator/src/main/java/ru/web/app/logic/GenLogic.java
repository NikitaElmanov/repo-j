package ru.web.app.logic;

import ru.web.app.logic.randomdata.Provider;

import java.util.List;

public class GenLogic {
    private String insertStr;

    List<String> resParams;
    List<String> fieldNames;
    List<String> fieldTypes;
    List<String> fieldPrecisions;
    List<String> fieldPK;
    String tableName;
    Integer amountRows;

    Integer commonLength;

    String notNullStr = "not null";
    String PKStr = "primary key auto_increment";

    public GenLogic(List<String> resParams,
                    List<String> fieldNames,
                    List<String> fieldTypes,
                    List<String> fieldPrecisions,
                    List<String> fieldPK,
                    String tableName,
                    String amountRows) {
        this.resParams = resParams;
        this.fieldNames = fieldNames;
        this.fieldTypes = fieldTypes;
        this.fieldPrecisions = fieldPrecisions;
        this.fieldPK = fieldPK;
        this.tableName = tableName;
        this.amountRows = Integer.parseInt(amountRows);

        commonLength = fieldNames.size();
    }

    public String generateScript(){

        StringBuilder stringBuilder;
        String finishedStr = "";

        //create script generation
        if (resParams.contains("create")){

            stringBuilder = new StringBuilder("create table ");

            stringBuilder.append(tableName);
            stringBuilder.append("\n(\n");

            for (int i = 0; i < commonLength; i++){

                if (fieldTypes.get(i).equalsIgnoreCase("VARCHAR")
                        || fieldTypes.get(i).equalsIgnoreCase("CHAR")
                        || fieldTypes.get(i).equalsIgnoreCase("DECIMAL")){

                    stringBuilder.append(fieldNames.get(i) + " " + fieldTypes.get(i) + "( " + fieldPrecisions.get(i) + " ) " + notNullStr + ",\n");
                } else if (fieldTypes.get(i).equalsIgnoreCase("INT")
                        || fieldTypes.get(i).equalsIgnoreCase("INT UNSIGNED")) {

                    if (fieldPK.get(i).equalsIgnoreCase("true")){
                        stringBuilder.append(fieldNames.get(i) + " " + fieldTypes.get(i) + " " + notNullStr + " " + PKStr + ",\n");
                    } else {
                        stringBuilder.append(fieldNames.get(i) + " " + fieldTypes.get(i) + " " + notNullStr + ",\n");
                    }

                } else {
                    stringBuilder.append(fieldNames.get(i) + " " + fieldTypes.get(i) + " " + notNullStr + ",\n");
                }
            }

            finishedStr = String.valueOf(stringBuilder).replaceAll(",$", "\n);");
        }

        //insert script generation
        if (resParams.contains("insert")) {

            stringBuilder = new StringBuilder("\n\ninsert into ");

            stringBuilder.append(tableName + "\n(");

            for (int i = 0; i < commonLength; i++){
                if (fieldPK.get(i).equalsIgnoreCase("false")){
                    stringBuilder.append(fieldNames.get(i) + ",");
                }
            }

            String tmpString = String.valueOf(stringBuilder).replaceAll(",$", "");
            stringBuilder.delete(0, stringBuilder.length());

            stringBuilder.append(tmpString);
            stringBuilder.append(")\nvalues\n");

            for (int i = 0; i < amountRows; i++){

                stringBuilder.append("(");

                for (int k = 0; k < commonLength; k++){

                    if (fieldPK.get(k).equalsIgnoreCase("true")){
                        continue;
                    } else {
                        if (fieldTypes.get(k).equalsIgnoreCase("INT")){

                            Integer firstNum = Integer.parseInt(fieldPrecisions.get(k).split(",")[0]);
                            Integer secondNum = Integer.parseInt(fieldPrecisions.get(k).split(",")[1]);

                            stringBuilder.append(Provider.getRandomInteger(firstNum, secondNum) + ", ");
                        } else if (fieldTypes.get(k).equalsIgnoreCase("INT UNSIGNED")){

                            Integer firstNum = Integer.parseInt(fieldPrecisions.get(k).split(",")[0]);
                            Integer secondNum = Integer.parseInt(fieldPrecisions.get(k).split(",")[1]);

                            stringBuilder.append(Provider.getRandomInteger(firstNum, secondNum) + ", ");
                        } else if (fieldTypes.get(k).equalsIgnoreCase("VARCHAR")
                            || fieldTypes.get(k).equalsIgnoreCase("CHAR")){

                            stringBuilder.append("\'" + Provider.getRandomString(Integer.parseInt(fieldPrecisions.get(k))) + "\', ");
                        } else if (fieldTypes.get(k).equalsIgnoreCase("DECIMAL")){

                            Integer commonAmount = Integer.parseInt(fieldPrecisions.get(k).split(",")[0]);
                            Integer amountAfterComma = Integer.parseInt(fieldPrecisions.get(k).split(",")[1]);
                            Integer amountOfZeros = commonAmount - amountAfterComma;

                            StringBuilder maxFigureForDouble = new StringBuilder("1");

                            for (int j = 0; j < amountOfZeros; j++){
                                maxFigureForDouble.append("0");
                            }

                            stringBuilder.append(Provider.getRandomDouble(Double.parseDouble(maxFigureForDouble.toString()), amountAfterComma) + ", ");
                        } else if (fieldTypes.get(k).equalsIgnoreCase("DATE")){

                            stringBuilder.append("\'" + Provider.getRandomDate(Integer.parseInt(fieldPrecisions.get(k).split(",")[0]),
                                                                        Integer.parseInt(fieldPrecisions.get(k).split(",")[1])) + "\', ");
                        } else if (fieldTypes.get(k).equalsIgnoreCase("BOOLEAN")){

                            stringBuilder.append(Provider.getRandomBoolean() + ", ");
                        }
                    }
                }

                String tmpIncrementalRowData = String.valueOf(stringBuilder).replaceAll(", $", "");

                stringBuilder.delete(0, stringBuilder.length());

                stringBuilder.append(tmpIncrementalRowData + "),\n");
            }

            finishedStr += String.valueOf(stringBuilder).replaceAll(",$", ";");
        }

        return finishedStr;
    }
}
