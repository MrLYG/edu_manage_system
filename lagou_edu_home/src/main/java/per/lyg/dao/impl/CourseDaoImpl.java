package per.lyg.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import per.lyg.dao.CourseDao;
import per.lyg.pojo.Course;
import per.lyg.utils.DruidUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    /**
     * 查询课程列表信息
     * @return
     */
    @Override
    public List<Course> findCourseList() {
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            String sql = "SELECT\n" +
                    "\tid,\n" +
                    "\tcourse_name,\n" +
                    "\tprice,\n" +
                    "\tsort_num,\n" +
                    "\t`status`\n" +
                    "FROM\n" +
                    "\tcourse\n" +
                    "WHERE\n" +
                    "\tis_del = ?";
            List<Course> query = queryRunner.query(sql, new BeanListHandler<Course>(Course.class), 0);

            return query;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 根据条件查询课程信息
     * @param courseName
     * @param status
     * @return
     */
    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            StringBuffer sb = new StringBuffer("SELECT\n" +
                    "\tid,\n" +
                    "\tcourse_name,\n" +
                    "\tprice,\n" +
                    "\tsort_num,\n" +
                    "\t`status`\n" +
                    "FROM\n" +
                    "\tcourse\n" +
                    "WHERE 1=1 and" +
                    "\tis_del = ?\n"
                    );

            //保存参数
            List<Object> list = new ArrayList<>();
            list.add(0);
            if(courseName != null && courseName != ""){
                sb.append("AND course_name LIKE ?\n");
                courseName = "%"+courseName+"%";
                list.add(courseName);
            }
            if(status != null && status != "" ){
                sb.append("AND `status` =?");
                list.add(Integer.parseInt(status));
            }
            List<Course> query = qr.query(sb.toString(), new BeanListHandler<Course>(Course.class), list.toArray());
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     *保存课程信息
     * @param course
     */
    @Override
    public int saveCourseSalesInfo(Course course) {
        try {
            //1.创建QueryRunner
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            //2.编写SQL
            String sql = "INSERT INTO course(\n" +
                    "course_name,\n" +
                    "brief,\n" +
                    "teacher_name,\n" +
                    "teacher_info,\n" +
                    "preview_first_field,\n" +
                    "preview_second_field,\n" +
                    "discounts,\n" +
                    "price,\n" +
                    "price_tag,\n" +
                    "share_image_title,\n" +
                    "share_title,\n" +
                    "share_description,\n" +
                    "course_description,\n" +
                    "course_img_url,\n" +
                    "STATUS,\n" +
                    "create_time,\n" +
                    "update_time\n" +
                    ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            //3.准备参数
            Object[] param = {course.getCourse_name(),course.getBrief(),course.getTeacher_name(),course.getTeacher_info(),
                    course.getPreview_first_field(),course.getPreview_second_field(),course.getDiscounts(),course.getPrice(),
                    course.getPrice_tag(),course.getShare_image_title(),course.getShare_title(),course.getShare_description(),
                    course.getCourse_description(),course.getCourse_img_url(),course.getStatus(),course.getCreate_time(),course.getUpdate_time()};

            //4.执行插入操作
            int row = qr.update(sql, param);

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
