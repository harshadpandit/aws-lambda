package com.hpandit.java.aws.lambda.contact;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class SESMailer {

    private static String SENDER_EMAIL = "harshad@hpandit.com";

    private static String RECIPIENT_EMAIL = "harshadpan@gmail.com";

    public void sendEmail(final ContactForm contactForm, final Context context) throws Exception {

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            // Replace US_WEST_2 with the AWS Region you're using for
                            // Amazon SES.
                            .withRegion(Regions.EU_WEST_1).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(RECIPIENT_EMAIL))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(enhanceMessageWithSenderDetails(contactForm))))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(contactForm.getSubject())))
                    .withSource(SENDER_EMAIL);
            context.getLogger().log("The request created is = "+ request);
            client.sendEmail(request);
            context.getLogger().log("Email sent!");
        } catch (Exception ex) {
            context.getLogger().log("The email was not sent. Error message: "
                    + ex.getMessage() + " exception is "+ ex);
            throw ex;
        } catch (Throwable t) {
            context.getLogger().log("in the throwable = "+ t);
            throw new Exception("An error occurred while trying to send an email");
        }
    }

    private String enhanceMessageWithSenderDetails(final ContactForm contactForm) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Message sent by ").append(contactForm.getName()).append(" from the email address ").append(contactForm.getEmail()).append(System.getProperty("line.separator"));
        stringBuilder.append("------------**************------------").append(" ORIGINAL Message here under").append("------------**************------------").append(System.getProperty("line.separator"))
                .append(contactForm.getMessage());
        return stringBuilder.toString();

    }
}
