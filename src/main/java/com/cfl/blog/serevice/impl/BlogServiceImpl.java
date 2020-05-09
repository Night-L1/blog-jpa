package com.cfl.blog.serevice.impl;

import com.cfl.blog.NotFoundException;
import com.cfl.blog.dao.BlogRepository;
import com.cfl.blog.pojo.Blog;
import com.cfl.blog.pojo.Type;
import com.cfl.blog.serevice.BlogService;
import com.cfl.blog.util.MarkDownUtils;
import com.cfl.blog.vo.BlogQuary;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author Administer
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> listBlogsTop(Integer size) {

        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return  blogRepository.findTop(pageable);
    }

    @Override
    public Long blogCount() {
        return blogRepository.count();
    }

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Blog getAndConvent(Long id) {

        Blog one = blogRepository.getOne(id);

        if (one == null){
            throw new  NotFoundException("博客不存在！");
        }


        Blog temp = new Blog();
        BeanUtils.copyProperties(one,temp);

        String content = temp.getContent();

        String s = MarkDownUtils.markdownToHtmlExtensions(content);

        temp.setContent(s);

        return temp;
    }

    @Transactional
    @Override
    public Page<Blog> listBlogs(Pageable pageable, BlogQuary blog) {
        return blogRepository.findAll(
                new Specification<Blog>() {
                    @Override
                    public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        // Root<Blog> root :查询的目标
                        // CriteriaQuery：查询容器
                        // CriteriaBuilder:查询条件

                        List<Predicate> predicateList = new ArrayList<>();
                        if (!"".equals(blog.getTitle())&& blog.getTitle() != null){
                            predicateList.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                        }
                        if (blog.getTypeId()!= null){
                            predicateList.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                        }
                        if(blog.isRecommend()){
                            predicateList.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                        }
                        criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                        return null;
                    }
                },pageable);
    }

    @Override
    public Page<Blog> listBlogs(Pageable pageable, Long tagId) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 表关联查询 blog 与 tags表关联
                Join join = root.join("tags");
                // 通过 tagid 关联
                return criteriaBuilder.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlogs(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.blogList(pageable);
    }

    @Override
    public Page<Blog> listBlogs(String query,Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {

        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog update(Blog blog) {

        Blog one = blogRepository.getOne(blog.getId());

        if (one==null){
            throw new NotFoundException("没找到");
        }

        BeanUtils.copyProperties(blog,one);
        one.setUpdateTime(new Date());
        return  blogRepository.save(one);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {

        List<String> years = blogRepository.findGroupYear();
        Map<String,List<Blog>> map = new LinkedHashMap<>();
        for (String year:years){
            map.put(year,blogRepository.findByYear(year));
        }
        return map;
    }
}
