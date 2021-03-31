package per.lyg.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import per.lyg.base.BaseServlet;
import per.lyg.pojo.Course;
import per.lyg.service.CourseService;
import per.lyg.service.impl.CourseServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 李沅罡
 */
@WebServlet("/course")
public class CourseServlet extends BaseServlet {

    public void findCourseList(HttpServletRequest request, HttpServletResponse response){
        CourseService cs = new CourseServiceImpl();
        List<Course> courseList = cs.findCourseList();
        //响应结果
        //SimplePropertyPreFilter制定要转换的JSON字段
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id","course_name",
                "price","sort_num","status");
        String res = JSON.toJSONString(courseList,filter);

        try {
            response.getWriter().print(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据条件查询课程信息
     * @param request
     * @param response
     */
    public void findByCourseNameAndStatus(HttpServletRequest request, HttpServletResponse response){
        String course_name = request.getParameter("course_name");
        String status  = request.getParameter("status");

        CourseService cs = new CourseServiceImpl();
        List<Course> courseList = cs.findByCourseNameAndStatus(course_name,status);
        //响应结果
        //SimplePropertyPreFilter制定要转换的JSON字段
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id","course_name",
                "price","sort_num","status");
        String res = JSON.toJSONString(courseList,filter);

        try {
            response.getWriter().print(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
