package com.jojoart.web;

import com.jojoart.service.EmailService;
import com.sun.jersey.api.client.ClientResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.FormParam;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 25/02/2012
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    private static Logger LOG = Logger.getLogger(ContactController.class);
    
    @Autowired
    private EmailService emailService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getContact(){
            return new ModelAndView("contact/index");
    }

    @RequestMapping(value = "/sendmail", method = RequestMethod.GET)
    public ModelAndView makeContact(@FormParam("email") String email,
                                    @FormParam("phone") String phone,
                                    @FormParam("subject") String subject,
                                    @FormParam("message") String message)
    {
        ModelAndView modelAndView = new ModelAndView("contact/response");

        ClientResponse response = emailService.send(email, phone, subject, message);

        if(response.getStatus()!=200){
            LOG.error("Error ...\n"+ response);
        }

        modelAndView.addObject("success", response.getStatus() == 200);

        return modelAndView;

    }
    
}
