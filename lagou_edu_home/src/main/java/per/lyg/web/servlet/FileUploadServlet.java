package per.lyg.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import per.lyg.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                            //普通表单项
                            String fieldName = item.getFieldName();
                            String fileValue = item.getString("utf-8");
                            System.out.println(fieldName + "="+fileValue);
                        }else {
                            //文件上传项
                            //获取文件名
                            String fileName = item.getName();

                            //拼接新的文件名 使用UUID保证不重复
                            String newFileName = UUIDUtils.getUUID() + "_" + fileName;

                            //获取输入流
                            InputStream in = item.getInputStream();

                            FileOutputStream out = new FileOutputStream("");

                            IOUtils.copy(in,out);

                            //关闭流
                            in.close();
                            out.close();
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }








    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
