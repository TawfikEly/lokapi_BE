package be.lokapi.mapping;

import be.lokapi.entity.Lease;
import be.lokapi.entity.User;
import be.lokapi.model.LeaseDTO;
import be.lokapi.model.PropertyDTO;
import be.lokapi.model.UserDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class LeaseMapperUtil {

    private final LeaseMapper leaseMapper;
    private final UserMapper userMapper;

    public LeaseMapperUtil(LeaseMapper leaseMapper, UserMapper userMapper) {
        this.leaseMapper = leaseMapper;
        this.userMapper = userMapper;
    }

    public  LeaseDTO toDto(Lease lease) {
        if (lease == null) {
            return null;
        }



        LeaseDTO leaseDTO = new LeaseDTO();
        leaseDTO.setId(lease.getId());
        leaseDTO.setStartDate(lease.getStartDate());
        leaseDTO.setEndDate(lease.getEndDate());
        leaseDTO.setRentAmount(lease.getRentAmount());
        leaseDTO.setDepositAmount(lease.getDepositAmount());
        leaseDTO.setContract(lease.getContract());
        leaseDTO.setCreationDate(lease.getCreationDate());
        leaseDTO.setUpdateDate(lease.getUpdateDate());
        leaseDTO.setDeleteDate(lease.getDeleteDate());



        UserDTO tenantDTO = new UserDTO();
        if (lease.getTenant() != null) {
            tenantDTO.setId(lease.getTenant().getId());
            tenantDTO.setUsername(lease.getTenant().getUsername());
            tenantDTO.setEmail(lease.getTenant().getEmail());
            tenantDTO.setPassword(lease.getTenant().getPassword());
            tenantDTO.setFirstname(lease.getTenant().getFirstname());
            tenantDTO.setLastname(lease.getTenant().getLastname());
            tenantDTO.setCreationDate(lease.getTenant().getCreationDate());
            tenantDTO.setUpdateDate(lease.getTenant().getUpdateDate());
            tenantDTO.setActive(lease.getTenant().getActive());
            tenantDTO.setCreationDate(lease.getTenant().getCreationDate());
            tenantDTO.setDeleteDate(lease.getTenant().getDeleteDate());
            tenantDTO.setRoles(
                    lease.getTenant().getRoles()
                            .stream()
                            .map(role -> be.lokapi.model.UserDTO.RolesEnum.valueOf(role.name()))
                            .collect(Collectors.toList())
            );


        }

        UserDTO ownerDTO =  new UserDTO();;
        if (lease.getOwner() != null) {
            ownerDTO.setId(lease.getOwner().getId());
            ownerDTO.setUsername(lease.getOwner().getUsername());
            ownerDTO.setEmail(lease.getOwner().getEmail());
            ownerDTO.setPassword(lease.getOwner().getPassword());
            ownerDTO.setFirstname(lease.getOwner().getFirstname());
            ownerDTO.setLastname(lease.getOwner().getLastname());
            ownerDTO.setCreationDate(lease.getOwner().getCreationDate());
            ownerDTO.setUpdateDate(lease.getOwner().getUpdateDate());
            ownerDTO.setActive(lease.getOwner().getActive());
            ownerDTO.setCreationDate(lease.getOwner().getCreationDate());
            ownerDTO.setDeleteDate(lease.getOwner().getDeleteDate());
            ownerDTO.setRoles(
                    lease.getOwner().getRoles()
                            .stream()
                            .map(role -> be.lokapi.model.UserDTO.RolesEnum.valueOf(role.name()))
                            .collect(Collectors.toList())
            );
            PropertyDTO propertyDTO = null;
            if (lease.getProperty() != null) {
                propertyDTO = new PropertyDTO();
                propertyDTO.setId(lease.getProperty().getId());
                propertyDTO.setAddress(lease.getProperty().getAddress());
                propertyDTO.setCity(lease.getProperty().getCity());
                propertyDTO.setZip(lease.getProperty().getZip());
                propertyDTO.setPrice(lease.getProperty().getPrice());
                propertyDTO.setDescription(lease.getProperty().getDescription());
                propertyDTO.setOwner(userMapper(lease.getProperty().getOwner())); // a verifier si c'est bon
                propertyDTO.setLeases(lease.getProperty().getLeases().stream().map(leaseMapper::toDto).collect(Collectors.toList()));
                propertyDTO.setCreationDate(lease.getProperty().getCreationDate());
                propertyDTO.setUpdateDate(lease.getProperty().getUpdateDate());
                propertyDTO.setDeleteDate(lease.getProperty().getDeleteDate());
            }

            leaseDTO.setTenant(tenantDTO);
            leaseDTO.setOwner(ownerDTO);
            leaseDTO.setProperty(propertyDTO);


        }
        return leaseDTO;
    }

    private UserDTO userMapper(User owner) {
        UserDTO ownerDTO = new UserDTO();
        ownerDTO.setId(owner.getId());
        ownerDTO.setUsername(owner.getUsername());
        ownerDTO.setEmail(owner.getEmail());
        ownerDTO.setPassword(owner.getPassword());
        ownerDTO.setFirstname(owner.getFirstname());
        ownerDTO.setLastname(owner.getLastname());
        ownerDTO.setCreationDate(owner.getCreationDate());
        ownerDTO.setUpdateDate(owner.getUpdateDate());
        ownerDTO.setActive(owner.getActive());
        ownerDTO.setCreationDate(owner.getCreationDate());
        ownerDTO.setDeleteDate(owner.getDeleteDate());
        ownerDTO.setRoles(
                owner.getRoles()
                        .stream()
                        .map(role -> be.lokapi.model.UserDTO.RolesEnum.valueOf(role.name()))
                        .collect(Collectors.toList())
        );

        return ownerDTO;
    }
}


