package com.hpandit.java.aws.lambda.contact;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootStrapTest {

    @Inject
    private BootStrap sut;

    @Test
    public void testAllBeansAreCreated() {
        assertNotNull(sut);
        assertNotNull(sut.lambdaContactFormHandler());
        assertNotNull(sut.sesMailer());
    }
}
