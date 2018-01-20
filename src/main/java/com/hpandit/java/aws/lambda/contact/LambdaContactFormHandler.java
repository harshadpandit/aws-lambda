package com.hpandit.java.aws.lambda.contact;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.validation.Valid;

import static java.util.Objects.requireNonNull;

public class LambdaContactFormHandler implements RequestHandler<ContactForm, String> {

    private static final String MESSAGE = "Thanks for your message!";

    private SESMailer sesMailer;

    public LambdaContactFormHandler(final SESMailer sesMailer) {
        this.sesMailer = sesMailer;
    }

    public String handleRequest(final @Valid ContactForm contactForm, final Context context) {
        requireNonNull(contactForm, "ContactForm can not be null");
        context.getLogger().log("Contact Form filled by "+contactForm.getName());
        sesMailer.sendEmail(contactForm, context);

        return MESSAGE;
    }
}
