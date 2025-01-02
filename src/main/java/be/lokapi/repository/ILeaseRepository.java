package be.lokapi.repository;

import be.lokapi.entity.Lease;
import be.lokapi.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILeaseRepository extends JpaRepository<Lease, Long> {
/*

       @Query("SELECT l FROM Lease l WHERE l.ownerId = :ownerId AND l.tenantId = :tenantId")
       List<Lease> findLeaseByOwnerIdAndTenantId(@Param("ownerId") Long ownerId, @Param("tenantId") Long tenantId);

       @Query("SELECT l FROM Lease l WHERE l.tenantId = :tenantId")
       List<Lease> findLeaseByTenantId(@Param("tenantId") Long tenantId);*/

    @Query("SELECT l.tenant FROM Lease l JOIN l.property p WHERE p.owner.id = :ownerId AND l.deleteDate is null")
    List<User> getTenantsByOwner(@Param("ownerId") Long ownerId);


    @Query("SELECT l FROM Lease l LEFT JOIN FETCH l.property WHERE l.owner.id = :ownerId AND l.deleteDate is null")
    List<Lease> getLeaseByOwnerId(@Param("ownerId") Long ownerId);


    @EntityGraph(attributePaths = {"property"})
    Optional<Lease> findById(Long leaseId);

    @EntityGraph(attributePaths = {"property"})
    @Query("SELECT l FROM Lease l WHERE l.id = :leaseId AND l.deleteDate IS NULL")
    Optional<Lease> findByIdWithProperty(@Param("leaseId") Long leaseId);

}
