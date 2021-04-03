package per.lyg.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import per.lyg.dao.CourseContentDao;
import per.lyg.pojo.Course;
import per.lyg.pojo.Course_Lesson;
import per.lyg.pojo.Course_Section;
import per.lyg.utils.DateUtils;
import per.lyg.utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 李沅罡
 */
public class CourseContentDaoImpl implements CourseContentDao {
    /**
     * 根据课程ID查询课程相关信息
     * @param courseId
     * @return
     */
    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            //SQL根据课程ID 查询section信息
            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_section WHERE course_id = ?";
            //3.执行查询
            List<Course_Section> sectionList = qr.query(sql, new BeanListHandler<Course_Section>(Course_Section.class), courseId);

            for (Course_Section cs : sectionList ) {
                List<Course_Lesson> lessons = findLessonBySectionId(cs.getId());
                cs.setLessonList(lessons);

            }
            return sectionList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }


    }

    @Override
    public List<Course_Lesson> findLessonBySectionId(int sectionId) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_id,\n" +
                    "theme,\n" +
                    "duration,\n" +
                    "is_free,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_lesson WHERE section_id = ?";

            List<Course_Lesson> lessonList = qr.query(sql, new BeanListHandler<Course_Lesson>(Course_Lesson.class), sectionId);

            return lessonList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT id,course_name FROM course WHERE id = ?";

            Course course = qr.query(sql, new BeanHandler<Course>(Course.class), courseId);

            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int saveSection(Course_Section section) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "INSERT INTO course_section(\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "STATUS,\n" +
                    "create_time,\n" +
                    "update_time)VALUES(?,?,?,?,?,?,?);";

            Object[] param = {section.getCourse_id(),section.getSection_name(),section.getDescription(),
                    section.getOrder_num(),section.getStatus(),section.getCreate_time(),section.getUpdate_time()};

            int row = qr.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSection(Course_Section section) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course_section SET\n" +
                    "section_name = ?,\n" +
                    "description = ?,\n" +
                    "order_num = ?,\n" +
                    "update_time = ? WHERE id = ?";

            Object[] param = {section.getSection_name(),section.getDescription(),section.getOrder_num(),
                    section.getUpdate_time(),section.getId()};

            int row = qr.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSectionStatus(int id, int status) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course_section SET STATUS = ?,update_time = ? WHERE id = ?";

            Object[] param = {status, DateUtils.getDateFormart(),id};

            int row = qr.update(sql, param);

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int saveLesson(Course_Lesson lesson) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "INSERT INTO course_lesson(course_id,section_id,theme,duration,is_free,order_num,create_time,update_time) VALUES(?,?,?,?,?,?,?,?)";
            Object[] param = {lesson.getCourse_id(),lesson.getSection_id(),lesson.getTheme(),lesson.getDuration(),
                    lesson.getIs_free(),lesson.getOrderNum(),lesson.getCreate_time(),lesson.getUpdate_time()};
            int row = qr.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int updateLesson(Course_Lesson lesson) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course_lesson  SET course_id =?,section_id=?,theme=?,duration=?,is_free=?,order_num=?,create_time=?,update_time=? where id=?";
            Object[] param = {lesson.getCourse_id(),lesson.getSection_id(),lesson.getTheme(),lesson.getDuration(),
                    lesson.getIs_free(),lesson.getOrderNum(),lesson.getCreate_time(),lesson.getUpdate_time(),lesson.getId()};
            int row = qr.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
