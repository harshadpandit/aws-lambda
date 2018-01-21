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

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private ContactForm contactForm;

    @Test
    public void testInvalidEmail() {
        contactForm = buildContactForm("James Bond", "hi", "aa@.@.cl","Some message");
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testBlankEmail() {
        contactForm = buildContactForm("James Bond", "hi", " ", "Some message");
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testBlankMessage() {
        contactForm = buildContactForm("James Bond", "hi", "aa@kk.cl","");
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testBlankSubject() {
        contactForm = buildContactForm("James Bond", "", "aa@kk.cl", "Some message");
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testNullSubject() {
        contactForm = buildContactForm("James Bond",  null, "aa@kk.cl","Some message");
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testBlankName() {
        contactForm = buildContactForm("", null, "aa@kk.cl", "Some message");
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        assertFalse(violations.isEmpty());
    }

    private ContactForm buildContactForm(String name, String subject,
                                         String email, String message) {
        ContactForm contactForm = new ContactForm();
        contactForm.setMessage(message);
        contactForm.setSubject(subject);
        contactForm.setEmail(email);
        contactForm.setName(name);
        return contactForm;
    }
}
