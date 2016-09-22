package by.pvt.filters;

import by.pvt.constants.Pages;
import by.pvt.constants.UIParams;
import by.pvt.constants.WebErrorMessages;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        try {
            httpRequest.getRequestURI();
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (Exception e) {
            servletRequest.setAttribute(UIParams.EXCEPTION, WebErrorMessages.EXCEPTION);
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(Pages.PAGE_ERROR);
            dispatcher.forward(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
