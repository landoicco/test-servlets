package web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import local.user.User;

import java.io.IOException;

public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
