package com.cfl.blog.controller.admin;

import com.cfl.blog.pojo.User;
import com.cfl.blog.serevice.*;
import com.cfl.blog.util.ConstFiled;
import com.cfl.blog.vo.CountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Administer
 *
 * 后台管理登录控制器
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AboutService aboutService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }


    @GetMapping("/index")
    public String welcome(Model model){

        Long blogCount = blogService.blogCount();
        Long tagsCount = tagService.getTagsCount();
        Long typesCount = typeService.getTypesCount();
        CountVo countVo = new CountVo();
        countVo.setBlogCount(blogCount).setTagsCount(tagsCount).setTypesCount(typesCount);
        model.addAttribute("count",countVo);
        return "admin/index";
    }



    /**
     * 登录功能
     * @param username 登录名
     * @param password 密码
     * @param session  session域
     * @param attributes 重定向携参
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes,
                        Model model,
                        HttpServletRequest request){
        User user = userService.checkUser(username, password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute(ConstFiled.USER_INFO,user);
            Long blogCount = blogService.blogCount();
            Long tagsCount = tagService.getTagsCount();
            Long typesCount = typeService.getTypesCount();

            CountVo countVo = new CountVo();
            countVo.setBlogCount(blogCount).setTagsCount(tagsCount).setTypesCount(typesCount);
            model.addAttribute("count",countVo);
            model.addAttribute("about",aboutService.getAbouts().get(0));
            return "admin/index";
        }else {
            attributes.addFlashAttribute(ConstFiled.LOGIN_MESSAGE,"用户名密码不匹配！");
            return "redirect:/admin";
        }
    }


    /**
     * 退出功能
     * @param session session域
     * @return
     */
    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute(ConstFiled.USER_INFO);
        return "redirect:/admin";
    }

}
