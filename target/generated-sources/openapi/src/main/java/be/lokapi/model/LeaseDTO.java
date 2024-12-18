package be.lokapi.model;

import java.net.URI;
import java.util.Objects;
import be.lokapi.model.PropertyDTO;
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
 * LeaseDTO
 */
@lombok.Builder
@lombok.AllArgsConstructor

@JsonTypeName("Lease")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-14T03:02:17.381251+01:00[Europe/Brussels]")
public class LeaseDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private PropertyDTO property;

  private UserDTO tenant;

  private UserDTO owner;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate endDate;

  private BigDecimal rentAmount;

  private BigDecimal depositAmount;

  private String contract;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate creationDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate deleteDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate updateDate;

  /**
   * Default constructor
   * @deprecated Use {@link LeaseDTO#LeaseDTO(Long, PropertyDTO, UserDTO, LocalDate, BigDecimal, String, LocalDate, LocalDate)}
   */
  @Deprecated
  public LeaseDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public LeaseDTO(Long id, PropertyDTO property, UserDTO tenant, LocalDate startDate, BigDecimal rentAmount, String contract, LocalDate creationDate, LocalDate updateDate) {
    this.id = id;
    this.property = property;
    this.tenant = tenant;
    this.startDate = startDate;
    this.rentAmount = rentAmount;
    this.contract = contract;
    this.creationDate = creationDate;
    this.updateDate = updateDate;
  }

  public LeaseDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Lease
   * @return id
  */
  @NotNull
  @Schema(name = "id", description = "Lease", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LeaseDTO property(PropertyDTO property) {
    this.property = property;
    return this;
  }

  /**
   * Get property
   * @return property
  */
  @NotNull
  @Schema(name = "property", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("property")
  public PropertyDTO getProperty() {
    return property;
  }

  public void setProperty(PropertyDTO property) {
    this.property = property;
  }

  public LeaseDTO tenant(UserDTO tenant) {
    this.tenant = tenant;
    return this;
  }

  /**
   * Get tenant
   * @return tenant
  */
  @NotNull
  @Schema(name = "tenant", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tenant")
  public UserDTO getTenant() {
    return tenant;
  }

  public void setTenant(UserDTO tenant) {
    this.tenant = tenant;
  }

  public LeaseDTO owner(UserDTO owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
  */
  
  @Schema(name = "owner", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("owner")
  public UserDTO getOwner() {
    return owner;
  }

  public void setOwner(UserDTO owner) {
    this.owner = owner;
  }

  public LeaseDTO startDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Date start of lease
   * @return startDate
  */
  @NotNull
  @Schema(name = "startDate", description = "Date start of lease", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("startDate")
  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LeaseDTO endDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Date end of lease  (null if empty)
   * @return endDate
  */
  
  @Schema(name = "endDate", description = "Date end of lease  (null if empty)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("endDate")
  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public LeaseDTO rentAmount(BigDecimal rentAmount) {
    this.rentAmount = rentAmount;
    return this;
  }

  /**
   * rent amount
   * @return rentAmount
  */
  @NotNull
  @Schema(name = "rentAmount", description = "rent amount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("rentAmount")
  public BigDecimal getRentAmount() {
    return rentAmount;
  }

  public void setRentAmount(BigDecimal rentAmount) {
    this.rentAmount = rentAmount;
  }

  public LeaseDTO depositAmount(BigDecimal depositAmount) {
    this.depositAmount = depositAmount;
    return this;
  }

  /**
   * desposit amount
   * @return depositAmount
  */
  
  @Schema(name = "depositAmount", description = "desposit amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("depositAmount")
  public BigDecimal getDepositAmount() {
    return depositAmount;
  }

  public void setDepositAmount(BigDecimal depositAmount) {
    this.depositAmount = depositAmount;
  }

  public LeaseDTO contract(String contract) {
    this.contract = contract;
    return this;
  }

  /**
   * Lease Contrat PDF ( bail )
   * @return contract
  */
  @NotNull
  @Schema(name = "contract", description = "Lease Contrat PDF ( bail )", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("contract")
  public String getContract() {
    return contract;
  }

  public void setContract(String contract) {
    this.contract = contract;
  }

  public LeaseDTO creationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Lease creation date
   * @return creationDate
  */
  @NotNull
  @Schema(name = "creationDate", description = "Lease creation date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creationDate")
  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public LeaseDTO deleteDate(LocalDate deleteDate) {
    this.deleteDate = deleteDate;
    return this;
  }

  /**
   * Lease delete date can be null
   * @return deleteDate
  */
  
  @Schema(name = "deleteDate", description = "Lease delete date can be null", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("deleteDate")
  public LocalDate getDeleteDate() {
    return deleteDate;
  }

  public void setDeleteDate(LocalDate deleteDate) {
    this.deleteDate = deleteDate;
  }

  public LeaseDTO updateDate(LocalDate updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  /**
   * Lease update date can be same on creation date
   * @return updateDate
  */
  @NotNull
  @Schema(name = "updateDate", description = "Lease update date can be same on creation date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("updateDate")
  public LocalDate getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDate updateDate) {
    this.updateDate = updateDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaseDTO lease = (LeaseDTO) o;
    return Objects.equals(this.id, lease.id) &&
        Objects.equals(this.property, lease.property) &&
        Objects.equals(this.tenant, lease.tenant) &&
        Objects.equals(this.owner, lease.owner) &&
        Objects.equals(this.startDate, lease.startDate) &&
        Objects.equals(this.endDate, lease.endDate) &&
        Objects.equals(this.rentAmount, lease.rentAmount) &&
        Objects.equals(this.depositAmount, lease.depositAmount) &&
        Objects.equals(this.contract, lease.contract) &&
        Objects.equals(this.creationDate, lease.creationDate) &&
        Objects.equals(this.deleteDate, lease.deleteDate) &&
        Objects.equals(this.updateDate, lease.updateDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, property, tenant, owner, startDate, endDate, rentAmount, depositAmount, contract, creationDate, deleteDate, updateDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaseDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    property: ").append(toIndentedString(property)).append("\n");
    sb.append("    tenant: ").append(toIndentedString(tenant)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    rentAmount: ").append(toIndentedString(rentAmount)).append("\n");
    sb.append("    depositAmount: ").append(toIndentedString(depositAmount)).append("\n");
    sb.append("    contract: ").append(toIndentedString(contract)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    deleteDate: ").append(toIndentedString(deleteDate)).append("\n");
    sb.append("    updateDate: ").append(toIndentedString(updateDate)).append("\n");
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

