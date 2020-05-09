package com.cfl.blog.dao;

import com.cfl.blog.pojo.Tag;
import com.cfl.blog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Administer
 */
public interface TagRepository  extends JpaRepository<Tag,Long> {

    /**
     * 根据标签名字查询
     * @param name
     * @return
     */
    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

}

