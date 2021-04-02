package per.lyg.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import per.lyg.base.BaseServlet;
import per.lyg.pojo.Course;
import per.lyg.service.CourseService;
import per.lyg.service.impl.CourseServiceImpl;
import per.lyg.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据id查询课程信息
     * @param request
     * @param response
     */
    public void findCourseById(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");

        CourseService cs = new CourseServiceImpl();
        Course course = cs.findCourseById(Integer.parseInt(id));
        //响应结果
        //3.返回结果
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id",
                "course_name","brief","teacher_name","teacher_info","price","price_tag",
                "discounts","preview_first_field","preview_second_field","course_img_url","share_title",
                "share_description","course_description","share_image_title");

        String result = JSON.toJSONString(course,filter);

        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改课程状态
     * @param request
     * @param response
     */
    public void updateCourseStatus(HttpServletRequest request, HttpServletResponse response){
        try {
            String id = request.getParameter("id");
            CourseService cs = new CourseServiceImpl();
            Course course = cs.findCourseById(Integer.parseInt(id));
            int status = course.getStatus();
            if (status == 0) {
                course.setStatus(1);
            }else {
                course.setStatus(0);
            }
            course.setUpdate_time(DateUtils.getDateFormart());
            Map<String, Integer> map = cs.updateCourseStatus(course);

            String res = JSON.toJSONString(map);

            response.getWriter().print(res);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
