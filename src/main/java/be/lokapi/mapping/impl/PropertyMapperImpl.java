package be.lokapi.mapping.impl;
/*
import be.lokapi.entity.Property;
import be.lokapi.entity.User;
import be.lokapi.mapping.PropertyMapper;
import be.lokapi.model.PropertyDTO;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapperImpl implements PropertyMapper {

    @Override
    public Property toEntity(PropertyDTO propertyDTO) {
        if (propertyDTO == null) {
            return null;
        }

        Property property = new Property();

        property.setId(propertyDTO.getId());
        property.setAddress(propertyDTO.getAddress());
        property.setCity(propertyDTO.getCity());
        property.setZip(propertyDTO.getZip());
        property.setDescription(propertyDTO.getDescription());
        property.setCreationDate(propertyDTO.getCreationDate());
        property.setUpdateDate(propertyDTO.getUpdateDate());
        property.setDeleteDate(propertyDTO.getDeleteDate());

        // Mapper ownerId vers owner
        User owner = new User();
        owner.setId(propertyDTO.getOwnerId());
        property.setOwner(owner);

        return property;
    }

    @Override
    public PropertyDTO toDto(Property property) {
        if (property == null) {
            return null;
        }

        PropertyDTO.PropertyDTOBuilder propertyDTO = PropertyDTO.builder();

        propertyDTO.id(property.getId());
        propertyDTO.address(property.getAddress());
        propertyDTO.city(property.getCity());
        propertyDTO.zip(property.getZip());
        propertyDTO.description(property.getDescription());
        propertyDTO.creationDate(property.getCreationDate());
        propertyDTO.updateDate(property.getUpdateDate());
        propertyDTO.deleteDate(property.getDeleteDate());

        // Mapper owner vers ownerId
        User owner = property.getOwner();
        if (owner != null) {
            propertyDTO.ownerId(owner.getId());
        }

        return propertyDTO.build();
    }
}
*/