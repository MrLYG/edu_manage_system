package per.lyg.service.impl;

import per.lyg.base.StatusCode;
import per.lyg.dao.CourseContentDao;
import per.lyg.dao.impl.CourseContentDaoImpl;
import per.lyg.pojo.Course;
import per.lyg.pojo.Course_Lesson;
import per.lyg.pojo.Course_Section;
import per.lyg.service.CourseContentService;
import per.lyg.utils.DateUtils;

import java.util.List;

/**
 * @author 李沅罡
 */
public class CourseContentServiceImpl implements CourseContentService {
    CourseContentDao contentDao = new CourseContentDaoImpl();
    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        List<Course_Section> sections = contentDao.findSectionAndLessonByCourseId(courseId);

        return sections;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {

        return contentDao.findCourseByCourseId(courseId);
    }

    @Override
    public String saveSection(Course_Section section) {
        section.setCreate_time(DateUtils.getDateFormart());
        section.setUpdate_time(DateUtils.getDateFormart());
        int row = contentDao.saveSection(section);
        if (row > 0) {

            return StatusCode.SUCCESS.toString();
        }else {
            return StatusCode.FAIL.toString();
        }

    }

    @Override
    public String updateSection(Course_Section section) {
        //1.补全信息
        section.setUpdate_time(DateUtils.getDateFormart());

        int row = contentDao.updateSection(section);
        if (row > 0) {
            return StatusCode.SUCCESS.toString();
        }else {
            return StatusCode.FAIL.toString();
        }
    }

    @Override
    public String updateSectionStatus(int id, int status) {
        int row = contentDao.updateSectionStatus(id, status);
        if (row > 0) {
            return StatusCode.SUCCESS.toString();
        }else {
            return StatusCode.FAIL.toString();
        }
    }

    @Override
    public String saveLesson(Course_Lesson course_lesson) {
        course_lesson.setCreate_time(DateUtils.getDateFormart());
        course_lesson.setUpdate_time(DateUtils.getDateFormart());
        int row = contentDao.saveLesson(course_lesson);
        if (row > 0) {
            return StatusCode.SUCCESS.toString();
        }else {
            return StatusCode.FAIL.toString();
        }
    }

    @Override
    public String updateLesson(Course_Lesson course_lesson) {
        course_lesson.setUpdate_time(DateUtils.getDateFormart());
        int row = contentDao.updateLesson(course_lesson);
        if (row > 0) {
            return StatusCode.SUCCESS.toString();
        }else {
            return StatusCode.FAIL.toString();
        }
    }
}
