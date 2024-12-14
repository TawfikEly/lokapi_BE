package be.lokapi.controller;


import be.lokapi.api.LeasesApi;
import be.lokapi.entity.Lease;
import be.lokapi.model.LeaseDTO;
import be.lokapi.service.ILeaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leases")
public class LeaseController implements LeasesApi {

    private final  ILeaseService leaseService;


    public LeaseController(ILeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @Override
    @PostMapping("/createLease")
    public ResponseEntity<LeaseDTO> createLease(@RequestBody LeaseDTO leaseDTO) {
        leaseDTO.setCreationDate(LocalDate.now());
        leaseDTO.setUpdateDate(LocalDate.now());
        LeaseDTO newleaseDTO = leaseService.createLease(leaseDTO);

        if(newleaseDTO!=null)
            return ResponseEntity.ok(newleaseDTO);
        return ResponseEntity.badRequest().build();
    }


    @Override
    @GetMapping("/getAllLeases")
    public ResponseEntity<List<LeaseDTO>> getAllLeases() {
        List<LeaseDTO> leasesDTO = leaseService.getAllLeases();

        return ResponseEntity.ok(leasesDTO);
    }

    @Override
    @GetMapping("/getLeaseById")
    public ResponseEntity<LeaseDTO> getLeaseById(Long leaseId) {
        LeaseDTO leaseDTO = leaseService.getLeaseById(leaseId);
        if (leaseDTO !=  null)
            return ResponseEntity.ok(leaseDTO);

        return ResponseEntity.badRequest().build();
    }

    @Override
    @GetMapping("/getLeaseByOwnerId/{ownerId}")
    public ResponseEntity<List<LeaseDTO>> getLeaseByOwnerId(@PathVariable Long ownerId) {
        List<LeaseDTO> leaseDTOList = leaseService.getLeaseByOwnerId(ownerId);
        if (leaseDTOList !=  null || !leaseDTOList.isEmpty())
            return ResponseEntity.ok(leaseDTOList);
        return ResponseEntity.notFound().build();
    }

    @Override
    @GetMapping("/getLeaseByOwnerIdAndTenantId")
    public ResponseEntity<List<LeaseDTO>> getLeaseByOwnerIdAndTenantId(Long ownerId, Long tenantId) {
        List<Lease> leases = leaseService.getLeaseByOwnerIdAndTenantId(ownerId,tenantId);
        if (leases !=  null || !leases.isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @Override
    @GetMapping("/getLeaseByTenantId")
    public ResponseEntity<List<LeaseDTO>> getLeaseByTenantId(Long tenantId) {
        List<Lease> leases = leaseService.getLeaseByTenantId(tenantId);
        if (leases !=  null || !leases.isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @Override
    @PutMapping("/updateLease")
    public ResponseEntity<LeaseDTO> updateLease(LeaseDTO leaseDTO) {
        LeaseDTO newLeaseDTO = leaseService.updateLease(leaseDTO);
        if (newLeaseDTO !=  null)
            return ResponseEntity.ok(newLeaseDTO);

        return ResponseEntity.badRequest().build();
    }
    @Override
    @PutMapping("/deleteLease")
    public ResponseEntity<LeaseDTO> deleteLease(LeaseDTO leaseDTO) {

        LeaseDTO deletedLeaseDTO = leaseService.deleteLease(leaseDTO);


        if (leaseDTO !=  null)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }
}
