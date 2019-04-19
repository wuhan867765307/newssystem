package cn.news.Filter;

import javax.servlet.*;
import java.io.IOException;

public class CharSetFilter implements Filter {
    private FilterConfig config;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----过滤器初始化----");
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String charset = config.getInitParameter("charset");
        if(charset == null){
            charset = "UTF-8";
        }
        servletRequest.setCharacterEncoding(charset);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
