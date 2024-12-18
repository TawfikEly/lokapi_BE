package be.lokapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ActivationTokenDTO
 */
@lombok.Builder
@lombok.AllArgsConstructor

@JsonTypeName("ActivationToken")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-14T03:02:17.381251+01:00[Europe/Brussels]")
public class ActivationTokenDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String token;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expirationDate;

  private Long userId;

  /**
   * Default constructor
   * @deprecated Use {@link ActivationTokenDTO#ActivationTokenDTO(String, LocalDate, Long)}
   */
  @Deprecated
  public ActivationTokenDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ActivationTokenDTO(String token, LocalDate expirationDate, Long userId) {
    this.token = token;
    this.expirationDate = expirationDate;
    this.userId = userId;
  }

  public ActivationTokenDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Token id
   * @return id
  */
  
  @Schema(name = "id", example = "11", description = "Token id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ActivationTokenDTO token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  */
  @NotNull
  @Schema(name = "token", example = "a1b2c3d4e5f6g7h8i9j0", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public ActivationTokenDTO expirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Get expirationDate
   * @return expirationDate
  */
  @NotNull
  @Schema(name = "expirationDate", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("expirationDate")
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public ActivationTokenDTO userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @NotNull
  @Schema(name = "userId", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userId")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivationTokenDTO activationToken = (ActivationTokenDTO) o;
    return Objects.equals(this.id, activationToken.id) &&
        Objects.equals(this.token, activationToken.token) &&
        Objects.equals(this.expirationDate, activationToken.expirationDate) &&
        Objects.equals(this.userId, activationToken.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, token, expirationDate, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActivationTokenDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

