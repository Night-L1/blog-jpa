package com.cfl.blog.dao;

import com.cfl.blog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Administer
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

    /**
     * 根据名字查询分类
     * @param name
     * @return
     */
    Type findByName(String name);

    @Query("select t from Type t")
    List<Type>  findTop(Pageable pageable);

}
