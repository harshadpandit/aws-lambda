package com.hpandit.java.aws.lambda.contact;

import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Domain class for the contact form.
 */

@Data
public class ContactForm {

    @NotBlank
    private String name;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String subject;
    @NotBlank
    private String message;

    private static Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    public boolean isValid() {
        Set<ConstraintViolation<ContactForm>> constraintViolations = getValidator().validate( this );
        return constraintViolations.isEmpty() ? true: false;
    }
}
