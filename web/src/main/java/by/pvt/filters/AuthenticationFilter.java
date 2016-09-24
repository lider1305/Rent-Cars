package by.pvt.filters;

import by.pvt.constants.Pages;
import by.pvt.pojo.Client;
import by.pvt.util.PathList;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.pvt.constants.Constants.CLIENT;


public class AuthenticationFilter implements Filter {
    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private List<String> guestURL = new PathList().setListURLGuest();
    private List<String> adminURL = new PathList().setListURLAdmin();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        Client sessionClient = (Client) httpRequest.getSession().getAttribute(CLIENT);
        //check url without login
        if (requestURI.equals(checkIn(httpRequest, guestURL))) {
            chain.doFilter(httpRequest, httpResponse);
            //check user Role and redirect to the page according to the role
        } else if (sessionClient != null & requestURI.equals(httpRequest.getContextPath() + Pages.PAGE_USER)) {
            getRole(request, response, sessionClient);
            //check authorization
        } else if (sessionClient != null) {
            //if user has role user, only user URLs
            if (sessionClient.getRole().getName().equals(USER) & requestURI.equals(checkIn(httpRequest, adminURL))) {
                httpResponse.sendRedirect(httpRequest.getContextPath() +Pages.VALUE_INDEX);
                //if user has admin access, all URLs
            } else {
                chain.doFilter(request, response);
            }
            //if session is over or user want to get page without login
        } else {
            httpRequest.getSession().invalidate();
            httpResponse.sendRedirect(httpRequest.getContextPath() +Pages.VALUE_INDEX);
        }
    }

    //check the role
    private void getRole(ServletRequest request, ServletResponse response, Client sessionClient) throws ServletException, IOException {
        switch (sessionClient.getRole().getName()) {
            case ADMIN:
                request.getRequestDispatcher(Pages.PAGE_ADMIN)
                        .forward(request, response);
                break;
            case USER:
                request.getRequestDispatcher(Pages.PAGE_CLIENT)
                        .forward(request, response);
                break;
            default:
                request.getRequestDispatcher(Pages.PAGE_INDEX)
                        .forward(request, response);
                break;
        }
    }

    //check the url
    private String checkIn(HttpServletRequest httpRequest, List<String> list) throws IOException, ServletException {
        String path = "";
        for (String line : list) {
            path = httpRequest.getContextPath() + line;
            if (path.equals(httpRequest.getRequestURI())) {
                path = httpRequest.getRequestURI();
                break;
            }
        }
        return path;
    }

    @Override
    public void destroy() {

    }
}
