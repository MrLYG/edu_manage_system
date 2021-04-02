package per.lyg.service;

import per.lyg.pojo.Course;
import per.lyg.pojo.Course_Section;

import java.util.List;

/**
 * 课程内容管理Service
 * @author 李沅罡
 */
public interface CourseContentService {

    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    public Course findCourseByCourseId(int courseId);

    public String saveSection(Course_Section section);

    public String updateSection(Course_Section section);
}
