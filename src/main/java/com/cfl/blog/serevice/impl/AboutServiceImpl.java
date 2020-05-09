package com.cfl.blog.serevice.impl;

import com.cfl.blog.dao.AboutRepository;
import com.cfl.blog.pojo.About;
import com.cfl.blog.serevice.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administer
 */

@Service
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutRepository aboutRepository;


    @Override
    public List<About> getAbouts() {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return aboutRepository.findAll(sort);
    }

    @Override
    public void delete() {
    }

    @Override
    public About save(About about) {
        return aboutRepository.save(about);
    }

    @Override
    public About update(About about) {
        return null;
    }
}
