package cn.news.Filter;

import cn.news.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    private FilterConfig config;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        String contextPath=httpServletRequest.getContextPath();

        //不拦截的地址
        String noLoginPaths = config.getInitParameter("noLoginPaths");

        if(noLoginPaths != null){
            //使用";"分割
            String[] strArray = noLoginPaths.split(";");
            for(int i = 0; i < strArray.length; i++){
                if(strArray[i] == null || "".equals(strArray[i])){
                    continue;
                }
                //httpServletRequest.getRequestURI()获取请求页面的相对路径
                //indexOf如果此字符串中没有这样的字符，则返回 -1
                if(httpServletRequest.getRequestURI().indexOf(strArray[i]) != -1){
                    filterChain.doFilter(httpServletRequest,httpServletResponse);
                    return;
                }
            }
        }
        //获取session中的username(sessinon在用户访问第一次访问服务器时创建)
        if(httpSession.getAttribute(Constants.USER_SESSION) != null){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else{
            System.out.println("aaaaa");
            httpServletResponse.sendRedirect(contextPath+"/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
