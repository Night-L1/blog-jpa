package com.cfl.blog.controller.admin;

import com.cfl.blog.pojo.Type;
import com.cfl.blog.serevice.TypeService;
import com.cfl.blog.util.ConstFiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Administer
 *
 * 分类
 */

@Controller
@RequestMapping("/admin")
public class TypesController {

    @Autowired
    private TypeService typeService;


    /**
     * 查看类型分类
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String toTypePage(
            // 设置默认每页多少条数据，按照id排序，倒序
            @PageableDefault(size = 3,sort = {"id",},direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model){
        Page<Type> types = typeService.listTypes(pageable);
        // 将数据携带待页面
        model.addAttribute("page",types);
        return "admin/types";
    }


    /**
     * 新增分类
     * @param type
     * @param attributes
     * @return
     */
    @RequestMapping("/types/input")
    public String typeInput(
            Type type,
            RedirectAttributes attributes){

        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
            // 已经存在
            attributes.addFlashAttribute(ConstFiled.OPTION_FAILED_EXIT,"抱歉，该分类已存在！");
            return "redirect:/admin/types";
        }

        Type type1 = typeService.saveType(type);
        if (type1 == null){
            // 新增失败
            attributes.addFlashAttribute(ConstFiled.OPTION_FAILED_FAILED,"抱歉，操作失败！");
        }else {
            // 新增成功
            attributes.addFlashAttribute(ConstFiled.OPTION_FAILED_SUCCESS,"恭喜，操作成功！");
        }
        return "redirect:/admin/types";
    }


    /**
     * 删除
     * @param id
     */

    @RequestMapping("/types/{id}/delete")
    public String deleteTypeById(
            @PathVariable("id") Long id,
            RedirectAttributes attributes){
         typeService.deleteType(id);
         // 操作成功
         attributes.addFlashAttribute(ConstFiled.OPTION_FAILED_SUCCESS,"恭喜，操作成功！");
         return "redirect:/admin/types";
    }


}
