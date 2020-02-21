package com.gdut.imis.campus.controller;

import com.gdut.imis.campus.model.Enterprise;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.service.EnterpriseService;
import com.gdut.imis.campus.service.StudentService;
import com.gdut.imis.campus.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UploadController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private EnterpriseService enterpriseService;

    private ResourceLoader resourceLoader;

    @Autowired
    public void TestController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping("/profile/fileUpload")
    public String upload(@RequestParam("fileName") MultipartFile file, HttpServletRequest request){

        // 要上传的目标文件存放路径
        String localPath = "D:/Program Files (x86)/IntelliJ IDEA 2018/Workspace/campus/src/main/resources/static/photos";
        // 上传成功或者失败的提示
        String msg = "";

        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            msg = "上传成功！";
        }else {
            msg = "上传失败！";

        }

        // 返回图片路径，显示图片
        String fileName2="/photos/"+file.getOriginalFilename();
        //System.out.println(fileName2);
        request.getSession().setAttribute("fileName",fileName2);

        //将路径存入数据库
        if(request.getSession().getAttribute("type").toString().equals("0")) {
            Student stu = (Student) request.getSession().getAttribute("user");
            stu.setAvatarUrl(fileName2);
            request.getSession().setAttribute("user", stu);
            studentService.createOrUpdate(stu);
        }else if(request.getSession().getAttribute("type").toString().equals("2")) {
            Enterprise ent= (Enterprise) request.getSession().getAttribute("user");
            ent.setAvatarUrl(fileName2);
            request.getSession().setAttribute("user", ent);
            enterpriseService.createOrUpdate(ent);
        }

        return "redirect:/profile/info";
    }

    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("/show")
    public ResponseEntity showPhotos(String fileName){

        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
