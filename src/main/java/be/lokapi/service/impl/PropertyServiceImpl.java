package be.lokapi.service.impl;

import be.lokapi.entity.Property;
import be.lokapi.mapping.PropertyMapper;
import be.lokapi.model.GetAddressPropertyByOwnerIdDefaultResponseInnerDTO;
import be.lokapi.model.PropertyDTO;
import be.lokapi.repository.IPropertyRepository;
import be.lokapi.service.IPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements IPropertyService {
    private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

    private final PropertyMapper propertyMapper;
    private IPropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyMapper propertyMapper, IPropertyRepository propertyRepository) {
        this.propertyMapper = propertyMapper;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {

        propertyDTO.setCreationDate(LocalDate.now());
        propertyDTO.setUpdateDate(LocalDate.now());
        Property property = propertyMapper.toEntity(propertyDTO);
        Property savedProperty = propertyRepository.save(property);

        return propertyMapper.toDto(savedProperty);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public PropertyDTO getPropertyById(Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElse(null);

        PropertyDTO propertyDTO = propertyMapper.toDto(property);
        return propertyDTO;

    }

    @Override
    public List<PropertyDTO> getPropertyByOwnerId(Long ownerId) {
        return propertyRepository.getPropertyByOwnerId(ownerId).stream()
                        .map(propertyMapper::toDto)
                                .collect(Collectors.toList());
    }

    // Récupérer uniquement les adresses des propriétés d'un propriétaire
    @Override
    public List<GetAddressPropertyByOwnerIdDefaultResponseInnerDTO> getAddressPropertyByOwnerId(Long ownerId) {
        return propertyRepository.getAddressPropertyByOwnerId(ownerId)
                .stream()
                .map(record -> new GetAddressPropertyByOwnerIdDefaultResponseInnerDTO(record.getId(),record.getAddress(), record.getCity(), record.getZip()))
                .toList();
    }

    public PropertyDTO getPropertyWithLeaseById(Long propertyId) {
        // TODO A REVOIR
       Optional<Property> property = propertyRepository.getPropertyWithLeaseById(propertyId);
        if(property.isPresent()){

            PropertyDTO propertyDTO = propertyMapper.toDto(property.get());
            return propertyDTO;
        }else{
            return null;
        }


        /*return propertyRepository.getPropertyWithLeaseById(propertyId)
                .map(propertyMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));*/
    }



    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO) {
        propertyDTO.setUpdateDate(LocalDate.now());
        Property property = propertyMapper.toEntity(propertyDTO);
        Property propertyUpdated = propertyRepository.save(property);
        return  propertyMapper.toDto(propertyUpdated);
    }
    @Override
    public PropertyDTO updatePropertyById(Long propertyId) {
        PropertyDTO propertyDTO = getPropertyById(propertyId);
        return updateProperty(propertyDTO);
    }
    @Override
    public void deleteProperty(PropertyDTO propertyDTO) {
        propertyDTO.setUpdateDate(LocalDate.now());
        propertyDTO.setDeleteDate(LocalDate.now());
        Property property = propertyMapper.toEntity(propertyDTO);
        propertyRepository.delete(property);

    }

    @Override
    public void deletePropertyById(Long propertyId) {
        PropertyDTO propertyDTO = getPropertyById(propertyId);
        deleteProperty(propertyDTO);
    }
}
