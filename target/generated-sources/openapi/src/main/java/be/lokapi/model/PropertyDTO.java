package be.lokapi.model;

import java.net.URI;
import java.util.Objects;
import be.lokapi.model.LeaseDTO;
import be.lokapi.model.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
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
 * PropertyDTO
 */
@lombok.Builder
@lombok.AllArgsConstructor

@JsonTypeName("Property")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-14T03:02:17.381251+01:00[Europe/Brussels]")
public class PropertyDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String address;

  private String city;

  private String zip;

  private String description;

  private UserDTO owner;

  private LeaseDTO lease;

  private BigDecimal price;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate creationDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate updateDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate deleteDate;

  /**
   * Default constructor
   * @deprecated Use {@link PropertyDTO#PropertyDTO(Long, String, String, UserDTO, LocalDate, LocalDate)}
   */
  @Deprecated
  public PropertyDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PropertyDTO(Long id, String city, String zip, UserDTO owner, LocalDate creationDate, LocalDate updateDate) {
    this.id = id;
    this.city = city;
    this.zip = zip;
    this.owner = owner;
    this.creationDate = creationDate;
    this.updateDate = updateDate;
  }

  public PropertyDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Property ID unique
   * @return id
  */
  @NotNull
  @Schema(name = "id", description = "Property ID unique", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PropertyDTO address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Property adress
   * @return address
  */
  
  @Schema(name = "address", description = "Property adress", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public PropertyDTO city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Property city
   * @return city
  */
  @NotNull
  @Schema(name = "city", description = "Property city", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public PropertyDTO zip(String zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Property zip
   * @return zip
  */
  @NotNull
  @Schema(name = "zip", description = "Property zip", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("zip")
  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public PropertyDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Property description
   * @return description
  */
  
  @Schema(name = "description", description = "Property description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PropertyDTO owner(UserDTO owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
  */
  @NotNull
  @Schema(name = "owner", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("owner")
  public UserDTO getOwner() {
    return owner;
  }

  public void setOwner(UserDTO owner) {
    this.owner = owner;
  }

  public PropertyDTO lease(LeaseDTO lease) {
    this.lease = lease;
    return this;
  }

  /**
   * Get lease
   * @return lease
  */
  
  @Schema(name = "lease", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lease")
  public LeaseDTO getLease() {
    return lease;
  }

  public void setLease(LeaseDTO lease) {
    this.lease = lease;
  }

  public PropertyDTO price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  
  @Schema(name = "price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public PropertyDTO creationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Property creation date
   * @return creationDate
  */
  @NotNull
  @Schema(name = "creationDate", description = "Property creation date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creationDate")
  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public PropertyDTO updateDate(LocalDate updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  /**
   * Property update date
   * @return updateDate
  */
  @NotNull
  @Schema(name = "updateDate", description = "Property update date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("updateDate")
  public LocalDate getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDate updateDate) {
    this.updateDate = updateDate;
  }

  public PropertyDTO deleteDate(LocalDate deleteDate) {
    this.deleteDate = deleteDate;
    return this;
  }

  /**
   * Property detele date
   * @return deleteDate
  */
  
  @Schema(name = "deleteDate", description = "Property detele date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("deleteDate")
  public LocalDate getDeleteDate() {
    return deleteDate;
  }

  public void setDeleteDate(LocalDate deleteDate) {
    this.deleteDate = deleteDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PropertyDTO property = (PropertyDTO) o;
    return Objects.equals(this.id, property.id) &&
        Objects.equals(this.address, property.address) &&
        Objects.equals(this.city, property.city) &&
        Objects.equals(this.zip, property.zip) &&
        Objects.equals(this.description, property.description) &&
        Objects.equals(this.owner, property.owner) &&
        Objects.equals(this.lease, property.lease) &&
        Objects.equals(this.price, property.price) &&
        Objects.equals(this.creationDate, property.creationDate) &&
        Objects.equals(this.updateDate, property.updateDate) &&
        Objects.equals(this.deleteDate, property.deleteDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, city, zip, description, owner, lease, price, creationDate, updateDate, deleteDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PropertyDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    lease: ").append(toIndentedString(lease)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    updateDate: ").append(toIndentedString(updateDate)).append("\n");
    sb.append("    deleteDate: ").append(toIndentedString(deleteDate)).append("\n");
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

