package com.baizhi;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Test
    void contextLoads() {
        Md5Hash md5Hash = new Md5Hash("111111", "abcd", 1024);
        String s = md5Hash.toHex();
        System.out.println(s);
    }

}
