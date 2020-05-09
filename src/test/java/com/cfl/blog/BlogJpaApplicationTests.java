package com.cfl.blog;

import com.cfl.blog.util.BlogUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogJpaApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void md5(){
        String s = BlogUtils.md5("cfl208959");
        System.out.println(s);
    }


    @Test
    public void ddd(){

    }


}
