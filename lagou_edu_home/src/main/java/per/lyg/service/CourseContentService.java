package per.lyg.service;

import per.lyg.pojo.Course;
import per.lyg.pojo.Course_Lesson;
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

    public String updateSectionStatus(int id, int status);

    /**
     * 保存课时信息
     * @param course_lesson
     * @return
     */
    public String saveLesson(Course_Lesson course_lesson);

    /**
     * 更新课时信息
     * @param course_lesson
     * @return
     */
    public String updateLesson(Course_Lesson course_lesson);

    Course_Section getSectionNameById(int parseInt);
}
