package com.cfl.blog.serevice;

import com.cfl.blog.pojo.User;

/**
 * @author Administer
 */
public interface UserService {

    /**
     * 检查用户名与密码
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username,String password);

}
