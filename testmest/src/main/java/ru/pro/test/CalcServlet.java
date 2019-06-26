package ru.pro.test;

import ru.pro.test.calc.CalcOperations;
import ru.pro.test.calc.TypeOperation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings(value = "unchecked")
public class CalcServlet extends HttpServlet {

    private static final String CONTEXT_PARAM = "list";

    private ArrayList<String> listOfOperations;
    private Map<String, ArrayList<String>> users;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.write("<html>");
        out.write("<head>");
        out.write("<title>Calc Servlet</title>");
        out.write("<link rel='stylesheet' href='"+ request.getContextPath() +"/css/style.css'/>");
        out.write("</head>");
        out.write("<body>");
        out.write("<div class='container'>");

        try{

            double p1 = Double.parseDouble(request.getParameter("p1"));
            double p2 = Double.parseDouble(request.getParameter("p2"));
            String sign = String.valueOf(request.getParameter("sign"));

            HttpSession session = request.getSession();

            ServletContext context = request.getServletContext();
/////////////////////////////////
            if (users == null){
                users = new HashMap<String, ArrayList<String>>();
            }

            users.put(session.getId(), listOfOperations);

            context.setAttribute(CONTEXT_PARAM, users);

//////////////////////////////////
            TypeOperation operation = TypeOperation.valueOf(sign.toUpperCase());

            double result = calcResult(operation, p1, p2);

            if (session.isNew()){
                listOfOperations = new ArrayList<String>();
            }
            else {
                listOfOperations = (ArrayList<String>) session.getAttribute("formula");
            }

            listOfOperations.add(p1 + " " + operation.getStringValue() + " " + p2 + " = " + result);

            session.setAttribute("formula", listOfOperations);

            out.write("<div class='l-b'><h3>Id of session : " + session.getId() + "</h3>");
            out.write("<h4>Total operations: " + listOfOperations.size() + "</h4>");

            out.write("<ul>");
            for(String oper : listOfOperations){
                out.write("<li>"+ oper +"</li>");
            }
            out.write("</ul></div>");
///////////////////////////

            out.write("<div class='r-b'><ul class='right'>");
            for(Map.Entry user : users.entrySet()){

                out.write("User: "+ user.getKey());

                for (Object act : (ArrayList)user.getValue()) {
                    out.write("<li>"+ act +"</li>");
                }
            }
            out.write("</ul></div>");

        }catch (Exception ex){
            out.write("<h2>Error of inputting</h2>");
            out.write("<h2>" + ex.getMessage() + "</h2>");
        }
        finally {
            out.write("</div></body></html>");
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
