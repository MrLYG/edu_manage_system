package per.lyg.dao;

import org.junit.Test;
import per.lyg.dao.impl.CourseContentDaoImpl;
import per.lyg.dao.impl.CourseDaoImpl;
import per.lyg.pojo.Course;
import per.lyg.pojo.Course_Section;
import per.lyg.utils.DateUtils;

import java.util.List;

public class TestCourseDao {
    CourseDao courseDao = new CourseDaoImpl();

    @Test
    public void testFindCourseDao(){
        List<Course> courseList = courseDao.findCourseList();
        System.out.println(courseList);

    }

    @Test
    public void testFindByCourseNameAndStatus(){
        List<Course> courseList = courseDao.findByCourseNameAndStatus("微服务",null);
        for (Course c :courseList
             ) {
            System.out.println(c.getCourse_name()+" "+c.getStatus());
        }

    }

    @Test
    public void testSaveCourseInfo(){
        //1.创建course对象
        Course course = new Course();
        course.setCourse_name("爱情36计");
        course.setBrief("学会去找对象");
        course.setTeacher_name("药水哥");
        course.setTeacher_info("人人都是药水哥");
        course.setPreview_first_field("共10讲");
        course.setPreview_second_field("每周日更新");
        course.setDiscounts(88.88);
        course.setPrice(188.0);
        course.setPrice_tag("最新优惠价");
        course.setShare_image_title("哈哈哈");
        course.setShare_title("嘻嘻嘻");
        course.setShare_description("天天向上");
        course.setCourse_description("爱情36计,就像一场游戏");
        course.setCourse_img_url("https://www.xx.com/xxx.jpg");
        course.setStatus(1); //1 上架 ,0 下架
        String formart = DateUtils.getDateFormart();
        course.setCreate_time(formart);
        course.setUpdate_time(formart);

        int i = courseDao.saveCourseSalesInfo(course);
        System.out.println(i);

    }
    @Test
    public void testUpdateCourse(){
        Course course = courseDao.findCourseById(1);
        System.out.println(course);
//        course.setTeacher_name("李老师");
//        courseDao.updateCourseStatus(course);
    }


}
