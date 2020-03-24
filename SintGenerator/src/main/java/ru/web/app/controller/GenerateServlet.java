package ru.web.app.controller;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.web.app.logic.GenLogic;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@WebServlet("/generate")
public class GenerateServlet extends HttpServlet {
    private JSONParser parser = new JSONParser();

    @Override
    protected void doPost(final HttpServletRequest req,
                         final HttpServletResponse resp) {
        List<String> resParams = Arrays.asList(req.getParameter("resParams").trim().split(";"));
        List<String> fieldNames = null;
        List<String> fieldTypes = null;
        List<String> fieldPrecisions = null;
        List<String> fieldPK = null;
        //
        List<String> fieldNames2 = null;
        List<String> fieldTypes2 = null;
        List<String> fieldPrecisions2 = null;
        List<String> fieldPK2 = null;
        String amountRows = req.getParameter("amountRows").trim();
        String tableName = req.getParameter("tableName").trim();

        String childTableField = null,
                parentTableField = null,
                tableName2 = null;

        if (resParams.indexOf("secondTable") != -1) {

            childTableField = req.getParameter("childTableField").trim();
            parentTableField = req.getParameter("parentTableField").trim();
            tableName2 = req.getParameter("tableName2").trim();
        }

        try {
            JSONArray tmpFieldNames = (JSONArray) parser.parse(req.getParameter("fieldNames").trim());
            JSONArray tmpFieldTypes = (JSONArray) parser.parse(req.getParameter("fieldTypes").trim());
            JSONArray tmpFieldPrecisions = (JSONArray) parser.parse(req.getParameter("fieldPrecisions").trim());
            JSONArray tmpFieldPK = (JSONArray) parser.parse(req.getParameter("fieldPK").trim());

            if (resParams.indexOf("secondTable") != -1) {

                JSONArray tmpFieldNames2 = (JSONArray) parser.parse(req.getParameter("fieldNames2").trim());
                JSONArray tmpFieldTypes2 = (JSONArray) parser.parse(req.getParameter("fieldTypes2").trim());
                JSONArray tmpFieldPrecisions2 = (JSONArray) parser.parse(req.getParameter("fieldPrecisions2").trim());
                JSONArray tmpFieldPK2 = (JSONArray) parser.parse(req.getParameter("fieldPK2").trim());

                fieldNames2 = (List<String>) tmpFieldNames2.clone();
                fieldTypes2 = (List<String>) tmpFieldTypes2.clone();
                fieldPrecisions2 = (List<String>) tmpFieldPrecisions2.clone();
                fieldPK2 = (List<String>) tmpFieldPK2.clone();
            }

            fieldNames = (List<String>) tmpFieldNames.clone();
            fieldTypes = (List<String>) tmpFieldTypes.clone();
            fieldPrecisions = (List<String>) tmpFieldPrecisions.clone();
            fieldPK = (List<String>) tmpFieldPK.clone();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        GenLogic logic = new GenLogic(resParams, fieldNames, fieldTypes, fieldPrecisions, fieldPK, tableName, amountRows,
                                                 fieldNames2, fieldTypes2, fieldPrecisions2, fieldPK2, tableName2, childTableField, parentTableField);
        String resScript = logic.generateScript();

        if (resParams.indexOf("secondTable") != -1) {
            resScript += logic.generateScriptConnectTable();
        }

        System.out.println(resScript);

        //Before setting attribute in session performing deleting and invalidating old session attribute
        HttpSession session = req.getSession();
        //session.removeAttribute("resScript");
        //session.invalidate();

        session.setAttribute("resScript", resScript);

        session.setAttribute("fieldsNames", logic.getFieldNamesWithoutPK());
        session.setAttribute("autoIncPKFlag", logic.getAutoIncPKFlag());
        session.setAttribute("fieldPKName", logic.getFieldPKName());
        session.setAttribute("listsOfValues", logic.getFieldValues());
        session.setAttribute("listOfValuesPK", logic.getFieldValuesPK());
        session.setAttribute("tableName", logic.getTableName());

        if (resParams.indexOf("secondTable") != -1) {
            session.setAttribute("fieldsNames2", logic.getFieldNamesWithoutPK2());
            session.setAttribute("autoIncPKFlag2", logic.getAutoIncPKFlag2());
            session.setAttribute("fieldPKName2", logic.getFieldPKName2());
            session.setAttribute("listsOfValues2", logic.getFieldValues2());
            session.setAttribute("listOfValuesPK2", logic.getFieldValuesPK2());
            session.setAttribute("tableName2", logic.getTableName2());
            session.setAttribute("childTableFieldFK", childTableField);
        }
    }
}
