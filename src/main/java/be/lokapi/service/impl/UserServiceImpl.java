package be.lokapi.service.impl;

import be.lokapi.entity.ActivationToken;
import be.lokapi.entity.User;
import be.lokapi.enums.RolesEnum;
import be.lokapi.exceptions.EmailAlreadyExistsException;
import be.lokapi.exceptions.UsernameAlreadyExistsException;
import be.lokapi.mapping.UserMapper;
import be.lokapi.model.*;
import be.lokapi.repository.IActivationTokenRepository;
import be.lokapi.repository.IRoleRepository;
import be.lokapi.repository.IUserRepository;
import be.lokapi.service.CustomUserDetailsService;
import be.lokapi.service.IActivationTokenService;
import be.lokapi.service.IEmailService;
import be.lokapi.service.IUserService;
import be.lokapi.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService, IActivationTokenService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserMapper userMapper;


    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    private final IActivationTokenRepository activationTokenRepository;

    @Autowired
    private final IUserRepository userRepository;
    @Autowired
    private final IRoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final IEmailService emailService;

    public UserServiceImpl(UserMapper userMapper, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, CustomUserDetailsService customUserDetailsService, IActivationTokenRepository activationTokenRepository, IUserRepository userRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder, IEmailService emailService) {
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.activationTokenRepository = activationTokenRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


    @Override
    public UserDTO registerUser(RegisterUserRequest registerUserRequest) {
        // check si l'adresse email ou le username existent deja dans la db
        boolean isEmailExist =userRepository.existsByEmail(registerUserRequest.getEmail());
        if(isEmailExist){
            logger.info("L'email "+registerUserRequest.getEmail() +" existe déjà.");
            throw new EmailAlreadyExistsException("L'email "+registerUserRequest.getEmail() +" existe déjà.");
        }
        boolean isUsernameExists = userRepository.existsByUsername(registerUserRequest.getUsername());
        if(isUsernameExists){
            logger.info("Username "+registerUserRequest.getUsername() +" existe déjà.");
            throw new UsernameAlreadyExistsException("Username "+registerUserRequest.getUsername() +" existe déjà.");
        }
        // Créer un nouvel utilisateur
        User newUser = new User();
        newUser.setUsername(registerUserRequest.getUsername());
        newUser.setEmail(registerUserRequest.getEmail());
        newUser.setPassword(registerUserRequest.getPassword());
        newUser.setActive(false);
        List<RegisterUserRequestDTO.RolesEnum> roles = registerUserRequest.getRoles();
        List<RolesEnum> rolesEnum = mapperRoles(roles);
        newUser.setRoles(rolesEnum);

        UserDTO userDTO = userMapper.toDto(newUser);
        UserDTO savedUserDTO = createUser(userDTO);
        User savedUser = userMapper.toEntity(savedUserDTO);
        generateSaveAndSendToken(savedUser);

        logger.info("User "+ savedUser.getUsername() +" with "+savedUser.getEmail()+" registered successfully");

        return userDTO;

    }

    private static List<RolesEnum> mapperRoles(List<RegisterUserRequestDTO.RolesEnum> roles) {
        List<RolesEnum> rolesEnum = new ArrayList<>();

        for (RegisterUserRequestDTO.RolesEnum role:roles) {
            String r = role.getValue();
            if(r.equals("TENANT"))
                rolesEnum.add(RolesEnum.TENANT);
            else
                rolesEnum.add(RolesEnum.OWNER);

        }
        return rolesEnum;
    }

    @Override
    public  void generateSaveAndSendToken(User user) {
        // Généré et sauvegarder le token d'activation
        String activationToken = generateActivationToken();
        ActivationToken token = new ActivationToken();

        token.setToken(activationToken);
        token.setUser(user);
        token.setExpirationDate(LocalDate.from(LocalDateTime.now().plusMinutes(15))); // Expire après 15 minutes
        activationTokenRepository.save(token);

        // Envoyer l'email d'activation
        emailService.sendEmail(user, activationToken);
    }

    private String generateActivationToken() {
        return UUID.randomUUID().toString();
    }




    @Override
    public AuthToken authenticateUser(AuthenticateUserRequest authenticateUserRequest) {
        //permet de se logger avec le username ou l'email
        try {
            String userName = this.findByIdentifier(authenticateUserRequest.getUsername()).get().getUsername();
            authenticateUserRequest.setUsername(userName);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticateUserRequest.getUsername(), authenticateUserRequest.getPassword());

            Authentication authentication = null;

            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("L'dentifiant est incorrect, veuillez réessayer.");
        } catch (BadCredentialsException e) {
            // Gérer les mauvais identifiants
            throw new BadCredentialsException("Les identifiants sont incorrects, veuillez réessayer.");
        } catch (DisabledException e) {
            // Gérer le cas où l'utilisateur est désactivé
            throw new DisabledException("Votre compte est désactivé. Veuillez contacter l'administrateur.");

        } catch (NullPointerException e) {// le findByIdentifier returne null car rien trouvé
        // Gérer le cas où l'utilisateur est désactivé
            throw new UsernameNotFoundException("L'dentifiant est incorrect, veuillez réessayer.");
        }

        /* } catch (Exception e) {
            // Gérer les autres types d'exception
            throw new RuntimeException("Erreur lors de l'authentification. Veuillez réessayer.");
        }*/


        try {
            // Charger l'utilisateur après l'authentification réussie
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticateUserRequest.getUsername());
            String jwt = jwtTokenUtil.generationToken(userDetails);
            logger.debug(userDetails.getUsername()+" is logged at "+ System.currentTimeMillis());

            //a verifier le roles si bespon ici en fin
            UserDTO userDTO = new UserDTO(null,authenticateUserRequest.getUsername(),authenticateUserRequest.getEmail(),authenticateUserRequest.getPassword(),null);

            return new AuthToken(jwt,userDetails.getUsername()+" is logged at "+ System.currentTimeMillis(),userDTO);
        } catch (UsernameNotFoundException e) {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            throw new RuntimeException("Utilisateur non trouvé avec ce nom d'utilisateur.");
        } catch (Exception e) {
            // Gérer les autres types d'erreurs
            throw new RuntimeException("Une erreur est survenue lors de la génération du token.");
        }
    }
    @Override
    public UserDTO createUser(UserDTO newUserDTO) {
        newUserDTO.setCreationDate(LocalDate.now());
        newUserDTO.setUpdateDate(newUserDTO.getCreationDate());
        newUserDTO.setDeleteDate(null);
        newUserDTO.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));

        User user = userMapper.toEntity(newUserDTO);
        User savedUser = userRepository.save(user);
        UserDTO savedUserDTO = userMapper.toDto(savedUser);



        return savedUserDTO;
    }



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO  getUserById(Long userId) {
        User user  = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with ID: " + userId    ));
        return  userMapper.toDto(user);

    }


    @Override
    public Optional<UserDTO> findByIdentifier(String identifier) {
        Optional<User> user = userRepository.findByUsernameOrEmail(identifier);
        if(!user.isEmpty()) {
            UserDTO userDTO = userMapper.toDto(user.get());
            return Optional.ofNullable(userDTO);
        }
        return null;

    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if(!user.isEmpty()) {
            return userMapper.toDto(user.get());
        }
        return null;
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        return userRepository.findUserByName(userName);
    }

    @Override
    public User updateUserById(Long userId) {
        UserDTO userDTO = getUserById(userId);
        return updateUser(userDTO);
    }


    @Override
    public User updateUser(UserDTO userDTO) {
        userDTO.setUpdateDate(LocalDate.now());
        User user = userMapper.toEntity(userDTO);

        return userRepository.save(user);
    }

    @Override
    public User deleteUserById(Long userId) {
        UserDTO userDTO = getUserById(userId);
        return deleteUser(userDTO);
    }

    @Override
    public User deleteUser(UserDTO userDTO) {
        userDTO.setUpdateDate(LocalDate.now());
        userDTO.setDeleteDate(LocalDate.now());

        User user = userMapper.toEntity(userDTO);

        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);

    }

    @Override
    public void activateUser(String token) {
        ActivationToken activationToken = (ActivationToken) activationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token invalide"));

        User user = activationToken.getUser();
        if (activationToken.getExpirationDate().isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
            throw new IllegalArgumentException("Token expired");
        }

        activeUser(user);

        activationTokenRepository.delete(activationToken); // Supprimer le token une fois utilisé

    }

    @Override
    public boolean isUSerActivated(User user) {
        return user.getActive();
    }

    @Override
    public User activeUser(User user) {
        user.setActive(true);
        user.setDeleteDate(null);
        user.setUpdateDate(LocalDate.now());

        return userRepository.save(user);
    }

    @Override
    public User desactiveUser(User user) {
        user.setActive(false);
        user.setDeleteDate(LocalDate.now());
        user.setUpdateDate(LocalDate.now());
        return user;
    }

    @Override
    public Optional<ActivationToken> findByToken(String token) {
        return activationTokenRepository.findByToken(token);
    }

    @Override
    public boolean isTokenExpired(ActivationToken activationToken) {
        return activationToken.getExpirationDate().isBefore(LocalDate.now());
    }
}
