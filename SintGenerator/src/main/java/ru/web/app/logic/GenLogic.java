package ru.web.app.logic;

import ru.web.app.logic.randomdata.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class GenLogic {
    /**
     * Default precision for receiving random string.
     */
    private static final String DEFAULT_PRECISION = "30";

    /**
     * variables contain values taking from web side.
     */
    private List<String> resParams;
    private List<String> fieldPKNames;
    private List<String> fieldTypes;
    private List<String> fieldPrecisions;
    private List<String> fieldPK;
    private String tableName;
    //
    private List<String> fieldNames2;
    private List<String> fieldTypes2;
    private List<String> fieldPrecisions2;
    private List<String> fieldPK2;
    private String tableName2;

    private String parentTableField;
    private String childTableField;

    private List<List<String>> fieldValues;
    private List<List<String>> fieldValues2;
    private List<List<String>> fieldValuesPK;
    private List<List<String>> fieldValuesPK2;
    private List<String> fieldNamesWithoutPK;
    private List<String> fieldNamesWithoutPK2;
    private String fieldPKName;
    private String fieldPKName2;
    /**
     * amount of rows to generate.
     */
    private Integer amountRows;
    /**
     * amount of come fields {@link fieldPKNames}.
     */
    private Integer commonLength;
    /**
     * amount of come fields {@link fieldPKNames2}.
     */
    private Integer commonLength2;

    /**
     * constants for building SQL script.
     */
    private static final String notNullStr = "NOT NULL";
    private static final  String PKstr = "PRIMARY KEY";
    private static final  String AIstr = "AUTO_INCREMENT";

    /**
     * variable contains generated PK from second table
     * course in a second table this PK will be FK.
     */
    private Stack<String> generatedStringFKsForSecondTable;

    /**
     * Constructor which take all needed variables for generating SQL script.
     * @param resParams
     * @param fieldPKNames
     * @param fieldTypes
     * @param fieldPrecisions
     * @param fieldPK
     * @param tableName
     * @param amountRows
     * @param fieldNames2
     * @param fieldTypes2
     * @param fieldPrecisions2
     * @param fieldPK2
     * @param tableName2
     * @param childTableField
     * @param parentTableField
     */
    public GenLogic(final List<String> resParams,
                    final List<String> fieldPKNames,
                    final List<String> fieldTypes,
                    final List<String> fieldPrecisions,
                    final List<String> fieldPK,
                    final String tableName,
                    final String amountRows,
                    final List<String> fieldNames2,
                    final List<String> fieldTypes2,
                    final List<String> fieldPrecisions2,
                    final List<String> fieldPK2,
                    final String tableName2,
                    final String childTableField,
                    final String parentTableField) {
        this.resParams = resParams;
        this.fieldPKNames = fieldPKNames;
        this.fieldTypes = fieldTypes;
        this.fieldPrecisions = fieldPrecisions;
        this.fieldPK = fieldPK;
        this.tableName = tableName;

        this.amountRows = Integer.parseInt(amountRows);

        this.fieldNames2 = fieldNames2;
        this.fieldTypes2 = fieldTypes2;
        this.fieldPrecisions2 = fieldPrecisions2;
        this.fieldPK2 = fieldPK2;
        this.tableName2 = tableName2;

        this.childTableField = childTableField;
        this.parentTableField = parentTableField;

        commonLength = fieldPKNames.size();
        if (fieldNames2 != null) {
            commonLength2 = fieldNames2.size();
        }

        fieldValues = new ArrayList<>();
        fieldValues2 = new ArrayList<>();
        fieldValuesPK = new ArrayList<>();
        fieldValuesPK2 = new ArrayList<>();
        fieldNamesWithoutPK = new ArrayList<>();
        fieldNamesWithoutPK2 = new ArrayList<>();
    }

    /**
     * generate SQL script for first table.
     * @return SQL script
     */
    public String generateScript() {
        StringBuilder stringBuilder;
        String finishedStr = "";
        //may not to be used

        Stack<String> generatedStringPKs = null;
        Stack<String> generatedIntegerPKs = null;

        if (fieldPK.indexOf("true") != -1
            && fieldTypes.get(fieldPK.indexOf("true"))
                .equalsIgnoreCase("VARCHAR")) {

            fieldPKName = fieldPKNames.get(fieldPK.indexOf("true"));

            generatedStringPKs = Provider.getRandomStringAsPK(amountRows,
                                                              fieldPrecisions.get(fieldPK.indexOf("true")));
            generatedStringFKsForSecondTable = (Stack<String>) generatedStringPKs.clone();

        } else if (fieldPK.indexOf("true") != -1
                    && (fieldTypes.get(fieldPK.indexOf("true"))
                                        .equalsIgnoreCase("INT")
                    || fieldTypes.get(fieldPK.indexOf("true"))
                                        .equalsIgnoreCase("INT UNSIGNED"))) {

            fieldPKName = fieldPKNames.get(fieldPK.indexOf("true"));

            generatedIntegerPKs = Provider.getRandomIntegerAsPK(amountRows);
            generatedStringFKsForSecondTable = (Stack<String>) generatedIntegerPKs.clone();
        }

        //region create script generation
        if (resParams.contains("create")) {

            stringBuilder = new StringBuilder("create table ");

            stringBuilder.append(tableName);
            stringBuilder.append("\n(\n");

            for (int i = 0; i < commonLength; i++) {

                if (fieldTypes.get(i).equalsIgnoreCase("DATE")) {
                    stringBuilder.append(fieldPKNames.get(i) + " " + fieldTypes.get(i) + ",\n");
                } else if (/*fieldTypes.get(i).equalsIgnoreCase("VARCHAR")
                        ||*/ fieldTypes.get(i).equalsIgnoreCase("CHAR")
                        || fieldTypes.get(i).equalsIgnoreCase("DECIMAL")) {

                    stringBuilder.append(fieldPKNames.get(i) + " " + fieldTypes.get(i) + " (" + fieldPrecisions.get(i) + ") " + notNullStr + ",\n");
                } else if (fieldTypes.get(i).equalsIgnoreCase("INT")
                        || fieldTypes.get(i).equalsIgnoreCase("INT UNSIGNED")
                        || fieldTypes.get(i).equalsIgnoreCase("VARCHAR")) {

                    if (fieldPK.get(i).equalsIgnoreCase("true")) {

                        if (resParams.indexOf("AIOne") != -1) {
                            if (fieldTypes.get(i).equalsIgnoreCase("VARCHAR")) {
                                stringBuilder.append(fieldPKNames.get(i) + " " + fieldTypes.get(i) + " (" + fieldPrecisions.get(i) + ") " + notNullStr + " " + PKstr + " " + AIstr + ",\n");
                            } else {
                                stringBuilder.append(fieldPKNames.get(i) + " " + fieldTypes.get(i) + " " + notNullStr + " " + PKstr + " " + AIstr + ",\n");
                            }
                        } else {
                            if (fieldTypes.get(i).equalsIgnoreCase("VARCHAR")) {
                                stringBuilder.append(fieldPKNames.get(i) + " " + fieldTypes.get(i) + " (" + fieldPrecisions.get(i) + ") " + notNullStr + " " + PKstr + ",\n");
                            } else {
                                stringBuilder.append(fieldPKNames.get(i) + " " + fieldTypes.get(i) + " " + notNullStr + " " + PKstr + ",\n");
                            }
                        }

                    } else if (fieldTypes.get(i).equalsIgnoreCase("VARCHAR")) {
                        stringBuilder.append(fieldPKNames.get(i) + " " + fieldTypes.get(i) + " (" + fieldPrecisions.get(i) + ") " + notNullStr + ",\n");
                    } else {
                        stringBuilder.append(fieldPKNames.get(i) + " " + fieldTypes.get(i) + " " + notNullStr + ",\n");
                    }

                } else {
                    stringBuilder.append(fieldPKNames.get(i) + " " + fieldTypes.get(i) + " " + notNullStr + ",\n");
                }
            }

            finishedStr = String.valueOf(stringBuilder).replaceAll(",$", "\n);");
        }
        //endregion

        //region insert script generation
        if (resParams.contains("insert")) {

            stringBuilder = new StringBuilder("\ninsert into ");

            stringBuilder.append(tableName + "\n(");

            for (int i = 0; i < commonLength; i++) {
                if (fieldPK.get(i).equalsIgnoreCase("false")) {
                    fieldNamesWithoutPK.add(fieldPKNames.get(i));
                }

                if (fieldPK.get(i).equalsIgnoreCase("false")
                    || (fieldPK.get(i).equalsIgnoreCase("true")
                        && resParams.indexOf("AIOne") == -1)) {

                    stringBuilder.append(fieldPKNames.get(i) + ",");
                } else {
                    //TODO some handling
                    //stringBuilder.append(fieldPKNames.get(i) + ",");
                }
            }

            String tmpString = String.valueOf(stringBuilder)
                    .replaceAll(",$", "");
            stringBuilder.delete(0, stringBuilder.length());

            stringBuilder.append(tmpString);
            stringBuilder.append(")\nvalues\n");

            String randomRes;

            for (int i = 0; i < amountRows; i++) {

                ArrayList<String> tmpValuesList = new ArrayList<>();
                ArrayList<String> tmpValuesListPK = new ArrayList<>();

                stringBuilder.append("(");

                for (int k = 0; k < commonLength; k++) {

                    if (fieldPK.get(k).equalsIgnoreCase("true")) {
                        if (fieldTypes.get(k).equalsIgnoreCase("VARCHAR")
                            && resParams.indexOf("AIOne") == -1) {

                            randomRes = generatedStringPKs.pop();
                            stringBuilder.append("\'" + randomRes + "\', ");

                            tmpValuesListPK.add(randomRes);
                        } else if ((fieldTypes.get(k).equalsIgnoreCase("INT")
                                    || fieldTypes.get(k).equalsIgnoreCase("INT UNSIGNED"))
                                    && resParams.indexOf("AIOne") == -1) {

                            randomRes = generatedIntegerPKs.pop();
                            stringBuilder.append(randomRes + ", ");

                            tmpValuesListPK.add(randomRes);
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

                            randomRes = Provider.getRandomString(
                                    Integer.parseInt(fieldPrecisions.get(k) == null
                                                             || fieldPrecisions.get(k).isEmpty()
                                                             ? DEFAULT_PRECISION : fieldPrecisions.get(k)));

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
                }

                fieldValuesPK.add((ArrayList<String>) tmpValuesListPK.clone());
                fieldValues.add((ArrayList<String>) tmpValuesList.clone());

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

    /**
     * generate SQL script for second table
     * @return SQL script
     */
    public String generateScriptConnectTable() {
        if (resParams.indexOf("secondTable") == -1) {
            return null;
        }

        StringBuilder stringBuilder;
        StringBuilder tmpStringBuilderFK = new StringBuilder();
        String finishedStr = "";

        Stack<String> generatedStringPKs2 = null, generatedIntegerPKs2 = null;

        if (fieldPK2.indexOf("true") != -1
                && fieldTypes2.get(fieldPK2.indexOf("true"))
                                    .equalsIgnoreCase("VARCHAR")) {

            fieldPKName2 = fieldNames2.get(fieldPK2.indexOf("true"));

            generatedStringPKs2 = Provider.getRandomStringAsPK(amountRows,
                                                               fieldPrecisions2.get(fieldPK.indexOf("true")));

        } else if (fieldPK2.indexOf("true") != -1
                && (fieldTypes2.get(fieldPK2.indexOf("true"))
                                            .equalsIgnoreCase("INT")
                || fieldTypes2.get(fieldPK2.indexOf("true"))
                                            .equalsIgnoreCase("INT UNSIGNED"))) {

            fieldPKName2 = fieldNames2.get(fieldPK2.indexOf("true"));

            generatedIntegerPKs2 = Provider.getRandomIntegerAsPK(amountRows);
        }

        //region prepare Precision2

        if (fieldTypes.get(fieldPK.indexOf("true"))
                .equalsIgnoreCase("VARCHAR")) {

            int PKPrecision = Integer.parseInt(fieldPrecisions.get(fieldPK.indexOf("true")));
            int FKPrecision = Integer.parseInt(fieldPrecisions2.get(fieldNames2.indexOf(childTableField)));

            if (PKPrecision > FKPrecision) {
                fieldPrecisions2.set(fieldNames2.indexOf(childTableField),
                                     String.valueOf(PKPrecision));
            }
        }

        //endregion

        //region create script generation
        if (resParams.contains("create2")) {

            stringBuilder = new StringBuilder("\n\ncreate table ");

            stringBuilder.append(tableName2);
            stringBuilder.append("\n(\n");

            for (int i = 0; i < commonLength2; i++) {

                if (fieldTypes2.get(i).equalsIgnoreCase("DATE")) {
                    stringBuilder.append(fieldNames2.get(i) + " " + fieldTypes2.get(i) + ",\n");
                } else if (/*fieldTypes.get(i).equalsIgnoreCase("VARCHAR")
                        ||*/ fieldTypes2.get(i).equalsIgnoreCase("CHAR")
                        || fieldTypes2.get(i).equalsIgnoreCase("DECIMAL")) {

                    stringBuilder.append(fieldNames2.get(i) + " " + fieldTypes2.get(i) + " (" + fieldPrecisions2.get(i) + ") " + notNullStr + ",\n");
                } else if (fieldTypes2.get(i).equalsIgnoreCase("INT")
                        || fieldTypes2.get(i).equalsIgnoreCase("INT UNSIGNED")
                        || fieldTypes2.get(i).equalsIgnoreCase("VARCHAR")) {


                    if (fieldPK2.get(i).equalsIgnoreCase("true")) {

                        if (resParams.indexOf("AITwo") != -1) {
                            if (fieldTypes2.get(i).equalsIgnoreCase("VARCHAR")) {
                                stringBuilder.append(fieldNames2.get(i)
                                                     + " " + fieldTypes2.get(i)
                                                     + " (" + fieldPrecisions2.get(i) + ") "
                                                     + notNullStr + " " + PKstr + " " + AIstr + ",\n");
                            } else {
                                stringBuilder.append(fieldNames2.get(i) + " "
                                                     + fieldTypes2.get(i) + " "
                                                     + notNullStr + " " + PKstr + " " + AIstr + ",\n");
                            }
                        } else {
                            if (fieldTypes2.get(i).equalsIgnoreCase("VARCHAR")) {
                                stringBuilder.append(fieldNames2.get(i) + " "
                                                     + fieldTypes2.get(i)
                                                     + " (" + fieldPrecisions2.get(i) + ") "
                                                     + notNullStr + " " + PKstr + ",\n");
                            } else {
                                stringBuilder.append(fieldNames2.get(i)
                                                     + " " + fieldTypes2.get(i) + " "
                                                     + notNullStr + " " + PKstr + ",\n");
                            }
                        }

                    } else if (fieldTypes2.get(i).equalsIgnoreCase("VARCHAR")) {
                        stringBuilder.append(fieldNames2.get(i)
                                             + " " + fieldTypes2.get(i)
                                             + " (" + fieldPrecisions2.get(i) + ") "
                                             + notNullStr + ",\n");
                    } else {
                        stringBuilder.append(fieldNames2.get(i) + " "
                                             + fieldTypes2.get(i) + " "
                                             + notNullStr + ",\n");
                    }
                } else {
                    stringBuilder.append(fieldNames2.get(i)
                                         + " " + fieldTypes2.get(i) + " "
                                         + notNullStr + ",\n");
                }

                if (fieldNames2.get(i).equalsIgnoreCase(childTableField)) {
                    tmpStringBuilderFK.append("FOREIGN KEY ("
                                              + childTableField + ") REFERENCES "
                                              + tableName
                                              + "(" + parentTableField + "),\n");
                }
            }

            stringBuilder.append(tmpStringBuilderFK);
            finishedStr = String.valueOf(stringBuilder).replaceAll(",$", "\n);");
        }
        //endregion

        //region insert script generation
        if (resParams.contains("insert")) {

            stringBuilder = new StringBuilder("\ninsert into ");

            stringBuilder.append(tableName2 + "\n(");

            for (int i = 0; i < commonLength2; i++) {
                if (fieldPK2.get(i).equalsIgnoreCase("false")) {
                    fieldNamesWithoutPK2.add(fieldNames2.get(i));
                }

                if (fieldPK2.get(i).equalsIgnoreCase("false")
                        || (fieldPK2.get(i).equalsIgnoreCase("true")
                        && resParams.indexOf("AITwo") == -1)) {

                    stringBuilder.append(fieldNames2.get(i) + ",");
                } else {
                    //TODO some handling
                    //stringBuilder.append(fieldPKNames.get(i) + ",");
                }
            }

            String tmpString = String.valueOf(stringBuilder)
                    .replaceAll(",$", "");
            stringBuilder.delete(0, stringBuilder.length());

            stringBuilder.append(tmpString);
            stringBuilder.append(")\nvalues\n");

            String randomRes;

            for (int i = 0; i < amountRows; i++) {

                ArrayList<String> tmpValuesList = new ArrayList<>();
                ArrayList<String> tmpValuesListPK = new ArrayList<>();

                stringBuilder.append("(");

                for (int k = 0; k < commonLength2; k++) {

                    if (fieldPK2.get(k).equalsIgnoreCase("true")) {
                        if (fieldTypes2.get(k).equalsIgnoreCase("VARCHAR")
                                && resParams.indexOf("AITwo") == -1) {

                            randomRes = generatedStringPKs2.pop();
                            stringBuilder.append("\'" + randomRes + "\', ");
                            tmpValuesListPK.add(randomRes);
                        } else if ((fieldTypes2.get(k).equalsIgnoreCase("INT")
                                || fieldTypes2.get(k).equalsIgnoreCase("INT UNSIGNED"))
                                && resParams.indexOf("AITwo") == -1) {

                            randomRes = generatedIntegerPKs2.pop();
                            stringBuilder.append(randomRes + ", ");
                            tmpValuesListPK.add(randomRes);
                        } else {
                            continue;
                        }
                    } else {
                        if (fieldTypes2.get(k).equalsIgnoreCase("INT")
                            && !fieldNames2.get(k).equalsIgnoreCase(childTableField)) {

                            Integer firstNum = Integer.parseInt(fieldPrecisions2.get(k).split(",")[0]);
                            Integer secondNum = Integer.parseInt(fieldPrecisions2.get(k).split(",")[1]);

                            randomRes = String.valueOf(Provider.getRandomInteger(firstNum, secondNum));

                            stringBuilder.append(randomRes + ", ");

                            tmpValuesList.add(randomRes);
                        } else if (fieldTypes2.get(k).equalsIgnoreCase("INT UNSIGNED")
                                   && !fieldNames2.get(k).equalsIgnoreCase(childTableField)) {

                            Integer firstNum = Integer.parseInt(fieldPrecisions2.get(k).split(",")[0]);
                            Integer secondNum = Integer.parseInt(fieldPrecisions2.get(k).split(",")[1]);

                            randomRes = String.valueOf(Provider.getRandomInteger(firstNum, secondNum));

                            stringBuilder.append(randomRes + ", ");

                            tmpValuesList.add(randomRes);
                        } else if ((fieldTypes2.get(k).equalsIgnoreCase("VARCHAR")
                                || fieldTypes2.get(k).equalsIgnoreCase("CHAR"))) {

                            randomRes = Provider.getRandomString(
                                    Integer.parseInt(fieldPrecisions2.get(k) == null
                                                             || fieldPrecisions2.get(k).isEmpty()
                                                             ? DEFAULT_PRECISION : fieldPrecisions2.get(k)));

                            if (!fieldNames2.get(k).equalsIgnoreCase(childTableField)) {
                                stringBuilder.append("\'" + randomRes + "\', ");
                                tmpValuesList.add(randomRes);
                            }

                        } else if (fieldTypes2.get(k).equalsIgnoreCase("DECIMAL")
                                   && !fieldNames2.get(k).equalsIgnoreCase(childTableField)) {

                            Integer commonAmount = Integer.parseInt(fieldPrecisions2.get(k).split(",")[0]);
                            Integer amountAfterComma = Integer.parseInt(fieldPrecisions2.get(k).split(",")[1]);
                            Integer amountOfZeros = commonAmount - amountAfterComma;

                            StringBuilder maxFigureForDouble = new StringBuilder("1");

                            for (int j = 0; j < amountOfZeros; j++) {
                                maxFigureForDouble.append("0");
                            }

                            randomRes = Provider.getRandomDouble(Double.parseDouble(maxFigureForDouble.toString()), amountAfterComma);

                            stringBuilder.append(randomRes + ", ");

                            tmpValuesList.add(randomRes);
                        } else if (fieldTypes2.get(k).equalsIgnoreCase("DATE")
                                && !fieldNames2.get(k).equalsIgnoreCase(childTableField)) {

                            randomRes = String.valueOf(
                                    Provider.getRandomDate(Integer.parseInt(fieldPrecisions2.get(k).split(",")[0]),
                                                           Integer.parseInt(fieldPrecisions2.get(k).split(",")[1])));

                            stringBuilder.append("\'" + randomRes + "\', ");

                            tmpValuesList.add(randomRes);
                        } else if (fieldTypes2.get(k).equalsIgnoreCase("BOOLEAN")
                                   && !fieldNames2.get(k).equalsIgnoreCase(childTableField)) {

                            randomRes = String.valueOf(Provider.getRandomBoolean());

                            stringBuilder.append(randomRes + ", ");

                            tmpValuesList.add(randomRes);
                        }

                        if (fieldNames2.get(k).equalsIgnoreCase(childTableField)) {

                            if (fieldTypes2.get(k).equalsIgnoreCase("VARCHAR")) {

                                randomRes = generatedStringFKsForSecondTable.pop();
                                stringBuilder.append("\'" + randomRes + "\', ");
                                tmpValuesList.add(randomRes);
                            } else if (fieldTypes2.get(k).equalsIgnoreCase("INT")
                                       || fieldTypes2.get(k).equalsIgnoreCase("INT UNSIGNED")) {

                                randomRes = generatedStringFKsForSecondTable.pop();
                                stringBuilder.append(randomRes + ", ");
                                tmpValuesList.add(randomRes);
                            }
                        }
                    }
                }

                fieldValuesPK2.add((ArrayList<String>) tmpValuesListPK.clone());
                fieldValues2.add((ArrayList<String>) tmpValuesList.clone());

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

    /**
     * @return all fields without PK field for first table.
     */
    public List<String> getFieldNamesWithoutPK() {
        return fieldNamesWithoutPK;
    }

    /**
     * @return all fields without PK field for second table.
     */
    public List<String> getFieldNamesWithoutPK2() {
        return fieldNamesWithoutPK2;
    }

    /**
     * @return first table's name.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return second table's name.
     */
    public String getTableName2() {
        return tableName2;
    }

    /**
     * getter PK flag.
     * @return flag that means first table has PK at all
     */
    public String getAutoIncPKFlag() {
        if (fieldPK.indexOf("true") == -1) {
            return String.valueOf(-1);
        } else if (fieldPK.indexOf("true") != -1 && resParams.indexOf("AIOne") == -1) {
            return String.valueOf(1);
        } else if (fieldPK.indexOf("true") != -1 && resParams.indexOf("AIOne") != -1) {
            return String.valueOf(2);
        }

        return String.valueOf(-1);
    }

    /**
     * getter PK flag.
     * @return flag that means second table has PK at all
     */
    public String getAutoIncPKFlag2() {
        if (fieldPK2.indexOf("true") == -1) {
            return String.valueOf(-1);
        } else if (fieldPK2.indexOf("true") != -1 && resParams.indexOf("AITwo") == -1) {
            return String.valueOf(1);
        } else if (fieldPK2.indexOf("true") != -1 && resParams.indexOf("AITwo") != -1) {
            return String.valueOf(2);
        }

        return String.valueOf(-1);
    }

    /**
     * @return all generated results except PK's values
     * for first table.
     */
    public List<List<String>> getFieldValues() {
        return fieldValues;
    }

    /**
     * @return all generated results except PK's values
     * for second table.
     */
    public List<List<String>> getFieldValues2() {
        return fieldValues2;
    }

    /**
     * @return PK field's name for first table.
     */
    public String getFieldPKName() {
        return fieldPKName;
    }

    /**
     * @return PK field's name for second table.
     */
    public String getFieldPKName2() {
        return fieldPKName2;
    }

    /**
     * @return PK's generated values
     * for first table.
     */
    public List<List<String>> getFieldValuesPK() {
        return fieldValuesPK;
    }

    /**
     * @return PK's generated values
     * for second table.
     */
    public List<List<String>> getFieldValuesPK2() {
        return fieldValuesPK2;
    }
}
