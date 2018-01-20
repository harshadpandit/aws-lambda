package com.hpandit.java.aws.lambda.contact;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;

public class ContactFormTest {

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private ContactForm contactForm;

    @Test
    public void testInvalidEmail() {
        contactForm = new ContactForm();
        contactForm.setEmail("aa@.@.cl");
        contactForm.setMessage("Some message");
        contactForm.setName("James Bond");
        contactForm.setSubject("Hi");
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testBlankEmail() {
        contactForm = new ContactForm();
        contactForm.setEmail(" ");
        contactForm.setMessage("Some message");
        contactForm.setName("James Bond");
        contactForm.setSubject("Hi");
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testBlankMessage() {
        contactForm = new ContactForm();
        contactForm.setEmail("aa@ll.nk");
        contactForm.setMessage("");
        contactForm.setName("James Bond");
        contactForm.setSubject("Hi");
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        assertFalse(violations.isEmpty());
    }

}
