package be.lokapi.controller;


import be.lokapi.api.UsersApi;
import be.lokapi.entity.User;
import be.lokapi.model.UserDTO;
import be.lokapi.service.ILeaseService;
import be.lokapi.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController implements UsersApi {

    private final IUserService userService;
    private final ILeaseService leaseService;


    public UserController(IUserService userService, ILeaseService leaseService) {
        this.userService = userService;
        this.leaseService = leaseService;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUserDTO = userService.createUser(userDTO);
        if(savedUserDTO != null)
            return ResponseEntity.ok(savedUserDTO);
        return ResponseEntity.badRequest().build() ;
    }



    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return (ResponseEntity<List<UserDTO>>) userService.getAllUsers();
    }

    @Override
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        //Optional<User> user =  userService.findByIdentifier(email);
        Optional<User> user  = userService.getUserByEmail(email);
        if (user !=  null)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }


    @Override
    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDTO> getUserById(Long userId) {
        UserDTO userDTO  = userService.getUserById(userId);

        if (userDTO !=  null)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @Override
    @GetMapping("/getUserByIdentifiant/{emailOrUsername}")
    public ResponseEntity<UserDTO> getUserByIdentifiant(@PathVariable String emailOrUsername) {
        try {
            Optional<UserDTO> user = userService.findByIdentifier(emailOrUsername);
            if (user !=  null)
                return ResponseEntity.ok(user.get());
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    @GetMapping("/isUserByIdentifiantRegistered/{existingEmailOrUsername}")
    public ResponseEntity<Boolean> isUserByIdentifiantRegistered(String existingEmailOrUsername) {
        Optional<UserDTO> user = userService.findByIdentifier(existingEmailOrUsername);
        if (user !=  null)
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }

    @Override
    @GetMapping("/getUserByName/{username}")
    public ResponseEntity<UserDTO> getUserByName(String username) {
        Optional<User> user  = userService.getUserByName(username);
        if (user !=  null)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }




    @Override
    @PutMapping("/updateUser/{user}")
    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) {
        User user = userService.updateUser(userDTO);
        if (user !=  null)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    @Override
    @PutMapping("/updateUserById/{userId}")
    public ResponseEntity<UserDTO> updateUserById(Long userId) {
        User user = userService.updateUserById(userId);
        if (user !=  null)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }


    @Override
    @PutMapping("/deleteUser/{user}")
    public ResponseEntity<UserDTO> deleteUser(UserDTO userDTO) {

        User userDeleted = userService.deleteUser(userDTO);
        if (userDeleted !=  null)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    @Override
    @PutMapping("/deleteUserById/{userId}")
    public ResponseEntity<UserDTO> deleteUserById(Long userId) {
        User user = userService.deleteUserById(userId);
        if (user !=  null)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    @Override
    @GetMapping("/getTenantByOwner/{ownerId}")
    public ResponseEntity<List<UserDTO>> getTenantByOwner(Long ownerId) {
        List<UserDTO> userDTO =  leaseService.getTenantByOwner(ownerId);
        if (userDTO !=  null)
            return ResponseEntity.ok(userDTO);

        return ResponseEntity.notFound().build();
    }
}
