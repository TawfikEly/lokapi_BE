package be.lokapi.service;

import be.lokapi.entity.Property;
import be.lokapi.model.GetAddressPropertyByOwnerIdDefaultResponseInnerDTO;
import be.lokapi.model.PropertyDTO;

import java.util.List;

public interface IPropertyService {

    PropertyDTO createProperty(PropertyDTO propertyDTO);

    Object getAllProperties();

    PropertyDTO getPropertyById(Long propertyId);

    List<PropertyDTO> getPropertyByOwnerId(Long ownerId);
    List<GetAddressPropertyByOwnerIdDefaultResponseInnerDTO> getAddressPropertyByOwnerId(Long ownerId);

    PropertyDTO updatePropertyById(Long propertyId);

    void deletePropertyById(Long propertyId);
    public PropertyDTO updateProperty(PropertyDTO propertyDTO);
    void deleteProperty(PropertyDTO propertyDTO);

    PropertyDTO getPropertyWithLeaseById(Long propertyId);
}
