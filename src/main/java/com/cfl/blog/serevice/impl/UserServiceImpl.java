package com.cfl.blog.serevice.impl;

import com.cfl.blog.dao.UserRepository;
import com.cfl.blog.pojo.User;
import com.cfl.blog.serevice.UserService;
import com.cfl.blog.util.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administer
 * UserServcie的实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {

        String pwd = BlogUtils.md5(password);

        User user = userRepository.findByUsernameAndPassword(username,pwd);

        return user;
    }
}
