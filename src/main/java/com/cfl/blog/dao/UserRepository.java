package com.cfl.blog.dao;

import com.cfl.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 操作User的 UserRepository
 * @author Administer
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);

}
