package be.lokapi.repository;

import be.lokapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

/*
    @Query("SELECT p FROM Payment p WHERE p.date = :date")
    List<Payment> findPaymentsByDate(@Param("date") LocalDate date);

    @Query("SELECT p FROM Payment p WHERE p.ownerId = :ownerId")
    List<Payment> findPaymentsByOwnerId(@Param("ownerId") Long ownerId);
    @Query("SELECT p FROM Payment p WHERE p.tenantId = :tenantId")
    List<Payment> findPaymentsByTenantId(@Param("tenantId")Long tenantId);*/
}
