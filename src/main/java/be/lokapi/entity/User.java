package be.lokapi.entity;

import be.lokapi.enums.RolesEnum;
import be.lokapi.model.RegisterUserRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name ="username",unique = true, nullable = false)
    protected String username;
    @Column(name="email",unique = true, nullable = false)
    protected String email;
    @Column(name ="password",nullable = false)
    protected String password;

    @Column(name ="firstname", nullable = true)
    protected String firstname;
    @Column(name ="lastname", nullable = true)
    protected String lastname;
    @Column(name ="profile_picture", nullable = true)
    protected String profilePicture;
    @Column(name ="phone", nullable = true)
    protected String phone;

    @Column(name = "active", nullable = false)
    protected Boolean active;
    @CreatedDate
    @Column(name = "creation_date", nullable = false,updatable = false)
    @Temporal(value = TemporalType.DATE)
    protected LocalDate creationDate;
    @LastModifiedDate
    @Column(name = "update_date", nullable = false)
    protected LocalDate updateDate;
    @Column(name = "delete_date", nullable = true)
    protected LocalDate deleteDate;

    @OneToMany(mappedBy = "owner")
    protected List<Property> properties = new ArrayList<>();

    @OneToMany(mappedBy = "tenant")
    protected List<Lease> leases = new ArrayList<>();

    @Enumerated(EnumType.STRING) // Stocke le nom du rôle dans la base de données
    @ElementCollection // Cela permet de stocker une liste d'éléments simples comme une collection
    protected List<RolesEnum> roles = new ArrayList<>();



}
