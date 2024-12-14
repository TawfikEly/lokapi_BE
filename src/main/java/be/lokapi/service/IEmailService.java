package be.lokapi.service;

import be.lokapi.entity.User;

public interface IEmailService {

    public void sendEmail(User user, String activationToken) ;

    }
