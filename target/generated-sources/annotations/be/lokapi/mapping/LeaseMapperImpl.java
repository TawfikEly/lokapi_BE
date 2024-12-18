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
public class LeaseMapperImpl implements LeaseMapper {

    @Override
    public LeaseDTO toDto(Lease lease) {
        if ( lease == null ) {
            return null;
        }

        LeaseDTO.LeaseDTOBuilder leaseDTO = LeaseDTO.builder();

        leaseDTO.tenant( userToUserDTO( lease.getTenant() ) );
        leaseDTO.owner( userToUserDTO1( lease.getOwner() ) );
        leaseDTO.id( lease.getId() );
        leaseDTO.startDate( lease.getStartDate() );
        leaseDTO.endDate( lease.getEndDate() );
        leaseDTO.rentAmount( lease.getRentAmount() );
        leaseDTO.depositAmount( lease.getDepositAmount() );
        leaseDTO.contract( lease.getContract() );
        leaseDTO.creationDate( lease.getCreationDate() );
        leaseDTO.deleteDate( lease.getDeleteDate() );
        leaseDTO.updateDate( lease.getUpdateDate() );

        return leaseDTO.build();
    }

    @Override
    public Lease toEntity(LeaseDTO leaseDTO) {
        if ( leaseDTO == null ) {
            return null;
        }

        Lease lease = new Lease();

        lease.setTenant( userDTOToUser( leaseDTO.getTenant() ) );
        lease.setOwner( userDTOToUser1( leaseDTO.getOwner() ) );
        lease.setId( leaseDTO.getId() );
        lease.setProperty( propertyDTOToProperty( leaseDTO.getProperty() ) );
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

    protected UserDTO.RolesEnum rolesEnumToRolesEnum(RolesEnum rolesEnum) {
        if ( rolesEnum == null ) {
            return null;
        }

        UserDTO.RolesEnum rolesEnum1;

        switch ( rolesEnum ) {
            case TENANT: rolesEnum1 = UserDTO.RolesEnum.TENANT;
            break;
            case OWNER: rolesEnum1 = UserDTO.RolesEnum.OWNER;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + rolesEnum );
        }

        return rolesEnum1;
    }

    protected List<UserDTO.RolesEnum> rolesEnumListToRolesEnumList(List<RolesEnum> list) {
        if ( list == null ) {
            return null;
        }

        List<UserDTO.RolesEnum> list1 = new ArrayList<UserDTO.RolesEnum>( list.size() );
        for ( RolesEnum rolesEnum : list ) {
            list1.add( rolesEnumToRolesEnum( rolesEnum ) );
        }

        return list1;
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.username( user.getUsername() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );
        userDTO.roles( rolesEnumListToRolesEnumList( user.getRoles() ) );
        userDTO.active( user.getActive() );
        userDTO.creationDate( user.getCreationDate() );
        userDTO.updateDate( user.getUpdateDate() );
        userDTO.deleteDate( user.getDeleteDate() );

        return userDTO.build();
    }

    protected UserDTO userToUserDTO1(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.username( user.getUsername() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );
        userDTO.roles( rolesEnumListToRolesEnumList( user.getRoles() ) );
        userDTO.active( user.getActive() );
        userDTO.creationDate( user.getCreationDate() );
        userDTO.updateDate( user.getUpdateDate() );
        userDTO.deleteDate( user.getDeleteDate() );

        return userDTO.build();
    }

    protected RolesEnum rolesEnumToRolesEnum1(UserDTO.RolesEnum rolesEnum) {
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

    protected List<RolesEnum> rolesEnumListToRolesEnumList1(List<UserDTO.RolesEnum> list) {
        if ( list == null ) {
            return null;
        }

        List<RolesEnum> list1 = new ArrayList<RolesEnum>( list.size() );
        for ( UserDTO.RolesEnum rolesEnum : list ) {
            list1.add( rolesEnumToRolesEnum1( rolesEnum ) );
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
        user.setRoles( rolesEnumListToRolesEnumList1( userDTO.getRoles() ) );

        return user;
    }

    protected User userDTOToUser1(UserDTO userDTO) {
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
        user.setRoles( rolesEnumListToRolesEnumList1( userDTO.getRoles() ) );

        return user;
    }

    protected User userDTOToUser2(UserDTO userDTO) {
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
        user.setRoles( rolesEnumListToRolesEnumList1( userDTO.getRoles() ) );

        return user;
    }

    protected Property propertyDTOToProperty(PropertyDTO propertyDTO) {
        if ( propertyDTO == null ) {
            return null;
        }

        Property property = new Property();

        property.setId( propertyDTO.getId() );
        property.setAddress( propertyDTO.getAddress() );
        property.setCity( propertyDTO.getCity() );
        property.setZip( propertyDTO.getZip() );
        property.setDescription( propertyDTO.getDescription() );
        property.setPrice( propertyDTO.getPrice() );
        property.setCreationDate( propertyDTO.getCreationDate() );
        property.setUpdateDate( propertyDTO.getUpdateDate() );
        property.setDeleteDate( propertyDTO.getDeleteDate() );
        property.setLease( toEntity( propertyDTO.getLease() ) );
        property.setOwner( userDTOToUser2( propertyDTO.getOwner() ) );

        return property;
    }
}
