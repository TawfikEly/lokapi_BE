package be.lokapi.model;

import java.net.URI;
import java.util.Objects;
import be.lokapi.model.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UserMessageResponseDTO
 */
@lombok.Builder
@lombok.AllArgsConstructor

@JsonTypeName("UserMessageResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-14T03:02:17.381251+01:00[Europe/Brussels]")
public class UserMessageResponseDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private UserDTO user;

  private String message;

  /**
   * Default constructor
   * @deprecated Use {@link UserMessageResponseDTO#UserMessageResponseDTO(String)}
   */
  @Deprecated
  public UserMessageResponseDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UserMessageResponseDTO(String message) {
    this.message = message;
  }

  public UserMessageResponseDTO user(UserDTO user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  
  @Schema(name = "user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user")
  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }

  public UserMessageResponseDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @NotNull
  @Schema(name = "message", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserMessageResponseDTO userMessageResponse = (UserMessageResponseDTO) o;
    return Objects.equals(this.user, userMessageResponse.user) &&
        Objects.equals(this.message, userMessageResponse.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserMessageResponseDTO {\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

