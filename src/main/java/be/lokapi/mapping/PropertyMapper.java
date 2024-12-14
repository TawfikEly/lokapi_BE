package be.lokapi.mapping;

import be.lokapi.entity.Property;
import be.lokapi.entity.User;
import be.lokapi.model.PropertyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    //@Mapping(source = "owner", target = "owner")
    //PropertyDTO toDto(Property property);

    @Mapping(source = "owner", target = "owner")
    Property toEntity(PropertyDTO propertyDTO);

    // Méthodes par défaut pour gérer les valeurs nulles
    default Long mapOwnerToOwnerId(User owner) {
        return owner != null ? owner.getId() : null;
    }

    default User mapOwnerIdToUser(Long ownerId) {
        if (ownerId == null) return null;
        User user = new User();
        user.setId(ownerId);
        return user;
    }

    default PropertyDTO toDto(Property property){
        if (property == null)
            return null;
        return PropertyDTO.builder()
                .id(property.getId())
                .address(property.getAddress())
                .city(property.getCity())
                .zip(property.getZip())
                .description(property.getDescription())
                .price(property.getPrice())
                .creationDate(property.getCreationDate())
                .updateDate(property.getUpdateDate())
                .deleteDate(property.getDeleteDate())
                .owner(UserMapper.INSTANCE.toDto(property.getOwner()))
                .lease(LeaseMapper.INSTANCE.toDto(property.getLease()))
                .build();

    }


}
