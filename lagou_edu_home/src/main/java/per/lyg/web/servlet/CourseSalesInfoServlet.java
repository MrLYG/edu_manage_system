package per.lyg.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import per.lyg.base.Constants;
import per.lyg.pojo.Course;
import per.lyg.service.CourseService;
import per.lyg.service.impl.CourseServiceImpl;
import per.lyg.utils.DateUtils;
import per.lyg.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李沅罡
 */
@WebServlet("/courseSalesInfo")
public class CourseSalesInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Course course = new Course();

        Map<String,Object> map = new HashMap<>();
        try {
            //1.创建磁盘文件工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //2.创建文件上传核心类
            ServletFileUpload upload = new ServletFileUpload(factory);
            //2.1设置上传文件的编码
            upload.setHeaderEncoding("utf-8");
            //2.2判断是否为文件上传表单
            boolean multipartContent = ServletFileUpload.isMultipartContent(req);
            if(multipartContent){
                //3.解析request -- 获取表单项的集合
                List<FileItem> list = upload.parseRequest(req);

                if(list !=null ){
                    //4.遍历表单项集合
                    for (FileItem item : list ) {
                        //5.判断是普通的表单项还是文件上传项
                        boolean formField = item.isFormField();
                        if(formField){
                            //普通表单项, 获取表单项中的数据，保存到map
                            String fieldName = item.getFieldName();
                            String value = item.getString("utf-8");
                            //使用map收集数据

                            System.out.println(fieldName + "="+value);
                            map.put(fieldName,value);
                        }else {
                            //文件上传项
                            //获取文件名
                            String fileName = item.getName();

                            //拼接新的文件名 使用UUID保证不重复
                            String newFileName = UUIDUtils.getUUID() + "_" + fileName;

                            //获取输入流
                            InputStream in = item.getInputStream();

                            //获取当前项目部署的路径
                            String realPath = req.getServletContext().getRealPath("/");
                            String webappsPath = realPath.substring(0, realPath.indexOf("lagou_edu_home"));
                            String uploadPath = webappsPath+"upload/"+newFileName;
                            FileOutputStream out = new FileOutputStream(uploadPath);

                            IOUtils.copy(in,out);

                            //关闭流
                            in.close();
                            out.close();
                            map.put("course_img_url", Constants.LOCAL_HOST+"/upload/"+newFileName);
                        }
                    }
                }
                BeanUtils.populate(course,map);
                String dateFormart = DateUtils.getDateFormart();
                CourseService courseService = new CourseServiceImpl();
                if(map.get("id")!=null){
                    //修改操作
                    course.setUpdate_time(dateFormart);
                    String res = courseService.updateCourseSalesInfo(course);
                    resp.getWriter().print(res);

                }else {
                    //新建操作
                    course.setCreate_time(dateFormart);
                    course.setUpdate_time(dateFormart);
                    course.setStatus(1);

                    String res = courseService.saveCourseSalesInfo(course);
                    resp.getWriter().print(res);

                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
