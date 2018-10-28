package com.revex.docrepo.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class UploadFileOptionsTest {

    @Test
    public void testToString() {
        UploadFileOptions diplomaWorksOptions = UploadFileOptions.builder()
                .documentType(DocumentType.DIPLOMA_WORKS)
                .groupName("353-A")
                .educationLevel("магістр")
                .disciplineName("1AAAAA")
                .studentFullName("Student1")
                .studyYear("2017-2018")
                .build();
        UploadFileOptions courseWorksOptions = UploadFileOptions.builder()
                .documentType(DocumentType.COURSE_WORKS)
                .groupName("353-A")
                .disciplineName("1AAAAA")
                .studentFullName("Student2")
                .studyYear("2017-2018")
                .build();
        UploadFileOptions otherOptions = UploadFileOptions.builder()
                .documentType(DocumentType.ORDERS)
                .groupName("353-A")
                .disciplineName("1AAAAA")
                .studentFullName("Student2")
                .studyYear("2017-2018")
                .build();

        Assert.assertEquals(diplomaWorksOptions.toString(), "Дипломні/2017-2018/магістр/353-A/Student1");
        Assert.assertEquals(courseWorksOptions.toString(), "Курсові/2017-2018/1AAAAA/353-A/Student2");
        Assert.assertEquals(otherOptions.toString(), "Накази");
    }
}