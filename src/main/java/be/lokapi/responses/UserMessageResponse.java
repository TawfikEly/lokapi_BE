package be.lokapi.responses;

import be.lokapi.entity.User;
import be.lokapi.model.UserDTO;
import be.lokapi.model.UserMessageResponseDTO;

public class UserMessageResponse<T> extends UserMessageResponseDTO {
    private T user;
    private String message;

    public UserMessageResponse(T user, String message) {
        super(message);
        this.user = user;

    }

    public UserMessageResponse(UserDTO user, String message) {
        super(user, message);
    }

    // Méthode pour créer un MessageResponses à partir d'un DTO
    public static UserMessageResponse fromDTO(UserMessageResponseDTO dto) {
        return new UserMessageResponse(dto.getUser(), dto.getMessage());
    }
}
