package per.lyg.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = req.getParameter("methodName");
        if(methodName!=null){
            try {
                Class c = this.getClass();
                Method method = c.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
                method.invoke(this,req,resp);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("该功能不存在");
            }
        }

    }
}
