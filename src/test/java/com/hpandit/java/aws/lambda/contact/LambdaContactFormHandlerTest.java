package com.hpandit.java.aws.lambda.contact;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LambdaContactFormHandlerTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Mock
    private SESMailer sesMailer;

    @InjectMocks
    private LambdaContactFormHandler sut;

    @Test
    public void testInvalidContactForm() {
        Context context = mock(Context.class);
        when(context.getLogger()).thenReturn(getLogger());
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(createInValidContactForm());

        sut.handleRequest(createInValidContactForm(), context);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testValidContactForm() {
        Context context = mock(Context.class);
        when(context.getLogger()).thenReturn(getLogger());
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(createValidContactForm());

        sut.handleRequest(createInValidContactForm(), context);
        assertTrue(violations.isEmpty());
    }

    private ContactForm createInValidContactForm() {
        final ContactForm contactForm = new ContactForm();
        contactForm.setName("Unit test");
        contactForm.setEmail("test@test@.nl");
        contactForm.setSubject("Subject");
        contactForm.setMessage("A long message");
        return contactForm;
    }

    private ContactForm createValidContactForm() {
        final ContactForm contactForm = new ContactForm();
        contactForm.setName("Unit test");
        contactForm.setEmail("test@test.nl");
        contactForm.setSubject("Subject");
        contactForm.setMessage("A long message");
        return contactForm;
    }

    private LambdaLogger getLogger() {
        return new LambdaLogger() {
            @Override
            public void log(String message) {
                System.out.println("In the test " + message);
            }

            @Override
            public void log(byte[] message) {

            }
        };
    }


}


