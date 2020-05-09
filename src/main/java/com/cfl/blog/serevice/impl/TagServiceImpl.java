package com.cfl.blog.serevice.impl;

import com.cfl.blog.dao.TagRepository;
import com.cfl.blog.pojo.Tag;
import com.cfl.blog.serevice.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administer
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;


    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    @Override
    public Long getTagsCount() {
        return tagRepository.count();
    }

    @Override
    public List<Tag> listTages(String id) {
        return tagRepository.findAllById(convertToList(id));
    }

    /**
     * 将 “1,2,3,4,5,6”格式字符串转换成list
     * @param ids
     * @return
     */
    private  List<Long>  convertToList(String ids){

        List<Long> list = new ArrayList<>();

        if (!"".equals(ids) && ids != null){
            String[] idArr= ids.split(",");
            for (int i=0;i<idArr.length;i++){
                list.add(new Long(idArr[i]));
            }
        }
        return list;

    }

    @Transactional
    @Override
    public List<Tag> getTages() {

        // 根据id倒序排列
        return tagRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Transactional
    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public void delTagById(Long id) {
        tagRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Tag findTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
