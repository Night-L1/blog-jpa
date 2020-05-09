package com.cfl.blog.controller;

import com.cfl.blog.serevice.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Administer
 */
@Controller
public class ArchivesController {


    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {

        model.addAttribute("map",blogService.archiveBlog());
        model.addAttribute("count",blogService.blogCount());
        model.addAttribute("footerBlogs",blogService.listBlogsTop(3));
        return "archives";

    }

}
