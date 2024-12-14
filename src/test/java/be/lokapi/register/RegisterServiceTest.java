package be.lokapi.register;

import be.lokapi.entity.User;
import be.lokapi.enums.RolesEnum;
import be.lokapi.exceptions.EmailAlreadyExistsException;
import be.lokapi.exceptions.UsernameAlreadyExistsException;
import be.lokapi.model.RegisterUserRequest;
import be.lokapi.model.RegisterUserRequestDTO;
import be.lokapi.repository.IActivationTokenRepository;
import be.lokapi.repository.IUserRepository;
import be.lokapi.service.impl.EmailServiceImpl;
import be.lokapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;


public class RegisterServiceTest {

    private final static String EMAIL_ADRESS= "email@example.com";
    private final static String USERNAME= "username";


    @Mock
    private IUserRepository userRepository;
    @Mock
    private IActivationTokenRepository activationTokenRepository;
    @Mock
    private EmailServiceImpl emailService;

    @Mock
    private PasswordEncoder passwordEncoder; // Mock du PasswordEncoder

    @InjectMocks
    private UserServiceImpl userService; // Remplacez par le nom de votre service d'utilisateur

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




}
