package be.lokapi.entity;


import be.lokapi.enums.PaymentStatus;
import be.lokapi.model.PaymentDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "Payment")
@Getter
@Setter
public class Payment extends PaymentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "leaseId", nullable = false)
    private Lease lease;

    @Column(nullable = false)
    private LocalDate paymentDate;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private BigDecimal commission;

    @CreatedDate
    @Column(name = "creation_date", nullable = false,updatable = false)
    @Temporal(value = TemporalType.DATE)
    private LocalDate creationDate;
    @LastModifiedDate
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;
    @Column(nullable = true)
    private LocalDate deleteDate;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Payment Status", example = "PAID", allowableValues = {"PAID", "PENDING", "FAILED"})
    @Column(nullable = false)
    private PaymentStatus paymentStat;
}
