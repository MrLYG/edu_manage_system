package per.lyg.dao;

import per.lyg.pojo.Course;
import per.lyg.pojo.Course_Lesson;
import per.lyg.pojo.Course_Section;

import java.util.List;

/**
 * 课程内容管理DAO
 * @author 李沅罡
 */
public interface CourseContentDao {
    /**+
     *  根据课程ID查询章节与课时信息
     * @param courseId
     * @return
     */
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    /**
     * 根据章节ID查询课时信息
     * @param sectionId
     * @return
     */
    public List<Course_Lesson> findLessonBySectionId(int sectionId);

    /**
     * 根据课程id 回显课程信息
     * @param courseId
     * @return
     */
    public Course findCourseByCourseId(int courseId);

    /**
     * 保存章节信息
     * @param section
     * @return
     */
    public int saveSection(Course_Section section);

    /**
     * 修改章节
     * @param section
     * @return
     */
    public int updateSection(Course_Section section);
    /**
     * 修改章节状态
     * @param id
     * @param status
     * @return
     */
    public int updateSectionStatus(int id, int status);
}
