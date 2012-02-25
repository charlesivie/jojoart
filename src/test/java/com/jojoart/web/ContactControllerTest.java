package com.jojoart.web;

import com.jojoart.service.EmailService;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 25/02/2012
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @InjectMocks
    private ContactController contactController = new ContactController();

    @Mock
    private EmailService mockEmailService;
    @Mock
    private ClientResponse mockClientResponse;

    @Test
    public void get_contact_should_load_view() throws Exception {
        assertTrue(contactController.getContact().getViewName().equalsIgnoreCase("contact/index"));
    }

    @Test
    public void makeContact_should_send_correct_message() throws Exception {

        when(mockClientResponse.getStatus()).thenReturn(200);
        when(mockEmailService.send("return", "123", "subject", "message")).thenReturn(mockClientResponse);
        ModelAndView modelAndView = contactController.makeContact("return", "123", "subject", "message");
        verify(mockEmailService).send("return", "123", "subject", "message");
    }

    @Test
    public void makeContact_should_return_success_true() throws Exception {

        when(mockClientResponse.getStatus()).thenReturn(200);
        when(mockEmailService.send("return", "123", "subject", "message")).thenReturn(mockClientResponse);

        ModelAndView modelAndView = contactController.makeContact("return", "123", "subject", "message");
        assertEquals(true, modelAndView.getModel().get("success"));
    }

    @Test
    public void makeContact_should_return_success_false() throws Exception {

        when(mockClientResponse.getStatus()).thenReturn(404);
        when(mockEmailService.send("return", "123", "subject", "message")).thenReturn(mockClientResponse);

        ModelAndView modelAndView = contactController.makeContact("return", "123", "subject", "message");
        assertEquals(false, modelAndView.getModel().get("success"));
    }
    
}
