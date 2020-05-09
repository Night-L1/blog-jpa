package com.cfl.blog.controller;

import com.cfl.blog.pojo.Tag;
import com.cfl.blog.serevice.BlogService;
import com.cfl.blog.serevice.TagService;
import com.cfl.blog.vo.BlogQuary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Administer
 */
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}/")
    public String tags(
            Model model,
            @PageableDefault(size = 4,sort = "updateTime",direction = Sort.Direction.DESC)
            Pageable pageable,
            @PathVariable Long id
    ) {

        List<Tag> tags = tagService.listTagTop(1000);

        if (id == 0){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlogs(pageable,id));
        model.addAttribute("activeId",id);
        model.addAttribute("footerBlogs",blogService.listBlogsTop(3));
        return "tags";

    }

}
