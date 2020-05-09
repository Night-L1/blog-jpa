package com.cfl.blog.controller.admin;

import com.cfl.blog.pojo.Blog;
import com.cfl.blog.pojo.User;
import com.cfl.blog.serevice.BlogService;
import com.cfl.blog.serevice.TagService;
import com.cfl.blog.serevice.TypeService;
import com.cfl.blog.util.ConstFiled;
import com.cfl.blog.vo.BlogQuary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Administer
 * 后台 blog BlogController
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;






    @GetMapping("/blogs")
    public String blogList(
            Model model,
            @PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)
            Pageable pageable,
            BlogQuary blog){
        model.addAttribute("types",typeService.types());
        model.addAttribute("pages",blogService.listBlogs(pageable,blog));
        return "admin/blogs";
    }



    @PostMapping("/blogs/search")
    public String search(
            Model model,
            @PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)
                    Pageable pageable,
            BlogQuary blog){
        model.addAttribute("pages",blogService.listBlogs(pageable,blog));
        // 加载片段
        return "admin/blogs::blogList";
    }


    @GetMapping("/blog/publish")
    public String publish(Model model){
        model.addAttribute("blog",new Blog());
        setTypeAndTag(model);
        return "admin/publish";
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.types());
        model.addAttribute("tags",tagService.getTages());
    }


    @GetMapping("/blog/{id}/update")
    public String update(@PathVariable("id") Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.initTags();
        model.addAttribute("blog",blog);
        return "admin/publish";
    }



    /**
     * 新增博客
     */
    @PostMapping("/save")
    public String post(
            Blog blog,
            HttpSession session,
            RedirectAttributes attributes){


        blog.setUser((User) session.getAttribute(ConstFiled.USER_INFO));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTages(blog.getTagsIds()));
        Blog b;
        if (blog.getId() == null){
            b = blogService.saveBlog(blog);
        }else {
            b = blogService.update(blog);
        }

        if (b == null){
            attributes.addFlashAttribute("message","操作失败！");
        }else {
            attributes.addFlashAttribute("message","操作成功！");
        }

        return "redirect:/admin/blogs";
    }



    @GetMapping("/blog/{id}/delete")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/blogs";
    }



}
