package ru.web.serv.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.web.serv.servlets.AllRequestsServlet;
import ru.web.serv.servlets.MirrorServlet;

public class Main {

    public static void main(String[] args) throws Exception {
        AllRequestsServlet servlet = new AllRequestsServlet();
        MirrorServlet mirSerlet = new MirrorServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.addServlet(new ServletHolder(servlet), "/*");
        context.addServlet(new ServletHolder(mirSerlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
