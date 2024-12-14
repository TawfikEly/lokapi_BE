package be.lokapi.mapping;

import be.lokapi.entity.User;
import be.lokapi.enums.RolesEnum;
import be.lokapi.model.UserDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "updateDate", target = "updateDate")
    @Mapping(source = "deleteDate", target = "deleteDate")
    UserDTO toDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "updateDate", target = "updateDate")
    @Mapping(source = "deleteDate", target = "deleteDate")
    User toEntity(UserDTO userDTO);


    @IterableMapping(elementTargetType = UserDTO.RolesEnum.class)
    List<UserDTO.RolesEnum> mapRoles(List<RolesEnum> roles);

}
