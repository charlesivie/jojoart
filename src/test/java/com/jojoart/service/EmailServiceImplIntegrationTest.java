package com.jojoart.service;

import com.sun.jersey.api.client.ClientResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.fail;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 24/02/2012
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplIntegrationTest {

    @Autowired
    private EmailService emailService = new EmailServiceImpl();

    @Before
    public void setup(){
        setInternalState(emailService, "emailFromAddress", System.getenv("MAILGUN_API_FROM_ADDRESS"));
        setInternalState(emailService, "emailToAddress", System.getenv("MAILGUN_API_TO_ADDRESS"));
        setInternalState(emailService, "emailAPIURL", System.getenv("MAILGUN_API_URL"));
        setInternalState(emailService, "emailAPIKey", System.getenv("MAILGUN_API_KEY"));
    }

    @Test
    @Ignore("email service int test")
    public void sendShouldSendMessage() throws Exception {

        ClientResponse response = emailService.send(
                "return@Email.Address",
                "123",
                "subject text "+new Date(),
                "message text"
        );
        if(response.getStatus()!=200){
            fail(response.toString());
        }

    }

}
