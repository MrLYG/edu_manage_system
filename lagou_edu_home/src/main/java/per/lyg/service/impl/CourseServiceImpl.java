package per.lyg.service.impl;

import per.lyg.base.StatusCode;
import per.lyg.dao.CourseDao;
import per.lyg.dao.impl.CourseDaoImpl;
import per.lyg.pojo.Course;
import per.lyg.service.CourseService;
import per.lyg.utils.DateUtils;

import java.util.List;

/**
 * @author 李沅罡
 */
public class CourseServiceImpl implements CourseService {
    CourseDao courseDao = new CourseDaoImpl();
    @Override
    public List<Course> findCourseList() {
        CourseDao courseDao = new CourseDaoImpl();
        return courseDao.findCourseList();
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        CourseDao courseDao = new CourseDaoImpl();
        return courseDao.findByCourseNameAndStatus(courseName,status) ;
    }

    @Override
    public String saveCourseSalesInfo(Course course) {
        //1.补全课程营销信息
        String strDate = DateUtils.getDateFormart();
        course.setCreate_time(strDate);
        course.setUpdate_time(strDate);
        course.setStatus(1);
        //2.执行插入操作

        int row = courseDao.saveCourseSalesInfo(course);
        if(row>0){
            return StatusCode.SUCCESS.toString();
        }else {
            return StatusCode.FAIL.toString();
        }

    }
}
