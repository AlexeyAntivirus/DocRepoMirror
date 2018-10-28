package com.revex.docrepo.services;

import com.revex.docrepo.DocRepoApplication;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class DocumentUploadingServiceTest {
//
//    @Autowired
//    private DocumentUploadingService service;

    @BeforeClass
    public static void a() {
        System.out.println(System.getProperty("java.classpath"));
    }

    @Test
    public void test() {

//        Assert.assertNotNull(service);
    }
}