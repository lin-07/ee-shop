package cn.itcast.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author lin-PC
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String method = request.getParameter("method");
            if (method == null) {
                method = "execute";
            }
            Method existMethod = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            String jspPath = (String) existMethod.invoke(this,request,response);
            if (jspPath != null) {
                request.getRequestDispatcher(jspPath).forward(request,response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public String execute(HttpServletRequest request, HttpServletResponse response){
        // NOOP
        return null;
    }
}
