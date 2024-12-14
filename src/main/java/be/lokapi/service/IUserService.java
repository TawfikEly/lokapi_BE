package be.lokapi.service;

import be.lokapi.entity.User;
import be.lokapi.model.AuthToken;
import be.lokapi.model.AuthenticateUserRequest;
import be.lokapi.model.RegisterUserRequest;
import be.lokapi.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {


    UserDTO registerUser(RegisterUserRequest registerUserRequest);
    AuthToken authenticateUser(AuthenticateUserRequest authenticateUserRequest);

    UserDTO createUser(UserDTO userDTO);

    User activeUser(User user);
    void activateUser(String token);
    boolean isUSerActivated(User user);

    User desactiveUser(User user);
    List<User> getAllUsers();
    UserDTO getUserById(Long userId);
    Optional<UserDTO> findByIdentifier(String identifier);
    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByName(String userName);
    User updateUserById(Long userId);
    User updateUser(UserDTO userDTO);
    User deleteUserById(Long userId);
    User deleteUser(UserDTO userDTO);
    //boolean verifyPassword(String email,String password);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);


}
