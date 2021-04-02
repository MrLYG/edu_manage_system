package per.lyg.dao;

import org.junit.Test;
import per.lyg.dao.impl.CourseContentDaoImpl;
import per.lyg.pojo.Course;
import per.lyg.pojo.Course_Section;
import per.lyg.utils.DateUtils;

import java.util.List;

public class TestCourseContentDao {
    CourseContentDao courseContentDao = new CourseContentDaoImpl();
    @Test
    public void testFindSectionAndLessonByCourseId(){
        List<Course_Section> course_sections = courseContentDao.findSectionAndLessonByCourseId(59);
        System.out.println(course_sections);
    }

    //测试根据课程id 回显课程名称
    @Test
    public void testFindCourseByCourseId(){

        Course course = courseContentDao.findCourseByCourseId(59);

        System.out.println(course.getId()+"  "+course.getCourse_name());
    }
    //测试保存章节功能
    @Test
    public void testSaveSection(){

        Course_Section section = new Course_Section();
        section.setCourse_id(59);
        section.setSection_name("Vue高级2");
        section.setDescription("Vue相关的高级技术");
        section.setOrder_num(8);

        String dateFormart = DateUtils.getDateFormart();
        section.setCreate_time(dateFormart);
        section.setUpdate_time(dateFormart);
        section.setStatus(2); //0:隐藏；1：待更新；2：已发布

        int i = courseContentDao.saveSection(section);
        System.out.println(i);
    }

    //测试修改章节功能
    @Test
    public void testUpdateSection(){

        Course_Section section = new Course_Section();
        section.setId(41);
        section.setSection_name("微服务架构11");
        section.setDescription("微服务架构详细讲解");
        section.setOrder_num(3);
        section.setUpdate_time(DateUtils.getDateFormart());

        int i = courseContentDao.updateSection(section);
        System.out.println(i);
    }

    //测试修改章节状态
    @Test
    public void testUpdateSectionStatus(){

        int i = courseContentDao.updateSectionStatus(1, 0);
        System.out.println(i);
    }
}
