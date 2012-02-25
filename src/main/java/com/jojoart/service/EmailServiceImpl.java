package com.jojoart.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 24/02/2012
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${MAILGUN_API_FROM_ADDRESS}")
    private String emailFromAddress;

    @Value("${MAILGUN_API_TO_ADDRESS}")
    private String emailToAddress;

    @Value("${MAILGUN_API_URL}")
    private String emailAPIURL;

    @Value("${MAILGUN_API_KEY}")
    private String emailAPIKey;

    public ClientResponse send(String returnAddress, String phone, String subject, String message) {

        StringBuilder messageBuilder = new StringBuilder("subject: ")
                .append(subject)
                .append("\n\n")
                .append("from: ")
                .append(returnAddress)
                .append("\n\n")
                .append("phone: ")
                .append(phone)
                .append("\n\n")
                .append("message:\n")
                .append(message);

        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", emailAPIKey));
        WebResource webResource = client.resource(emailAPIURL);
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "JoIvieArts <"+emailFromAddress+">");
        formData.add("to", emailToAddress);
        formData.add("subject", "Website enquiry from Jo Ivie Arts");
        formData.add("text", messageBuilder.toString());

        return webResource
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, formData);
    }
}
