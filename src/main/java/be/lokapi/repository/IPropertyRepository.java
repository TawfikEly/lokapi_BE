package be.lokapi.repository;

import be.lokapi.entity.Property;
import be.lokapi.model.GetAddressPropertyByOwnerIdDefaultResponseInnerDTO;
import be.lokapi.model.PropertyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPropertyRepository extends JpaRepository<Property, Long> {
    @Query("SELECT p FROM Property p WHERE p.owner.id = :owner_id AND p.deleteDate is null")
    List<Property> getPropertyByOwnerId(@Param("owner_id") Long owner_id);

    @Query("SELECT new be.lokapi.model.GetAddressPropertyByOwnerIdDefaultResponseInnerDTO(p.id, p.address, p.city, p.zip) FROM Property p WHERE p.owner.id = :ownerId AND p.deleteDate is null")
    List<GetAddressPropertyByOwnerIdDefaultResponseInnerDTO>  getAddressPropertyByOwnerId(@Param("ownerId") Long ownerId);

    @Query("SELECT p FROM Property p LEFT JOIN FETCH p.leases WHERE p.id = :propertyId AND p.deleteDate is null")
    Optional<Property> getPropertyWithLeaseById(@Param("propertyId") Long propertyId);



}
