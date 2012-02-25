package com.jojoart.service;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 24/02/2012
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public interface EmailService {
    
    public ClientResponse send(String returnAddress, String phone, String subject, String message);
    
}
