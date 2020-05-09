package com.cfl.blog.serevice;

import com.cfl.blog.pojo.Blog;
import com.cfl.blog.vo.BlogQuary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Administer
 */
public interface BlogService {


    List<Blog> listBlogsTop(Integer size);

    /**
     * 获取博客数量
     */
    Long blogCount();


    /**
     * 根据id 获取
     * @param id
     * @return
     */
    Blog getBlog(Long id);


    Blog getAndConvent(Long id);

    /**
     * 分页查询
     * @param pageable
     * @param blog
     * @return
     */
    Page<Blog> listBlogs(Pageable pageable, BlogQuary blog);


    Page<Blog> listBlogs(Pageable pageable, Long tagId);

    Page<Blog> listBlogs(Pageable pageable);
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlogs(String query,Pageable pageable);
    /**
     * 新增
     * @param blog
     * @return
     */

    Blog saveBlog(Blog blog);

    /**
     * 更新
     * @param blog
     * @return
     */
    Blog update(Blog blog);

    /**
     * 删除
     * @param id
     */
    void deleteBlog(Long id);

    Map<String,List<Blog>> archiveBlog();

}
