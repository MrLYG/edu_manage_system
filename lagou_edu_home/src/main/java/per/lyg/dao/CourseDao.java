package per.lyg.dao;

import per.lyg.pojo.Course;

import java.util.List;

/**
 * @author 李沅罡
 */
public interface CourseDao {
    /**
     * 查询课程列表信息
     * @return
     */
    public List<Course> findCourseList();

    /**
     * 根据条件查询课程信息
     * @param courseName
     * @param status
     * @return
     */
    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    /**
     *保存课程信息
     * @param course
     */
    public int saveCourseSalesInfo(Course course);
}
