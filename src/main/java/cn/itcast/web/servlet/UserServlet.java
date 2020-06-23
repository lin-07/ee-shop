package cn.itcast.web.servlet;

import cn.itcast.constant.Constant;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import cn.itcast.utils.MyBeanUtils;
import cn.itcast.utils.UUIDUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author lin-PC
 */
@WebServlet(urlPatterns = {"/UserServlet"})
public class UserServlet extends BaseServlet {

    private static final UserService userService = new UserServiceImpl();

    /**
     * 激活用户
     * @param request
     * @param response
     * @return
     */
    public String active(HttpServletRequest request, HttpServletResponse response){
        try {
            String code = request.getParameter("code");
            userService.active(code);
            request.setAttribute("msg","用户激活成功,请前往登录");
            return "/msg.jsp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    public String logOut(HttpServletRequest request, HttpServletResponse response){
        try{
            request.getSession().invalidate();
            Cookie cookie = new Cookie("autoLoginUser","");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            Cookie cookie1 = new Cookie("rememberUserName","");
            cookie1.setPath("/");
            cookie1.setMaxAge(0);
            response.addCookie(cookie1);
            response.sendRedirect(request.getContextPath() + "/");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 登录
     * @param request
     * @param response
     * @return
     */
    public String login(HttpServletRequest request, HttpServletResponse response){
        try{
            // 获取验证码
            String verificationCode = request.getParameter("verificationCode");
            // 获取session中的验证码
            String originVerificationCode = (String) request.getSession().getAttribute("verificationCode");
            // 清除session中的验证码
            request.getSession().removeAttribute("verificationCode");

            if(!originVerificationCode.equalsIgnoreCase(verificationCode)){
                request.setAttribute("msg","验证码错误");
                return "/jsp/login.jsp";
            }
            User user = MyBeanUtils.populate(User.class, request.getParameterMap());
            User exitUser = userService.findByNameAndPwd(user);
            if(exitUser == null){
                request.setAttribute("msg","用户名或者密码错误");
                return "/jsp/login.jsp";
            }
            if(exitUser.getState() == Constant.USER_STATE_0){
                request.setAttribute("msg","请先去邮箱激活用户");
                return "/msg.jsp";
            }
            request.getSession().setAttribute("user",exitUser);
            String autoLogin = request.getParameter("autoLogin");
            if ("1".equals(autoLogin)){
                Cookie cookie = new Cookie("autoLoginUser",user.getUsername()+"@"+user.getPassword());
                cookie.setPath("/");
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
            }else{
                Cookie cookie = new Cookie("autoLoginUser","");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            String remember = request.getParameter("remember");
            if("1".equals(remember)){
                Cookie cookie = new Cookie("rememberUserName",user.getUsername());
                cookie.setPath("/");
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
            }else{
                Cookie cookie = new Cookie("rememberUserName","");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            response.sendRedirect(request.getContextPath() + "/");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 注册用户
     * @param request
     * @param response
     * @return
     */
    public String register(HttpServletRequest request, HttpServletResponse response){
        try{
            User user = MyBeanUtils.populate(User.class, request.getParameterMap());
            user.setState(Constant.USER_STATE_0);
            user.setUid(UUIDUtils.getId());
            user.setCode(UUIDUtils.getCode());
            userService.add(user);
            request.setAttribute("msg","注册成功请前往邮箱激活账号！");
            return "/msg.jsp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
