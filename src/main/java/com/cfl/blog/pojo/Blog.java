package com.cfl.blog.pojo;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administer
 * blog 实体类
 */
@Entity
@Table(name = "t_blog")
@Proxy(lazy = false)
public class Blog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 标题
     */
    private String title;


    private String description;

    public String getDescription() {
        return description;
    }

    public Blog setDescription(String description) {
        this.description = description;
        return this;
    }

    @Transient
    private String tagsIds;


    public void initTags(){
        this.tagsIds = tagsToIds(this.getTags());
    }


    private String tagsToIds(List<Tag> tags){
        StringBuilder ids = new StringBuilder();
        if (!tags.isEmpty()){
            boolean flag = false;
            for (Tag tag:tags){
                if (flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
        }
        return ids.toString();
    }





    public String getTagsIds() {
        return tagsIds;
    }

    public Blog setTagsIds(String tagsIds) {
        this.tagsIds = tagsIds;
        return this;
    }

    /**
     * 内容
     * Lob:大数据字段
     * Basic(fetch = FetchType.LAZY)：懒加载
     */
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;

    /**
     * 首图
     */
    private String fitsrPicture;

    /**
     * 标记
     */
    private String flag;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 赞赏开启
     */
    private boolean appreciation;

    /**
     * 能否分享
     */
    private boolean shareStatment;


    /**
     *  能否评论
     */
    private boolean commentabled;


    /**
     *  是否发布
     */
    private boolean published;


    /**
     *  是否推荐
     */
    private boolean recommend;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;


    /**
     * 配置多对一关系：一个blog多个type
     * 此为多的一方，由其来维护关系
     * 即：多的一方来维护关系
     */
    @ManyToOne
    private Type type;

    /**
     * 多对多关系：一个blog可以有多个tag
     * 设置级联关系：
     */
    @ManyToMany(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();


    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();




    public List<Comment> getComments() {
        return comments;
    }

    public Blog setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Blog setUser(User user) {
        this.user = user;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Blog setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Blog setType(Type type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", fitsrPicture='" + fitsrPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatment=" + shareStatment +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Blog setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Blog setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Blog setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Blog setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Blog setContent(String content) {
        this.content = content;
        return this;
    }

    public String getFitsrPicture() {
        return fitsrPicture;
    }

    public Blog setFitsrPicture(String fitsrPicture) {
        this.fitsrPicture = fitsrPicture;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public Blog setFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public Integer getViews() {
        return views;
    }

    public Blog setViews(Integer views) {
        this.views = views;
        return this;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public Blog setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
        return this;
    }

    public boolean isShareStatment() {
        return shareStatment;
    }

    public Blog setShareStatment(boolean shareStatment) {
        this.shareStatment = shareStatment;
        return this;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public Blog setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
        return this;
    }

    public boolean isPublished() {
        return published;
    }

    public Blog setPublished(boolean published) {
        this.published = published;
        return this;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public Blog setRecommend(boolean recommend) {
        this.recommend = recommend;
        return this;
    }
}
