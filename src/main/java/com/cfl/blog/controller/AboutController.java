package com.cfl.blog.controller;


import com.cfl.blog.pojo.About;
import com.cfl.blog.serevice.AboutService;
import com.cfl.blog.serevice.BlogService;
import com.cfl.blog.util.BlogUtils;
import com.cfl.blog.vo.AboutVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author Administer
 */
@Controller
public class AboutController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private AboutService aboutService;


    @GetMapping("/about")
    public String about(Model model) {


        About about = aboutService.getAbouts().get(0);
        AboutVo aboutVo = new AboutVo();
        BeanUtils.copyProperties(about,aboutVo);

        aboutVo.setHobby(BlogUtils.makeTags(about.getHobbys()));
        aboutVo.setTechnology(BlogUtils.makeTags(about.getTechnologys()));

        System.out.println(aboutVo);

        model.addAttribute("footerBlogs",blogService.listBlogsTop(3));
        model.addAttribute("about",aboutVo);
        return "about";

    }




}
