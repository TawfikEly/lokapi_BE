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
 * AuthTokenDTO
 */
@lombok.Builder
@lombok.AllArgsConstructor

@JsonTypeName("AuthToken")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-14T03:02:17.381251+01:00[Europe/Brussels]")
public class AuthTokenDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String token;

  private String message;

  private UserDTO user;

  public AuthTokenDTO token(String token) {
    this.token = token;
    return this;
  }

  /**
   * JWT Token
   * @return token
  */
  
  @Schema(name = "token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.e...", description = "JWT Token", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public AuthTokenDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Message comportement
   * @return message
  */
  
  @Schema(name = "message", example = "message d'information", description = "Message comportement", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public AuthTokenDTO user(UserDTO user) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthTokenDTO authToken = (AuthTokenDTO) o;
    return Objects.equals(this.token, authToken.token) &&
        Objects.equals(this.message, authToken.message) &&
        Objects.equals(this.user, authToken.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, message, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthTokenDTO {\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

