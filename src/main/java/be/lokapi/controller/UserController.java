package be.lokapi.controller;


import be.lokapi.api.UsersApi;
import be.lokapi.entity.User;
import be.lokapi.model.ChangePasswordDTO;
import be.lokapi.model.UserDTO;
import be.lokapi.service.ILeaseService;
import be.lokapi.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        UserDTO userDTO  = userService.getUserByEmail(email);
        if (userDTO !=  null)
            return ResponseEntity.ok(userDTO);
        return ResponseEntity.notFound().build();
    }


    @Override
    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO  = userService.getUserById(userId);
        if (userDTO !=  null)
            return ResponseEntity.ok(userDTO);
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<Boolean> isUserByIdentifiantRegistered(@PathVariable String existingEmailOrUsername) {
        Optional<UserDTO> user = userService.findByIdentifier(existingEmailOrUsername);
        if (user !=  null)
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }

    @Override
    @GetMapping("/getUserByName/{username}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String username) {
        UserDTO userDTO  = userService.getUserByName(username);
        if (userDTO !=  null)
            return ResponseEntity.ok(userDTO);
        return ResponseEntity.badRequest().build();
    }

    @Override
    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO userDTOUpdated = userService.updateUser(userDTO);
        if (userDTOUpdated !=  null)
            return ResponseEntity.ok(userDTOUpdated);

        return ResponseEntity.badRequest().build();
    }

    @Override
    @PutMapping("/updateUserById/{userId}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long userId) {
        UserDTO userDTO = userService.updateUserById(userId);
        if (userDTO !=  null)
            return ResponseEntity.ok(userDTO);

        return ResponseEntity.badRequest().build();
    }


    @Override
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non authentifié.");
        }
        if (authentication instanceof AnonymousAuthenticationToken){
            throw new IllegalStateException("Utilisateur non authentifié");
        }

        String username = authentication.getName();
        boolean success = userService.changePassword(username, changePasswordDTO);

        if(success){
            return ResponseEntity.ok("Mot de passe changé avec succès.");
        }else{
            return ResponseEntity.badRequest().body("L'ancien mot de passe est incorrect.");
        }
    }


    @Override
    @PutMapping("/deleteUser/{user}")
    public ResponseEntity<UserDTO> deleteUser(@RequestBody  UserDTO userDTO) {

        User userDeleted = userService.deleteUser(userDTO);
        if (userDeleted !=  null)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    @Override
    @PutMapping("/deleteUserById/{userId}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Long userId) {
        User user = userService.deleteUserById(userId);
        if (user !=  null)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    @Override
    @GetMapping("/getTenantByOwner/{ownerId}")
    public ResponseEntity<List<UserDTO>> getTenantByOwner(@PathVariable Long ownerId) {
        List<UserDTO> userDTO =  leaseService.getTenantByOwner(ownerId);
        if (userDTO !=  null)
            return ResponseEntity.ok(userDTO);

        return ResponseEntity.notFound().build();
    }
}
