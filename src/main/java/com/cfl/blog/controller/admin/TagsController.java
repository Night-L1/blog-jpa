package com.cfl.blog.controller.admin;

import com.cfl.blog.pojo.Tag;
import com.cfl.blog.pojo.Type;
import com.cfl.blog.serevice.TagService;
import com.cfl.blog.serevice.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @author Administer
 */
@Controller
@RequestMapping("/admin")
public class TagsController {

    @Autowired
    private TagService tagService;

    /**
     * 查询所有的便签
     * @param model
     * @return
     */
    @RequestMapping("/tags")
    public String tags(Model model){
        List<Tag> tags = tagService.getTages();
        model.addAttribute("tags",tags);
        return "admin/tags";
    }


    /**
     * 新增标签
     * @param tag
     * @return
     */
    @RequestMapping("/tags/input")
    public String addTags(
            Tag tag,
            RedirectAttributes attributes){
        Tag tagByName = tagService.findTagByName(tag.getName());
        if (tagByName != null){
            //不允许添加
            attributes.addFlashAttribute("tag_err_msg","该标签已存在！");
            return "redirect:/admin/tags";
        }
        //允许添加
        Tag tag1 = tagService.addTag(tag);
        if (tag1 != null){
            // 添加成功
            attributes.addFlashAttribute("tag_succ_msg","标签添加成功！");
        }else {
            // 添加失败
            attributes.addFlashAttribute("tag_err_msg","标签添加失败！");
        }
        return "redirect:/admin/tags";
    }


    /**
     * 删除标签
     * @param id
     * @return
     */
    @RequestMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        tagService.delTagById(id);
        return "redirect:/admin/tags";
    }



}
