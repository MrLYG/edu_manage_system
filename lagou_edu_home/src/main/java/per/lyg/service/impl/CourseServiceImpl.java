package per.lyg.service.impl;

import per.lyg.base.StatusCode;
import per.lyg.dao.CourseDao;
import per.lyg.dao.impl.CourseDaoImpl;
import per.lyg.pojo.Course;
import per.lyg.service.CourseService;
import per.lyg.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Course findCourseById(int id) {

        return courseDao.findCourseById(id);
    }

    @Override
    public String updateCourseSalesInfo(Course c) {
        int i = courseDao.updateCourseSalesInfo(c);
        if(i>0){
            return StatusCode.SUCCESS.toString();
        }else {
            return StatusCode.FAIL.toString();
        }

    }

    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {
        int i = courseDao.updateCourseStatus(course);
        Map<String,Integer> map = new HashMap<>();
        
        if(i>0){
            if(course.getStatus()==0){
                map.put("status",0);
            }else {
                map.put("status",1);
            }
        }
        return map;
    }
}
