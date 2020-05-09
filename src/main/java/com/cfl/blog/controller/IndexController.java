package com.cfl.blog.controller;

import com.cfl.blog.serevice.AboutService;
import com.cfl.blog.serevice.BlogService;
import com.cfl.blog.serevice.TagService;
import com.cfl.blog.serevice.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administer
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private AboutService aboutService;

    @GetMapping("/")
    public String index(
            Model model,
            @PageableDefault(size = 4,sort = "updateTime",direction = Sort.Direction.DESC)
            Pageable pageable,
            HttpServletRequest request) {
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listBlogsTop(8));
        model.addAttribute("footerBlogs",blogService.listBlogsTop(3));
        request.getSession().setAttribute("about",aboutService.getAbouts().get(0));
        return "index";
    }


}
