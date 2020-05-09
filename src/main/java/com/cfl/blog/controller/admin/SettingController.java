package com.cfl.blog.controller.admin;

import com.cfl.blog.pojo.About;
import com.cfl.blog.serevice.AboutService;
import com.cfl.blog.util.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Administer
 */
@Controller
@RequestMapping("/admin")
public class SettingController {

    @Autowired
    private AboutService aboutService;

    @RequestMapping("/settings")
    public String setting(Model model){


        List<About> abouts = aboutService.getAbouts();
        if (abouts != null && abouts.size() >0){
            model.addAttribute("about",abouts.get(0));
        }
        return "admin/settings";
    }

    @RequestMapping("/updateSettings")
    public String updateSettings(
            MultipartFile[] files,
            RedirectAttributes attributes,
            About about,
            HttpServletRequest request){

        String realPath = "/usr/tomcat8080/apache-tomcat-8.5.47/webapps/blog/";

        String ipAddr = BlogUtils.getAddr("8080") + "blog/";


        File file = new File(realPath);
        if (!file.exists()){
            file.mkdir();
        }
        List<String> fileNameList = new ArrayList<>();
        for (MultipartFile m:files){
            String name = BlogUtils.makeFileName(Objects.requireNonNull(m.getOriginalFilename()));
            try {
                fileNameList.add(name);
                m.transferTo(new File(realPath,name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileNameList.size();
        about.setTxPic(ipAddr + fileNameList.get(0));
        about.setWxPic(ipAddr + fileNameList.get(1));
        about.setAliPay(ipAddr + fileNameList.get(2));
        about.setWxPay(ipAddr + fileNameList.get(3));
        aboutService.save(about);
        attributes.addFlashAttribute("message","操作成功！");
        return "redirect:/admin/settings";
    }


}
