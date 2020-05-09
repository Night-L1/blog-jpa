package com.cfl.blog.serevice.impl;

import com.cfl.blog.NotFoundException;
import com.cfl.blog.dao.TypeRepository;
import com.cfl.blog.pojo.Type;
import com.cfl.blog.serevice.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Administer
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Override
    public Long getTypesCount() {
        return typeRepository.count();
    }

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> listTypes(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> types() {
        return typeRepository.findAll();
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type one = typeRepository.getOne(id);
        if (one == null){
            throw new  NotFoundException("不存在");
        }
        // 对象内容复制，将type复制给one
        BeanUtils.copyProperties(type,one);
        return typeRepository.save(one);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }



    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }
}
