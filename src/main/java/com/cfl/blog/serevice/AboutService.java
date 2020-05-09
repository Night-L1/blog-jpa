package com.cfl.blog.serevice;

import com.cfl.blog.pojo.About;

import java.util.List;

/**
 * @author Administer
 */
public interface AboutService {

    List<About> getAbouts();

    void delete();

    About save(About about);

    About update(About about);

}
