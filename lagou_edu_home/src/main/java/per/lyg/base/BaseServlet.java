package per.lyg.base;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String methodName = req.getParameter("methodName");
        String methodName = null;
        //1.获取POST请求的 Content-Type类型
        String type = req.getHeader("Content-Type");
        //2.判断传递的数据是不是JSON格式
        if ("application/json;charset=utf-8".equals(type)) {
            //是JOSN格式 调用getPostJSON
            String postJSON = getPostJSON(req);
            System.out.println(postJSON);
            //将JSON格式的字符串转化为map
            Map<String,Object> map = JSON.parseObject(postJSON, Map.class);
            System.out.println(map);
            //从map集合中获取 methodName
            methodName =(String) map.get("methodName");

            //将获取到的数据,保存到request域对象中
            req.setAttribute("map",map);

        }else{
            methodName = req.getParameter("methodName");
        }


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

    /**
     * POST请求格式为:  application/json;charset=utf-8
     * 使用该方法进行读取
     * */
    public String getPostJSON(HttpServletRequest request){
        try {
            //1.从request中 获取缓冲输入流对象
            BufferedReader reader = request.getReader();

            //2.创建StringBuffer 保存读取出的数据
            StringBuffer sb = new StringBuffer();

            //3.循环读取
            String line = null;
            while((line = reader.readLine())!=null){
                sb.append(line);
            }

            //4.返回结果
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
