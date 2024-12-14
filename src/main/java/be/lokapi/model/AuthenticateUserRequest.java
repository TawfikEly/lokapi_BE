package be.lokapi.model;

public class AuthenticateUserRequest extends AuthenticateUserRequestDTO {
    public AuthenticateUserRequest(String username, String email,String password) {
        super(username,email, password);
    }

    public static AuthenticateUserRequest fromDTO(AuthenticateUserRequestDTO dto) {
        return new AuthenticateUserRequest(dto.getUsername(), dto.getEmail(),dto.getPassword());
    }
}
