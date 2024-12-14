package be.lokapi.service.impl;

import be.lokapi.entity.Lease;
import be.lokapi.entity.User;
import be.lokapi.mapping.LeaseMapper;
import be.lokapi.mapping.UserMapper;
import be.lokapi.model.LeaseDTO;
import be.lokapi.model.UserDTO;
import be.lokapi.repository.ILeaseRepository;
import be.lokapi.service.ILeaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaseServiceImpl implements ILeaseService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private ILeaseRepository leaseRepository;
    private final UserMapper userMapper;
    private final LeaseMapper leaseMapper;

    public LeaseServiceImpl(UserMapper userMapper, ILeaseRepository leaseRepository, LeaseMapper leaseMapper) {
        this.userMapper = userMapper;
        this.leaseRepository = leaseRepository;
        this.leaseMapper = leaseMapper;
    }


    @Override
    public LeaseDTO createLease(LeaseDTO leaseDTO) {
        leaseDTO.setCreationDate(LocalDate.now());
        leaseDTO.setUpdateDate(LocalDate.now());
        leaseDTO.setDeleteDate(null);
        Lease lease = leaseMapper.toEntity(leaseDTO);
        return leaseMapper.toDto(leaseRepository.save(lease));
    }

    @Override
    public List<LeaseDTO> getAllLeases() {
        return  leaseRepository.findAll().stream().map(leaseMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public LeaseDTO getLeaseById(Long leaseId) {
        return leaseMapper.toDto(leaseRepository.findById(leaseId).orElse(null));
    }


    @Override
    public List<LeaseDTO> getLeaseByOwnerId(Long ownerId) {
        return leaseRepository.getLeaseByOwnerId(ownerId).stream().map(leaseMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Lease> getLeaseByOwnerIdAndTenantId(Long ownerId, Long tenantId) {
        return null;//leaseRepository.getLeaseByOwnerIdAndTenantId(ownerId,tenantId);
    }

    @Override
    public List<UserDTO> getTenantByOwner(Long ownerId) {
        List<User> tenants = leaseRepository.getTenantsByOwner(ownerId);
        List<UserDTO> userDTOs = tenants.stream()
                .map(userMapper::toDto).collect(Collectors.toList());

        return userDTOs;
    }

    @Override
    public List<Lease> getLeaseByTenantId(Long tenantId) {
        return null;//leaseRepository.getLeaseByTenantId(tenantId);
    }

    @Override
    public LeaseDTO updateLease(LeaseDTO leaseDTO) {

        leaseDTO.setUpdateDate(LocalDate.now());
        Lease lease = leaseMapper.toEntity(leaseDTO);
        Lease updatedLease = leaseRepository.save(lease);
        return  leaseMapper.toDto(updatedLease);

    }

    @Override
    public LeaseDTO deleteLease(LeaseDTO leaseDTO) {
        leaseDTO.setUpdateDate(LocalDate.now());
        leaseDTO.setDeleteDate(LocalDate.now());
        Lease lease = leaseMapper.toEntity(leaseDTO);
        Lease deletedLease = leaseRepository.save(lease);
        return leaseMapper.toDto(deletedLease);
    }
}
