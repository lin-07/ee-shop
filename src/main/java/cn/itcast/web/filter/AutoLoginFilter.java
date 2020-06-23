package cn.itcast.web.filter;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import cn.itcast.utils.CookUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lin-PC
 */
@WebFilter(urlPatterns = {"/*"})
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            // System.out.println("AutoLoginFilter");
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            User user = (User) request.getSession().getAttribute("user");
            if(user != null){
                filterChain.doFilter(request,response);
                return;
            }
            Cookie[] cookies = request.getCookies();
            Cookie autoLoginUser = CookUtils.getCookieByName("autoLoginUser",cookies);
            if(autoLoginUser == null){
                filterChain.doFilter(request,response);
                return;
            }
            String[] split = autoLoginUser.getValue().split("@");
            user = new User();
            user.setUsername(split[0]);
            user.setPassword(split[1]);
            UserService userService = new UserServiceImpl();
            User exitUser = userService.findByNameAndPwd(user);
            if(exitUser == null){
                filterChain.doFilter(request,response);
                return;
            }
            request.getSession().setAttribute("user",exitUser);
            filterChain.doFilter(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
