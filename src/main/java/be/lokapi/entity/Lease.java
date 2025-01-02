package be.lokapi.entity;

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

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Column(name = "rent_amount", nullable = false)
    private BigDecimal rentAmount;
    @Column(nullable = false)
    private BigDecimal depositAmount;
    @Column(name = "contract", nullable = false) // fichier pdf path
    private String contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private User tenant;


    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false) // Cette annotation mappe la clé étrangère
    private User owner;

    @OneToMany(mappedBy = "lease")
    private List<Payment> payments = new ArrayList<>();

    @CreatedDate
    @Column(name = "creation_date", nullable = false,updatable = false)
    @Temporal(value = TemporalType.DATE)
    private LocalDate creationDate;
    @LastModifiedDate
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;
    @Column(name = "delete_date",nullable = true)
    private LocalDate deleteDate;
}
