package com.cfl.blog.dao;

import com.cfl.blog.pojo.About;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administer
 */
public interface AboutRepository  extends JpaRepository<About,Long> {
}
