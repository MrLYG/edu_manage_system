package per.lyg.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.apache.commons.beanutils.BeanUtils;
import per.lyg.base.BaseServlet;
import per.lyg.pojo.Course;
import per.lyg.pojo.Course_Lesson;
import per.lyg.pojo.Course_Section;
import per.lyg.service.CourseContentService;
import per.lyg.service.impl.CourseContentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 李沅罡
 */
@WebServlet("/courseContent")
public class CourseContentServlet extends BaseServlet {

    CourseContentService contentService = new CourseContentServiceImpl();
    public void findSectionAndLessonByCourseId(HttpServletRequest request, HttpServletResponse response){
        try {
            String course_id = request.getParameter("course_id");

            List<Course_Section> sections = contentService.findSectionAndLessonByCourseId(Integer.parseInt(course_id));

            String res = JSON.toJSONString(sections);
            response.getWriter().print(res);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回显章节对应的课程信息
     * @param request
     * @param response
     */
    public void findCourseById(HttpServletRequest request, HttpServletResponse response){
        try {
            String course_id = request.getParameter("course_id");

            Course course = contentService.findCourseByCourseId(Integer.parseInt(course_id));

            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name");
            String res = JSON.toJSONString(course,filter);

            response.getWriter().print(res);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存和修改章节信息
     * @param request
     * @param response
     */
    public void saveOrUpdateSection(HttpServletRequest request, HttpServletResponse response){

        try {
            Map<String,Object> mapReq = (Map) request.getAttribute("map");

            Course_Section course_section = new Course_Section();

            BeanUtils.populate(course_section,mapReq);
            String res = null;
            if (course_section.getId() == 0) {
                //新增操作
                res = contentService.saveSection(course_section);
            }else {
                //修改操作
                res = contentService.updateSection(course_section);
            }
            response.getWriter().print(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateSectionStatus(HttpServletRequest request, HttpServletResponse response){
        try {
            String id = request.getParameter("id");
            String status = request.getParameter("status");

            String s = contentService.updateSectionStatus(Integer.parseInt(id), Integer.parseInt(status));

            response.getWriter().print(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void saveOrUpdateLesson(HttpServletRequest request, HttpServletResponse response){

        try {
            Map<String,Object> mapReq = (Map) request.getAttribute("map");

            Course_Lesson course_lesson = new Course_Lesson();
            BeanUtils.populate(course_lesson,mapReq);
            String res = null;
            if (course_lesson.getId() == 0) {
                //新增操作
                res = contentService.saveLesson(course_lesson);
            }else {
                //修改操作
                res = contentService.updateLesson(course_lesson);
            }
            response.getWriter().print(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
