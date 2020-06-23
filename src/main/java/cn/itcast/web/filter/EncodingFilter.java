package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lin-PC
 */
@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request;
        HttpServletResponse response;
        try{
            request = (HttpServletRequest)servletRequest;
            response = (HttpServletResponse)servletResponse;
            // 设置post请求参数编码
            request.setCharacterEncoding("utf-8");
            // 设置响应编码
            response.setHeader("content-type","text/html;charset=utf-8");
            HttpServletRequest proxyRequest = (HttpServletRequest) Proxy.newProxyInstance(
                    EncodingFilter.class.getClassLoader(),
                    request.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if ("GET".equalsIgnoreCase(request.getMethod())) {
                                if ("getParameter".equalsIgnoreCase(method.getName())) {
                                    String value = request.getParameter(args[0].toString());
                                    // 防止没有获取值导致报错
                                    if (value == null) {
                                        return null;
                                    }
                                    return new String(value.getBytes("iso-8859-1"),"utf-8");
                                }
                            }
                            return method.invoke(request,args);
                        }
                    }
            );
            filterChain.doFilter(proxyRequest,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
