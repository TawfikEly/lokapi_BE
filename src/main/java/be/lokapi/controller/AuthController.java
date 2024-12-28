package be.lokapi.controller;


import be.lokapi.api.AuthApi;
import be.lokapi.entity.ActivationToken;
import be.lokapi.entity.User;
import be.lokapi.exceptions.EmailAlreadyExistsException;
import be.lokapi.exceptions.UsernameAlreadyExistsException;
import be.lokapi.mapping.UserMapper;
import be.lokapi.model.*;
import be.lokapi.responses.UserMessageResponse;
import be.lokapi.service.IActivationTokenService;
import be.lokapi.service.IUserService;
import be.lokapi.utils.Constantes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController  implements AuthApi {

    private final IUserService userService;
    private final IActivationTokenService activationTokenService;
    private final UserMapper userMapper;


    public AuthController(IUserService userService, IActivationTokenService activationTokenService, UserMapper userMapper) {
        this.userService = userService;
        this.activationTokenService = activationTokenService;
        this.userMapper = userMapper;
    }
    @Override
    @PostMapping("/signup")
    public ResponseEntity<UserMessageResponseDTO> registerUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        try {
            RegisterUserRequest registerUser = RegisterUserRequest.fromDTO(registerUserRequestDTO);
            UserDTO userDTO = userService.registerUser(registerUser);

            UserMessageResponseDTO userMessageResponseDTO = new UserMessageResponse(userDTO,"User registered successfully");

            return ResponseEntity.ok(userMessageResponseDTO);

        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserMessageResponseDTO(null, "L'email existe déjà."));
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserMessageResponseDTO(null, "Le nom d'utilisateur existe déjà."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserMessageResponseDTO(null, "Une erreur est survenue lors de l'inscription."));
        }
    }


    @Override
    @GetMapping("/activate")
    public ResponseEntity<String> activateUser(@RequestParam("token") String token) {

        Optional<ActivationToken> activationToken = activationTokenService.findByToken(token);
        if (activationToken == null) {
            return ResponseEntity.badRequest().body("Token non valide");
        }

        if(activationToken.isEmpty()){
            return ResponseEntity.badRequest().body("Token déjà utilisé");
        }

        if(activationTokenService.isTokenExpired(activationToken.get())){
            //regénérer un nouveau token si expiré
            activationTokenService.generateSaveAndSendToken(activationToken.get().getUser());
            return ResponseEntity.badRequest().body("Le token a expiré. Un nouvel email a été envoyé avec un nouveau lien d'activation.");
        }

        try {

            userService.activateUser(activationToken.get().getToken());
            //return ResponseEntity.ok("Compte activé avec succès !");
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, Constantes.HTTP_ORIGIN_FRONT_CHROME+"/#/activation-success")
                    .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Token invalide ou expiré. Veuillez vérifier votre email pour un nouveau lien.");
        }

    }


    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthTokenDTO> authenticateUser(@RequestBody AuthenticateUserRequestDTO authenticateUserRequestDTO) {
        try {
            AuthToken jwt = userService.authenticateUser(AuthenticateUserRequest.fromDTO(authenticateUserRequestDTO));
            if (jwt != null) {
                Optional<User> user = userService.getUserByName(jwt.getUser().getUsername());
                if (!userService.isUSerActivated(user.get())) {
                    // Cas où l'authentification échoué (si le le user n'est pas encore actif)
                    AuthTokenDTO authTokenDTO = new AuthTokenDTO("", "Votre compte n'est pas actif verifiez vos mails et activez votre compte", null);
                    return ResponseEntity.badRequest().body(authTokenDTO);
                } else {
                    Map<String, Object> response = new HashMap<>();
                    response.put("token", jwt.getToken());
                    response.put("user", Map.of("username", user.get().getUsername(), "email", user.get().getEmail()));

                    UserDTO userDTO = new UserDTO(user.get().getId(), user.get().getUsername(), user.get().getEmail(), user.get().getPassword(), userMapper.mapRoles(user.get().getRoles()));
                    AuthTokenDTO authTokenDTO = new AuthTokenDTO(response.get("token").toString(), "User registered successfully", userDTO);
                    return ResponseEntity.ok(authTokenDTO);
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AuthTokenDTO("", "Identifiant incorrect.", null)
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AuthTokenDTO("", "Mot de passe incorrect.", null)
            );
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AuthTokenDTO("", "Votre compte est désactivé. Veuillez contacter l'administrateur.", null)
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new AuthTokenDTO("", "Identifiant incorrect.", null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new AuthTokenDTO("", "Erreur lors de l'authentification. Veuillez reessayer plus tard.", null)
            );
        }
    }

    @Override
    @GetMapping("/infoUser/{userId}")
    public ResponseEntity<UserDTO> infoUser(@PathVariable("userId") Long userId) {

            UserDTO userDTO = userService.getUserById(userId);

        if(userDTO != null){
            return ResponseEntity.ok().body(userDTO);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }




}
