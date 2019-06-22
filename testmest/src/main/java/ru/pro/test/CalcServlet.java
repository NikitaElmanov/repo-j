package ru.pro.test;

import ru.pro.test.calc.CalcOperations;
import ru.pro.test.calc.TypeOperation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@SuppressWarnings(value = "unchecked")
public class CalcServlet extends HttpServlet {

    private ArrayList<String> listOfOperations;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.write("<html>");
        out.write("<head>");
        out.write("<title>Calc Servlet</title>");
//        out.write("<link rel='stylesheet' href='style/ei.css'/>");
        out.write("</head>");
        out.write("<body>");

        try{

            double p1 = Double.parseDouble(request.getParameter("p1"));
            double p2 = Double.parseDouble(request.getParameter("p2"));
            String sign = String.valueOf(request.getParameter("sign"));

            HttpSession session = request.getSession();

            TypeOperation operation = TypeOperation.valueOf(sign.toUpperCase());

            double result = calcResult(operation, p1, p2);

            if (session.isNew()){
                listOfOperations = new ArrayList<String>();
            } else {
                listOfOperations = (ArrayList<String>) session.getAttribute("formula");
            }

            listOfOperations.add(p1 + " " + operation.getStringValue() + " " + p2 + " = " + result);

            session.setAttribute("formula", listOfOperations);

            out.write("<h3>Id of session : " + session.getId() + "</h3>");
            out.write("<h4>Total operations: " + listOfOperations.size() + "</h4>");

            out.write("<ul>");
            for(String oper : listOfOperations){
                out.write("<li>"+ oper +"</li>");
            }
            out.write("</ul>");

        }catch (Exception ex){
            out.write("<h2>Error of inputting</h2>");
            out.write("<h2 style='color: red;'>" + ex.getMessage() + "</h2>");
        }
        finally {
            out.close();
        }
    }

    private double calcResult(TypeOperation operation, double p1, double p2) {
        double result = 0;

        switch (operation){
            case ADD:
                result = CalcOperations.add(p1,p2);
                break;
            case DIV:
                result = CalcOperations.div(p1,p2);
                break;
            case SUB:
                result = CalcOperations.sub(p1,p2);
                break;
            case MUL:
                result = CalcOperations.mul(p1,p2);
                break;
        }

        return result;
    }


}
