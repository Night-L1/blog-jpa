package com.cfl.blog.pojo;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administer
 * 标签实体类
 */
@Entity
@Table(name = "t_tag")
@Proxy(lazy = false)
public class Tag {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;


    /**
     * 标签名
     */
    private String name;

    /**
     * 多对多关系：一个tag可以有多个blog
     */
    @ManyToMany(mappedBy = "tags",fetch = FetchType.EAGER)
    private List<Blog> blogs = new ArrayList<>();


    public List<Blog> getBlogs() {
        return blogs;
    }

    public Tag setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
        return this;
    }

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public Tag setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
