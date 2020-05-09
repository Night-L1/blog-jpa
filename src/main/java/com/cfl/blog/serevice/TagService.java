package com.cfl.blog.serevice;

import com.cfl.blog.pojo.Tag;
import com.cfl.blog.pojo.Type;

import java.util.List;

/**
 * @author Administer
 * @date 2020-04-25
 */
public interface TagService {


    List<Tag> listTagTop(Integer size);

    Long getTagsCount();

    List<Tag> listTages(String id);


    /**
     * 获取所有标签
     * @return
     */
    List<Tag> getTages();

    /**
     * 新增一个标签
     * @param tag
     * @return
     */
    Tag addTag(Tag tag);


    /**
     * 删除一个标签
     * @param id
     */
    void delTagById(Long id);

    /**
     * 根据标签内容查询
     * @param name
     * @return
     */
    Tag findTagByName(String name);



}
