package com.cfl.blog.dao;

import com.cfl.blog.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Administer
 */
public interface BlogRepository  extends JpaRepository<Blog,Long> ,
        JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1 or b.description like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Query("select function('date_format',b.updateTime,'%Y') " +
            "as year from Blog b " +
            "group by function('date_format',b.updateTime,'%Y') " +
            "order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1 ")
    List<Blog> findByYear(String year);


    @Query("select b from Blog b where b.recommend=true ")
    Page<Blog> blogList(Pageable pageable);

}
