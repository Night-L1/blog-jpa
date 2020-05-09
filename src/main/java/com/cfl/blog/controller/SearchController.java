package com.cfl.blog.controller;

import com.cfl.blog.pojo.About;
import com.cfl.blog.pojo.Blog;
import com.cfl.blog.serevice.AboutService;
import com.cfl.blog.serevice.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administer
 */
@Controller
public class SearchController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private AboutService aboutService;

    @PostMapping("/search")
    public String search(
                         @PageableDefault(size = 4,sort = "updateTime",direction = Sort.Direction.DESC)
                         Pageable pageable,
                         String query,
                         Model model){

        // 根据标题和内容查询
        model.addAttribute("page",blogService.listBlogs("%"+query+"%",pageable));
        model.addAttribute("query",query);
        model.addAttribute("footerBlogs",blogService.listBlogsTop(3));
        return "search";
    }


    /**
     * 根据博客ID展示详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        Blog blog = blogService.getAndConvent(id);
        model.addAttribute("footerBlogs",blogService.listBlogsTop(3));
        model.addAttribute("blog",blog);

        Blog updateBlog = new Blog();
        BeanUtils.copyProperties(blog,updateBlog);
        updateBlog.setViews(blog.getViews()+1);
        blogService.saveBlog(updateBlog);

        About about = aboutService.getAbouts().get(0);
        model.addAttribute("about",about);

        return "blog";
    }
}
