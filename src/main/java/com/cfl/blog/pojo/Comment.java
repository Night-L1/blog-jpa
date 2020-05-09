package com.cfl.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administer
 * 评论实体类
 */
@Entity
@Table(name = "t_comment")
public class Comment {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> remplyComments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;


    public List<Comment> getRemplyComments() {
        return remplyComments;
    }

    public Comment setRemplyComments(List<Comment> remplyComments) {
        this.remplyComments = remplyComments;
        return this;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public Comment setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
        return this;
    }

    public Blog getBlog() {
        return blog;
    }

    public Comment setBlog(Blog blog) {
        this.blog = blog;
        return this;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public Comment setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Comment setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public Comment setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Comment setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
