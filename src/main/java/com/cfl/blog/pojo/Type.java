package com.cfl.blog.pojo;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administer
 * 类型实体类
 */
@Entity
@Table(name = "t_type")
public class Type {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 类型名
     */
    @NotBlank(message = "分类名不能为空")
    private String name;

    /**
     * 一对多的关系:一个type可以有多个blog
     * 放弃关系维护权
     */
    @OneToMany(mappedBy = "type",fetch = FetchType.EAGER)
    private List<Blog> blogs = new ArrayList<Blog>();


    public List<Blog> getBlogs() {
        return blogs;
    }

    public Type setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
        return this;
    }

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public Type setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Type setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
