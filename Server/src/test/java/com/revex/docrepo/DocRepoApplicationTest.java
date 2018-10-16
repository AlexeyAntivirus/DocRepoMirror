package com.revex.docrepo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:application-test.properties")
public class DocRepoApplicationTest {

    @Value("${docrepo.docs.filepath}")
    private String folder;

    @Test
    public void contextLoads() {
        System.out.println(folder);
        Assert.assertNotNull(folder);
    }
}
