package be.lokapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * PaymentDTO
 */
@lombok.Builder
@lombok.AllArgsConstructor

@JsonTypeName("Payment")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-14T03:02:17.381251+01:00[Europe/Brussels]")
public class PaymentDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private Long leaseId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate paymentDate;

  private BigDecimal amount;

  private BigDecimal commission;

  /**
   * Payment Status can be paid or not
   */
  public enum PaymentStatusEnum {
    PAID("PAID"),
    
    PENDING("PENDING"),
    
    FAILED("FAILED");

    private String value;

    PaymentStatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PaymentStatusEnum fromValue(String value) {
      for (PaymentStatusEnum b : PaymentStatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private PaymentStatusEnum paymentStatus;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate creationDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate deleteDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate updateDate;

  /**
   * Default constructor
   * @deprecated Use {@link PaymentDTO#PaymentDTO(Long, LocalDate, BigDecimal, PaymentStatusEnum, LocalDate, LocalDate)}
   */
  @Deprecated
  public PaymentDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PaymentDTO(Long id, LocalDate paymentDate, BigDecimal amount, PaymentStatusEnum paymentStatus, LocalDate creationDate, LocalDate updateDate) {
    this.id = id;
    this.paymentDate = paymentDate;
    this.amount = amount;
    this.paymentStatus = paymentStatus;
    this.creationDate = creationDate;
    this.updateDate = updateDate;
  }

  public PaymentDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Payment id
   * @return id
  */
  @NotNull
  @Schema(name = "id", description = "Payment id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PaymentDTO leaseId(Long leaseId) {
    this.leaseId = leaseId;
    return this;
  }

  /**
   * Lease id
   * @return leaseId
  */
  
  @Schema(name = "leaseId", description = "Lease id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("leaseId")
  public Long getLeaseId() {
    return leaseId;
  }

  public void setLeaseId(Long leaseId) {
    this.leaseId = leaseId;
  }

  public PaymentDTO paymentDate(LocalDate paymentDate) {
    this.paymentDate = paymentDate;
    return this;
  }

  /**
   * Payment date
   * @return paymentDate
  */
  @NotNull
  @Schema(name = "paymentDate", description = "Payment date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentDate")
  public LocalDate getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(LocalDate paymentDate) {
    this.paymentDate = paymentDate;
  }

  public PaymentDTO amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @NotNull
  @Schema(name = "amount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public PaymentDTO commission(BigDecimal commission) {
    this.commission = commission;
    return this;
  }

  /**
   * Get commission
   * @return commission
  */
  
  @Schema(name = "commission", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("commission")
  public BigDecimal getCommission() {
    return commission;
  }

  public void setCommission(BigDecimal commission) {
    this.commission = commission;
  }

  public PaymentDTO paymentStatus(PaymentStatusEnum paymentStatus) {
    this.paymentStatus = paymentStatus;
    return this;
  }

  /**
   * Payment Status can be paid or not
   * @return paymentStatus
  */
  @NotNull
  @Schema(name = "paymentStatus", example = "PAID", description = "Payment Status can be paid or not", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentStatus")
  public PaymentStatusEnum getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public PaymentDTO creationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Payment creation date
   * @return creationDate
  */
  @NotNull
  @Schema(name = "creationDate", description = "Payment creation date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creationDate")
  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public PaymentDTO deleteDate(LocalDate deleteDate) {
    this.deleteDate = deleteDate;
    return this;
  }

  /**
   * Payment delete date  NULL if not deleted
   * @return deleteDate
  */
  
  @Schema(name = "deleteDate", description = "Payment delete date  NULL if not deleted", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("deleteDate")
  public LocalDate getDeleteDate() {
    return deleteDate;
  }

  public void setDeleteDate(LocalDate deleteDate) {
    this.deleteDate = deleteDate;
  }

  public PaymentDTO updateDate(LocalDate updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  /**
   * Payment update date can be same on creation date
   * @return updateDate
  */
  @NotNull
  @Schema(name = "updateDate", description = "Payment update date can be same on creation date", requiredMode = Schema.RequiredMode.REQUIRED)
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
    PaymentDTO payment = (PaymentDTO) o;
    return Objects.equals(this.id, payment.id) &&
        Objects.equals(this.leaseId, payment.leaseId) &&
        Objects.equals(this.paymentDate, payment.paymentDate) &&
        Objects.equals(this.amount, payment.amount) &&
        Objects.equals(this.commission, payment.commission) &&
        Objects.equals(this.paymentStatus, payment.paymentStatus) &&
        Objects.equals(this.creationDate, payment.creationDate) &&
        Objects.equals(this.deleteDate, payment.deleteDate) &&
        Objects.equals(this.updateDate, payment.updateDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, leaseId, paymentDate, amount, commission, paymentStatus, creationDate, deleteDate, updateDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    leaseId: ").append(toIndentedString(leaseId)).append("\n");
    sb.append("    paymentDate: ").append(toIndentedString(paymentDate)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    commission: ").append(toIndentedString(commission)).append("\n");
    sb.append("    paymentStatus: ").append(toIndentedString(paymentStatus)).append("\n");
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

