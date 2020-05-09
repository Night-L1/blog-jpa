package com.cfl.blog.interceptor;

import com.cfl.blog.util.ConstFiled;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administer
 * 登陆拦截过滤器
 * 从session中获取用户信息，如果为空进行拦截，并重定向到登录页面
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute(ConstFiled.USER_INFO)==null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }



}
