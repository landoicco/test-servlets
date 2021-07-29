package web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        boolean hasAccess = Boolean.parseBoolean((String)session.getAttribute("hasAccess"));

        if (session == null || !hasAccess)
        {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.html");
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {    }

    @Override
    public void destroy() {    }
}
