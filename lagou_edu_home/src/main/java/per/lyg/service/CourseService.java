package per.lyg.service;

import per.lyg.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * 课程管理模块service接口
 * @author 李沅罡
 */
public interface CourseService {
    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    public String saveCourseSalesInfo(Course course);

    public Course findCourseById(int id);

    public String updateCourseSalesInfo(Course c);

    public Map<String,Integer> updateCourseStatus(Course course);
}
