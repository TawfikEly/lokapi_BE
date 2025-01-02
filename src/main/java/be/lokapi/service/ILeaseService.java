package be.lokapi.service;

import be.lokapi.entity.Lease;
import be.lokapi.model.LeaseDTO;
import be.lokapi.model.UserDTO;

import java.util.List;

public interface ILeaseService {

    LeaseDTO createLease(LeaseDTO leaseDTO);

    List<LeaseDTO> getAllLeases();

    LeaseDTO getLeaseById(Long leaseId);

    List<LeaseDTO> getLeaseByOwnerId(Long ownerId);

    List<Lease> getLeaseByOwnerIdAndTenantId(Long ownerId, Long tenantId);
    List<UserDTO> getTenantByOwner(Long ownerId);

    List<Lease> getLeaseByTenantId(Long tenantId);

    LeaseDTO updateLease(LeaseDTO leaseDTO);

    void deleteLease(LeaseDTO leaseDTO);
    void deleteLeaseById(Long leaseId);
}