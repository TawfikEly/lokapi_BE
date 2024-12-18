package be.lokapi.mapping;

import be.lokapi.entity.User;
import be.lokapi.enums.RolesEnum;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.username( user.getUsername() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );
        userDTO.roles( mapRoles( user.getRoles() ) );
        userDTO.active( user.getActive() );
        userDTO.creationDate( user.getCreationDate() );
        userDTO.updateDate( user.getUpdateDate() );
        userDTO.deleteDate( user.getDeleteDate() );

        return userDTO.build();
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setEmail( userDTO.getEmail() );
        user.setPassword( userDTO.getPassword() );
        user.setRoles( rolesEnumListToRolesEnumList( userDTO.getRoles() ) );
        user.setActive( userDTO.getActive() );
        user.setCreationDate( userDTO.getCreationDate() );
        user.setUpdateDate( userDTO.getUpdateDate() );
        user.setDeleteDate( userDTO.getDeleteDate() );

        return user;
    }

    @Override
    public List<UserDTO.RolesEnum> mapRoles(List<RolesEnum> roles) {
        if ( roles == null ) {
            return null;
        }

        List<UserDTO.RolesEnum> list = new ArrayList<UserDTO.RolesEnum>( roles.size() );
        for ( RolesEnum rolesEnum : roles ) {
            list.add( rolesEnumToRolesEnum1( rolesEnum ) );
        }

        return list;
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

    protected UserDTO.RolesEnum rolesEnumToRolesEnum1(RolesEnum rolesEnum) {
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
}
