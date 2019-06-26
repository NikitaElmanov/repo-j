package ru.pro.filters;

import ru.pro.filters.impl.MyFirstFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class HelloWorldFilter extends MyFirstFilter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("Entering HelloWorldFilter");
        servletRequest.setAttribute("hello", "Hey World!");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Exiting HelloWorldFiler");
    }
}
