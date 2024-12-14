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
@Table(name = "property")
@Getter
@Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String zip;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;

    @CreatedDate
    @Column(name = "creation_date",nullable = false, updatable = false)
    private LocalDate creationDate;
    @LastModifiedDate
    @Column(name = "update_date",nullable = false)
    private LocalDate updateDate;
    @Column(name = "delete_date",nullable = true)
    private LocalDate deleteDate;

    @OneToOne(mappedBy = "property")
    private Lease lease;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "property")
    private List<Lease> leases = new ArrayList<>();


}
