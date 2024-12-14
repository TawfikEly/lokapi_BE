package be.lokapi.model;

import java.util.List;

public class RegisterUserRequest extends RegisterUserRequestDTO{
    public RegisterUserRequest(String username, String email, String password,  List<RolesEnum> roles) {
        super(username, email, password, roles);
    }

    // Méthode pour créer un RegisterUserRequest à partir d'un DTO
    public static RegisterUserRequest fromDTO(RegisterUserRequestDTO dto) {
        return new RegisterUserRequest(dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getRoles());
    }
}
