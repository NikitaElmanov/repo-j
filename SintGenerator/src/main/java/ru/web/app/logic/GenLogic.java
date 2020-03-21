package ru.web.app.logic;

import ru.web.app.logic.randomdata.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenLogic {
    private List<String> resParams;
    private List<String> fieldNames;
    private List<String> fieldTypes;
    private List<String> fieldPrecisions;
    private List<String> fieldPK;
    private String tableName;
    /**
     * amount of rows to generate
     */
    private Integer amountRows;
    /**
     * amount of come fields {@link fieldNames}
     */
    private Integer commonLength;

    private static final String notNullStr = "not null";
    private static final  String PKStr = "primary key auto_increment";

    private List<List<String>> fieldValues;
    private List<String> fieldNamesWithoutPK;
    private String fieldPKName;

    public GenLogic(final List<String> resParams,
                    final List<String> fieldNames,
                    final List<String> fieldTypes,
                    final List<String> fieldPrecisions,
                    final List<String> fieldPK,
                    final String tableName,
                    final String amountRows) {
        this.resParams = resParams;
        this.fieldNames = fieldNames;
        this.fieldTypes = fieldTypes;
        this.fieldPrecisions = fieldPrecisions;
        this.fieldPK = fieldPK;
        this.tableName = tableName;
        this.amountRows = Integer.parseInt(amountRows);

        commonLength = fieldNames.size();

        fieldValues = new ArrayList<>();
        fieldNamesWithoutPK = new ArrayList<>();
    }

    public String generateScript() {
        StringBuilder stringBuilder;
        String finishedStr = "";
        Stack<String> ganeratedStringPKs = Provider.getRandomStringAsPK(amountRows);

        //region create script generation
        if (resParams.contains("create")) {

            stringBuilder = new StringBuilder("create table ");

            stringBuilder.append(tableName);
            stringBuilder.append("\n(\n");

            for (int i = 0; i < commonLength; i++) {

                if (/*fieldTypes.get(i).equalsIgnoreCase("VARCHAR")
                        ||*/ fieldTypes.get(i).equalsIgnoreCase("CHAR")
                        || fieldTypes.get(i).equalsIgnoreCase("DECIMAL")) {

                    stringBuilder.append(fieldNames.get(i) + " " + fieldTypes.get(i) + "( " + fieldPrecisions.get(i) + " ) " + notNullStr + ",\n");
                } else if (fieldTypes.get(i).equalsIgnoreCase("INT")
                        || fieldTypes.get(i).equalsIgnoreCase("INT UNSIGNED")
                        || fieldTypes.get(i).equalsIgnoreCase("VARCHAR")) {

                    if (fieldPK.get(i).equalsIgnoreCase("true")) {
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
        //endregion

        //region insert script generation
        if (resParams.contains("insert")) {

            stringBuilder = new StringBuilder("\n\ninsert into ");

            stringBuilder.append(tableName + "\n(");

            for (int i = 0; i < commonLength; i++) {
                if (fieldPK.get(i).equalsIgnoreCase("false")) {
                    stringBuilder.append(fieldNames.get(i) + ",");

                    fieldNamesWithoutPK.add(fieldNames.get(i));
                } else if (fieldTypes.get(i).equalsIgnoreCase("VARCHAR")) {
                    stringBuilder.append(fieldNames.get(i) + ",");

                    fieldPKName = fieldNames.get(i);
                }
            }

            String tmpString = String.valueOf(stringBuilder)
                                        .replaceAll(",$", "");
            stringBuilder.delete(0, stringBuilder.length());

            stringBuilder.append(tmpString);
            stringBuilder.append(")\nvalues\n");

            String randomRes;

            for (int i = 0; i < amountRows; i++) {

                List<String> tmpValuesList = new ArrayList<>();

                stringBuilder.append("(");

                for (int k = 0; k < commonLength; k++) {

                    if (fieldPK.get(k).equalsIgnoreCase("true")) {
                        if (fieldTypes.get(k).equalsIgnoreCase("VARCHAR")) {

                            stringBuilder.append("\'" + ganeratedStringPKs.pop() + "\', ");
                        } else {
                            continue;
                        }
                    } else {
                        if (fieldTypes.get(k).equalsIgnoreCase("INT")) {

                            Integer firstNum = Integer.parseInt(fieldPrecisions.get(k).split(",")[0]);
                            Integer secondNum = Integer.parseInt(fieldPrecisions.get(k).split(",")[1]);

                            randomRes = String.valueOf(Provider.getRandomInteger(firstNum, secondNum));

                            stringBuilder.append(randomRes + ", ");

                            tmpValuesList.add(randomRes);
                        } else if (fieldTypes.get(k).equalsIgnoreCase("INT UNSIGNED")) {

                            Integer firstNum = Integer.parseInt(fieldPrecisions.get(k).split(",")[0]);
                            Integer secondNum = Integer.parseInt(fieldPrecisions.get(k).split(",")[1]);

                            randomRes = String.valueOf(Provider.getRandomInteger(firstNum, secondNum));

                            stringBuilder.append(randomRes + ", ");

                            tmpValuesList.add(randomRes);
                        } else if (fieldTypes.get(k).equalsIgnoreCase("VARCHAR")
                            || fieldTypes.get(k).equalsIgnoreCase("CHAR")) {

                            randomRes = Provider.getRandomString(Integer.parseInt(fieldPrecisions.get(k)));

                            stringBuilder.append("\'" + randomRes + "\', ");

                            tmpValuesList.add(randomRes);
                        } else if (fieldTypes.get(k).equalsIgnoreCase("DECIMAL")) {

                            Integer commonAmount = Integer.parseInt(fieldPrecisions.get(k).split(",")[0]);
                            Integer amountAfterComma = Integer.parseInt(fieldPrecisions.get(k).split(",")[1]);
                            Integer amountOfZeros = commonAmount - amountAfterComma;

                            StringBuilder maxFigureForDouble = new StringBuilder("1");

                            for (int j = 0; j < amountOfZeros; j++) {
                                maxFigureForDouble.append("0");
                            }

                            randomRes = Provider.getRandomDouble(Double.parseDouble(maxFigureForDouble.toString()), amountAfterComma);

                            stringBuilder.append(randomRes + ", ");

                            tmpValuesList.add(randomRes);
                        } else if (fieldTypes.get(k).equalsIgnoreCase("DATE")) {

                            randomRes = String.valueOf(
                                    Provider.getRandomDate(Integer.parseInt(fieldPrecisions.get(k).split(",")[0]),
                                                               Integer.parseInt(fieldPrecisions.get(k).split(",")[1])));

                            stringBuilder.append("\'" + randomRes + "\', ");

                            tmpValuesList.add(randomRes);
                        } else if (fieldTypes.get(k).equalsIgnoreCase("BOOLEAN")) {

                            randomRes = String.valueOf(Provider.getRandomBoolean());

                            stringBuilder.append(randomRes + ", ");

                            tmpValuesList.add(randomRes);
                        }
                    }

                    fieldValues.add(tmpValuesList);
                }

                String tmpIncrementalRowData = String.valueOf(stringBuilder)
                                        .replaceAll(", $", "");

                stringBuilder.delete(0, stringBuilder.length());

                stringBuilder.append(tmpIncrementalRowData + "),\n");
            }

            finishedStr += String.valueOf(stringBuilder).replaceAll(",$", ";");
        }
        //endregion

        return finishedStr;
    }

    public List<String> getFieldNamesWithoutPK() {
        return fieldNamesWithoutPK;
    }

    public String getTableName() {
        return tableName;
    }

    public String getFieldPKSeqNumber() {
        Integer indexOfPK = fieldPK.indexOf("true");
        return String.valueOf(indexOfPK);
    }

    public List<List<String>> getFieldValues() {
        return fieldValues;
    }

    public String getFieldPKName() {
        return fieldPKName;
    }
}
