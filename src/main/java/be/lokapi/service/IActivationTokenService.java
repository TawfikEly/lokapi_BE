package be.lokapi.service;

import be.lokapi.entity.ActivationToken;
import be.lokapi.entity.User;

import java.util.Optional;

public interface IActivationTokenService {

    void generateSaveAndSendToken(User newUser);

    Optional<ActivationToken> findByToken(String token);
    public boolean isTokenExpired(ActivationToken activationToken) ;


}
