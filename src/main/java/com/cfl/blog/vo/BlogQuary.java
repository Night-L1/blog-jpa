package com.cfl.blog.vo;

/**
 * @author Administer
 */
public class BlogQuary {

    private String title;
    private Long typeId;
    private boolean recommend;

    public BlogQuary() {
    }

    public String getTitle() {
        return title;
    }

    public BlogQuary setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getTypeId() {
        return typeId;
    }

    public BlogQuary setTypeId(Long typeId) {
        this.typeId = typeId;
        return this;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public BlogQuary setRecommend(boolean recommend) {
        this.recommend = recommend;
        return this;
    }
}
