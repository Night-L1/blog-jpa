package com.cfl.blog.serevice;

import com.cfl.blog.pojo.Tag;
import com.cfl.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Administer
 *
 * 后台类型管理业务处理
 */

public interface TypeService {




    /**
     * 根据传递的值获取前多少条数据
     * @param size
     * @return
     */
    List<Type> listTypeTop(Integer size);



    Long getTypesCount();

    /**
     * 新增一个类型
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Type getType(Long id);
    /**
     * 类型的分页查询
     * @param pageable
     * @return
     */
    Page<Type> listTypes(Pageable pageable);


    List<Type> types();

    /**
     * 更新一个type
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 根据id删除type
     * @param id
     */
    void deleteType(Long id);

    /**
     * 根据名称获取分类
     * @param name
     * @return
     */
    Type getTypeByName(String name);
}
