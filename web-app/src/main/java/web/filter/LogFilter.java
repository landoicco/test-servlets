package web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        //Cast Servlet objects to HttpServlet objects
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String message = "REQUEST SEND-->" + httpRequest.getMethod()
                + " TO-->" + httpRequest.getRequestURI();

        logger.info(message);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
