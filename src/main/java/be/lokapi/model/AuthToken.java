package be.lokapi.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthToken extends AuthTokenDTO {
    public AuthToken(String token,String message, UserDTO userDTO) {
        super(token,message,userDTO);
    }
}
