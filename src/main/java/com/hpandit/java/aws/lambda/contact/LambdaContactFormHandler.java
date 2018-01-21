package com.hpandit.java.aws.lambda.contact;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import javax.validation.Valid;

import static java.util.Objects.requireNonNull;

public class LambdaContactFormHandler implements RequestHandler<ContactForm, String> {

    private static final String MESSAGE = "Thanks for your message!";

    @Inject
    private SESMailer sesMailer;


    @Override
    public String handleRequest(final @Valid ContactForm contactForm, final Context context) {
        requireNonNull(contactForm, "ContactForm can not be null");
        context.getLogger().log("Contact Form filled by "+contactForm.getName());
        try {
            sesMailer = new SESMailer();
            sesMailer.sendEmail(contactForm, context);
        } catch (Exception e) {
            context.getLogger().log("An exception occurred while trying to send the email. The details are" +contactForm.toString());
        }
        return MESSAGE;
    }
}
