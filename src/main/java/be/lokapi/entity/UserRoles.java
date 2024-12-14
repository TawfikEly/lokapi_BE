package be.lokapi.entity;

import be.lokapi.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RolesEnum role;
}



