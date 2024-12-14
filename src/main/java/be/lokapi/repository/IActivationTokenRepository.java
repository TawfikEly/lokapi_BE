package be.lokapi.repository;

import be.lokapi.entity.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface IActivationTokenRepository extends JpaRepository<ActivationToken, Long> {

    @Query("SELECT at FROM ActivationToken at WHERE at.token = :token")
    Optional<ActivationToken> findByToken(@Param("token")  String token);

}
