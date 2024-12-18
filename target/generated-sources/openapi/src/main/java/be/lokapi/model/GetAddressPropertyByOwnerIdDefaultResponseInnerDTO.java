package be.lokapi.model;

import java.net.URI;
import java.util.Objects;
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
 * GetAddressPropertyByOwnerIdDefaultResponseInnerDTO
 */
@lombok.Builder
@lombok.AllArgsConstructor

@JsonTypeName("getAddressPropertyByOwnerId_default_response_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-14T03:02:17.381251+01:00[Europe/Brussels]")
public class GetAddressPropertyByOwnerIdDefaultResponseInnerDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String address;

  private String city;

  private String zip;

  public GetAddressPropertyByOwnerIdDefaultResponseInnerDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GetAddressPropertyByOwnerIdDefaultResponseInnerDTO address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  
  @Schema(name = "address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public GetAddressPropertyByOwnerIdDefaultResponseInnerDTO city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  */
  
  @Schema(name = "city", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public GetAddressPropertyByOwnerIdDefaultResponseInnerDTO zip(String zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Get zip
   * @return zip
  */
  
  @Schema(name = "zip", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("zip")
  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetAddressPropertyByOwnerIdDefaultResponseInnerDTO getAddressPropertyByOwnerIdDefaultResponseInner = (GetAddressPropertyByOwnerIdDefaultResponseInnerDTO) o;
    return Objects.equals(this.id, getAddressPropertyByOwnerIdDefaultResponseInner.id) &&
        Objects.equals(this.address, getAddressPropertyByOwnerIdDefaultResponseInner.address) &&
        Objects.equals(this.city, getAddressPropertyByOwnerIdDefaultResponseInner.city) &&
        Objects.equals(this.zip, getAddressPropertyByOwnerIdDefaultResponseInner.zip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, city, zip);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetAddressPropertyByOwnerIdDefaultResponseInnerDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
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

