package be.lokapi.mapping;

import be.lokapi.entity.Lease;
import be.lokapi.entity.Property;
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

    public Lease toEntity(LeaseDTO leaseDTO){
        if (leaseDTO == null) {
            return null;
        }

        Lease lease = new Lease();
        lease.setId(leaseDTO.getId());
        lease.setStartDate(leaseDTO.getStartDate());
        lease.setEndDate(leaseDTO.getEndDate());
        lease.setRentAmount(leaseDTO.getRentAmount());
        lease.setDepositAmount(leaseDTO.getDepositAmount());
        lease.setContract(leaseDTO.getContract());
        lease.setCreationDate(leaseDTO.getCreationDate());
        lease.setUpdateDate(leaseDTO.getUpdateDate());
        lease.setDeleteDate(leaseDTO.getDeleteDate());


        User tenant = new User();
        if (leaseDTO.getTenant() != null) {
            tenant.setId(leaseDTO.getTenant().getId());
            tenant.setUsername(leaseDTO.getTenant().getUsername());
            tenant.setEmail(leaseDTO.getTenant().getEmail());
            tenant.setPassword(leaseDTO.getTenant().getPassword());
            tenant.setFirstname(leaseDTO.getTenant().getFirstname());
            tenant.setLastname(leaseDTO.getTenant().getLastname());
            tenant.setPhone(leaseDTO.getTenant().getPhone());
            tenant.setProfilePicture(leaseDTO.getTenant().getProfilePicture());
            tenant.setCreationDate(leaseDTO.getTenant().getCreationDate());
            tenant.setUpdateDate(leaseDTO.getTenant().getUpdateDate());
            tenant.setActive(leaseDTO.getTenant().getActive());
            tenant.setCreationDate(leaseDTO.getTenant().getCreationDate());
            tenant.setDeleteDate(leaseDTO.getTenant().getDeleteDate());
            tenant.setRoles(
                    leaseDTO.getTenant().getRoles()
                            .stream()
                            .map(role -> be.lokapi.enums.RolesEnum.valueOf(role.name()))
                            .collect(Collectors.toList())
            );
        }

        User owner =  new User();;
        if (leaseDTO.getOwner() != null) {
            owner.setId(leaseDTO.getOwner().getId());
            owner.setUsername(leaseDTO.getOwner().getUsername());
            owner.setEmail(leaseDTO.getOwner().getEmail());
            owner.setPassword(leaseDTO.getOwner().getPassword());
            owner.setFirstname(leaseDTO.getOwner().getFirstname());
            owner.setLastname(leaseDTO.getOwner().getLastname());
            owner.setPhone(leaseDTO.getOwner().getPhone());
            owner.setProfilePicture(leaseDTO.getOwner().getProfilePicture());
            owner.setCreationDate(leaseDTO.getOwner().getCreationDate());
            owner.setUpdateDate(leaseDTO.getOwner().getUpdateDate());
            owner.setActive(leaseDTO.getOwner().getActive());
            owner.setCreationDate(leaseDTO.getOwner().getCreationDate());
            owner.setDeleteDate(leaseDTO.getOwner().getDeleteDate());
            owner.setRoles(
                    leaseDTO.getOwner().getRoles()
                            .stream()
                            .map(role -> be.lokapi.enums.RolesEnum.valueOf(role.name()))
                            .collect(Collectors.toList())
            );
            Property property = null;
            if (leaseDTO.getProperty() != null) {
                property = new Property();
                property.setId(leaseDTO.getProperty().getId());
                property.setAddress(leaseDTO.getProperty().getAddress());
                property.setCity(leaseDTO.getProperty().getCity());
                property.setZip(leaseDTO.getProperty().getZip());
                property.setPrice(leaseDTO.getProperty().getPrice());
                property.setDescription(leaseDTO.getProperty().getDescription());
                property.setOwner(userMapper(leaseDTO.getProperty().getOwner())); // a verifier si c'est bon
                property.setLeases(leaseDTO.getProperty().getLeases().stream().map(leaseMapper::toEntity).collect(Collectors.toList()));
                property.setCreationDate(leaseDTO.getProperty().getCreationDate());
                property.setUpdateDate(leaseDTO.getProperty().getUpdateDate());
                property.setDeleteDate(leaseDTO.getProperty().getDeleteDate());
            }

            lease.setTenant(tenant);
            lease.setOwner(owner);
            lease.setProperty(property);


        }

        return lease;
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
            tenantDTO.setPhone(lease.getTenant().getPhone());
            tenantDTO.setProfilePicture(lease.getTenant().getProfilePicture());
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
            ownerDTO.setPhone(lease.getOwner().getPhone());
            ownerDTO.setProfilePicture(lease.getOwner().getProfilePicture());
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
        ownerDTO.setPhone(owner.getPhone());
        ownerDTO.setProfilePicture(owner.getProfilePicture());
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


    private User userMapper(UserDTO ownerDTO) {
        User owner = new User();
        owner.setId(ownerDTO.getId());
        owner.setUsername(ownerDTO.getUsername());
        owner.setEmail(ownerDTO.getEmail());
        owner.setPassword(ownerDTO.getPassword());
        owner.setFirstname(ownerDTO.getFirstname());
        owner.setLastname(ownerDTO.getLastname());
        owner.setPhone(ownerDTO.getPhone());
        owner.setProfilePicture(ownerDTO.getProfilePicture());
        owner.setCreationDate(ownerDTO.getCreationDate());
        owner.setUpdateDate(ownerDTO.getUpdateDate());
        owner.setActive(ownerDTO.getActive());
        owner.setCreationDate(ownerDTO.getCreationDate());
        owner.setDeleteDate(ownerDTO.getDeleteDate());
        owner.setRoles(
                ownerDTO.getRoles()
                        .stream()
                        .map(role -> be.lokapi.enums.RolesEnum.valueOf(role.name()))
                        .collect(Collectors.toList())
        );

        return owner;
    }
}


