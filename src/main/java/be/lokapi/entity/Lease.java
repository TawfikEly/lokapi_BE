package be.lokapi.entity;

import be.lokapi.model.LeaseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "lease")
@Getter
@Setter
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;


    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private User tenant;


    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false) // Cette annotation mappe la clé étrangère
    private User owner;


    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Column(name = "rent_amount", nullable = false)
    private BigDecimal rentAmount;
    @Column(nullable = false)
    private BigDecimal depositAmount;
    @Column(name = "contract", nullable = false)
    private String contract;

    @CreatedDate
    @Column(name = "creation_date", nullable = false,updatable = false)
    @Temporal(value = TemporalType.DATE)
    private LocalDate creationDate;
    @LastModifiedDate
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;
    @Column(nullable = true)
    private LocalDate deleteDate;

    @OneToMany(mappedBy = "lease")
    private List<Payment> payments = new ArrayList<>();
}
