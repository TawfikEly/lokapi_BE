package be.lokapi.entity;

import be.lokapi.model.ActivationTokenDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "activationToken")
@Getter
@Setter
public class ActivationToken extends ActivationTokenDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="token",unique = true, nullable = false)
    private String token;
    @Column(name="expirationDate", nullable = false)
    private LocalDate expirationDate;
    @OneToOne
    private User  user;


}
