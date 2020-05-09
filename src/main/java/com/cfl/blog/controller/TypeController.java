package com.cfl.blog.controller;

import com.cfl.blog.pojo.Type;
import com.cfl.blog.serevice.BlogService;
import com.cfl.blog.serevice.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}/")
    public String types(
            Model model,
            @PageableDefault(size = 4,sort = "updateTime",direction = Sort.Direction.DESC)
            Pageable pageable,
            @PathVariable Long id
    ) {

        List<Type> types = typeService.listTypeTop(1000);

        if (id == 0){
            id = types.get(0).getId();
        }
        BlogQuary blogQuary = new BlogQuary();
        blogQuary.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlogs(pageable,blogQuary));
        model.addAttribute("activeId",id);
        model.addAttribute("footerBlogs",blogService.listBlogsTop(3));
        return "types";

    }


}
