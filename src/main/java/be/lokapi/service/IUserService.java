package be.lokapi.service;

import be.lokapi.entity.User;
import be.lokapi.model.*;

import java.util.List;
import java.util.Optional;

public interface IUserService {


    UserDTO registerUser(RegisterUserRequest registerUserRequest);
    AuthToken authenticateUser(AuthenticateUserRequest authenticateUserRequest);

    UserDTO createUser(UserDTO userDTO);

    User activeUser(User user);
    void activateUser(String token);
    boolean isUSerActivated(UserDTO userDTO);

    User desactiveUser(User user);
    List<User> getAllUsers();
    UserDTO getUserById(Long userId);
    Optional<UserDTO> findByIdentifier(String identifier);
    UserDTO getUserByEmail(String email);

    UserDTO  getUserByName(String userName);
    UserDTO updateUserById(Long userId);
    UserDTO updateUser(UserDTO userDTO);
    User deleteUserById(Long userId);
    User deleteUser(UserDTO userDTO);
    //boolean verifyPassword(String email,String password);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean changePassword(String username, ChangePasswordDTO changePasswordDTO);

    UserDTO updateUserProfilePicture(Long userId, String fileUrl) ;

    }
