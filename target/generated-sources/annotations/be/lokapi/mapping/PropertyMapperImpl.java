package be.lokapi.mapping;

import be.lokapi.entity.Lease;
import be.lokapi.entity.Property;
import be.lokapi.entity.User;
import be.lokapi.enums.RolesEnum;
import be.lokapi.model.LeaseDTO;
import be.lokapi.model.PropertyDTO;
import be.lokapi.model.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-18T11:03:55+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (IBM Corporation)"
)
@Component
public class PropertyMapperImpl implements PropertyMapper {

    @Override
    public Property toEntity(PropertyDTO propertyDTO) {
        if ( propertyDTO == null ) {
            return null;
        }

        Property property = new Property();

        property.setOwner( userDTOToUser( propertyDTO.getOwner() ) );
        property.setId( propertyDTO.getId() );
        property.setAddress( propertyDTO.getAddress() );
        property.setCity( propertyDTO.getCity() );
        property.setZip( propertyDTO.getZip() );
        property.setDescription( propertyDTO.getDescription() );
        property.setPrice( propertyDTO.getPrice() );
        property.setCreationDate( propertyDTO.getCreationDate() );
        property.setUpdateDate( propertyDTO.getUpdateDate() );
        property.setDeleteDate( propertyDTO.getDeleteDate() );
        property.setLease( leaseDTOToLease( propertyDTO.getLease() ) );

        return property;
    }

    protected RolesEnum rolesEnumToRolesEnum(UserDTO.RolesEnum rolesEnum) {
        if ( rolesEnum == null ) {
            return null;
        }

        RolesEnum rolesEnum1;

        switch ( rolesEnum ) {
            case OWNER: rolesEnum1 = RolesEnum.OWNER;
            break;
            case TENANT: rolesEnum1 = RolesEnum.TENANT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + rolesEnum );
        }

        return rolesEnum1;
    }

    protected List<RolesEnum> rolesEnumListToRolesEnumList(List<UserDTO.RolesEnum> list) {
        if ( list == null ) {
            return null;
        }

        List<RolesEnum> list1 = new ArrayList<RolesEnum>( list.size() );
        for ( UserDTO.RolesEnum rolesEnum : list ) {
            list1.add( rolesEnumToRolesEnum( rolesEnum ) );
        }

        return list1;
    }

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setEmail( userDTO.getEmail() );
        user.setPassword( userDTO.getPassword() );
        user.setActive( userDTO.getActive() );
        user.setCreationDate( userDTO.getCreationDate() );
        user.setUpdateDate( userDTO.getUpdateDate() );
        user.setDeleteDate( userDTO.getDeleteDate() );
        user.setRoles( rolesEnumListToRolesEnumList( userDTO.getRoles() ) );

        return user;
    }

    protected Lease leaseDTOToLease(LeaseDTO leaseDTO) {
        if ( leaseDTO == null ) {
            return null;
        }

        Lease lease = new Lease();

        lease.setId( leaseDTO.getId() );
        lease.setProperty( toEntity( leaseDTO.getProperty() ) );
        lease.setTenant( userDTOToUser( leaseDTO.getTenant() ) );
        lease.setOwner( userDTOToUser( leaseDTO.getOwner() ) );
        lease.setStartDate( leaseDTO.getStartDate() );
        lease.setEndDate( leaseDTO.getEndDate() );
        lease.setRentAmount( leaseDTO.getRentAmount() );
        lease.setDepositAmount( leaseDTO.getDepositAmount() );
        lease.setContract( leaseDTO.getContract() );
        lease.setCreationDate( leaseDTO.getCreationDate() );
        lease.setUpdateDate( leaseDTO.getUpdateDate() );
        lease.setDeleteDate( leaseDTO.getDeleteDate() );

        return lease;
    }
}
