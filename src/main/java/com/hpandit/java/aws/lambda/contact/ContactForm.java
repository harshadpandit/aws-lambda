package com.hpandit.java.aws.lambda.contact;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
}
