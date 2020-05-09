package com.cfl.blog.vo;

/**
 * @author Administer
 */
public class CountVo {

    private Long blogCount;

    private Long typesCount;

    private Long tagsCount;

    private Long commentsCount;

    public CountVo() {
    }

    public Long getBlogCount() {
        return blogCount;
    }

    public CountVo setBlogCount(Long blogCount) {
        this.blogCount = blogCount;
        return this;
    }

    public Long getTypesCount() {
        return typesCount;
    }

    public CountVo setTypesCount(Long typesCount) {
        this.typesCount = typesCount;
        return this;
    }

    public Long getTagsCount() {
        return tagsCount;
    }

    public CountVo setTagsCount(Long tagsCount) {
        this.tagsCount = tagsCount;
        return this;
    }

    public Long getCommentsCount() {
        return commentsCount;
    }

    public CountVo setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
        return this;
    }
}
