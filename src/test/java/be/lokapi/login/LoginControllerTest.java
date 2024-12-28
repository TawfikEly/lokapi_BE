package be.lokapi.login;

/*

import be.lokapi.entity.User;
import be.lokapi.model.AuthToken;
import be.lokapi.model.AuthTokenDTO;
import be.lokapi.model.AuthenticateUserRequestDTO;
import be.lokapi.model.UserDTO;
import be.lokapi.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;

@WebMvcTest(LoginControllerTest.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Test Connexion réussie")
    void testSuccessfulLogin() throws Exception {
        AuthenticateUserRequestDTO loginRequest = new AuthenticateUserRequestDTO("owner", "owner@owner.owner","owner");

        UserDTO user = new UserDTO();
        user.setUsername("owner");
        user.setEmail("john@example.com");

        AuthTokenDTO tokenResponse = new AuthTokenDTO("jwt-token-example", "Connexion réussie", user);

        when(userService.authenticateUser(Mockito.any())).thenReturn((AuthToken) tokenResponse);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token-example"))
                .andExpect(jsonPath("$.message").value("Connexion réussie"));
    }

    @Test
    @DisplayName("Test Nom d'utilisateur incorrect")
    void testInvalidUsername() throws Exception {
        AuthenticateUserRequestDTO loginRequest = new AuthenticateUserRequestDTO("invalid_user","", "password123");

        when(userService.authenticateUser(Mockito.any()))
                .thenThrow(new RuntimeException("Utilisateur non trouvé avec ce nom d'utilisateur."));

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Utilisateur non trouvé avec ce nom d'utilisateur."));
    }

    @Test
    @DisplayName("Test Mot de passe incorrect")
    void testInvalidPassword() throws Exception {
        AuthenticateUserRequestDTO loginRequest = new AuthenticateUserRequestDTO("john_doe","", "wrongpassword");

        when(userService.authenticateUser(Mockito.any()))
                .thenThrow(new RuntimeException("Mot de passe incorrect."));

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Mot de passe incorrect."));
    }

    @Test
    @DisplayName("Test Compte non activé")
    void testInactiveAccount() throws Exception {
        AuthenticateUserRequestDTO loginRequest = new AuthenticateUserRequestDTO("john_doe", "","password123");

        AuthTokenDTO tokenResponse = new AuthTokenDTO("", "Votre compte n'est pas activé. Vérifiez vos mails et activez votre compte.", null);

        when(userService.authenticateUser(Mockito.any())).thenReturn((AuthToken) tokenResponse);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Votre compte n'est pas activé. Vérifiez vos mails et activez votre compte."));
    }

    @Test
    @DisplayName("Test Erreur serveur")
    void testInternalServerError() throws Exception {
        AuthenticateUserRequestDTO loginRequest = new AuthenticateUserRequestDTO("john_doe","","password123");

        when(userService.authenticateUser(Mockito.any()))
                .thenThrow(new RuntimeException("Erreur serveur"));

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Erreur lors de l'authentification. Veuillez réessayer plus tard."));
    }

}
*/