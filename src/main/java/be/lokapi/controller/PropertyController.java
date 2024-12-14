package be.lokapi.controller;

import be.lokapi.api.PropertiesApi;
import be.lokapi.entity.Property;
import be.lokapi.mapping.PropertyMapper;
import be.lokapi.model.GetAddressPropertyByOwnerIdDefaultResponseInnerDTO;
import be.lokapi.model.PropertyDTO;
import be.lokapi.service.IPropertyService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController implements PropertiesApi {

    private final IPropertyService propertyService;


    public PropertyController(IPropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    @PostMapping("/createProperty")
    public ResponseEntity<PropertyDTO> createProperty(PropertyDTO dto) {

        PropertyDTO propertyDTO = propertyService.createProperty(dto);

        if(propertyDTO!=null)
            return ResponseEntity.ok(propertyDTO);
        return ResponseEntity.badRequest().build();
    }



    @Override
    @GetMapping("/getAllProperties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        return (ResponseEntity<List<PropertyDTO>>) propertyService.getAllProperties();
    }

    @Override
    @GetMapping("/getPropertyById/{propertyId}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long propertyId) {

        PropertyDTO propertyDTO = propertyService.getPropertyById(propertyId);

        if (propertyDTO!=null)
            return ResponseEntity.ok(propertyDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/getPropertyWithLeaseById/{propertyId}/lease")
    public ResponseEntity<PropertyDTO> getPropertyWithLeaseById(@PathVariable Long propertyId) {
            //TODO a corriger cette hitoire de mapping impossible de copie vers le dto

            PropertyDTO propertyDTO = propertyService.getPropertyWithLeaseById(propertyId);

            if (propertyDTO!=null)
                return ResponseEntity.ok(propertyDTO);
            return ResponseEntity.badRequest().build();
    }

    @Override
    @GetMapping("/getPropertyByOwnerId/{ownerId}")
    public ResponseEntity<List<PropertyDTO>> getPropertyByOwnerId(@PathVariable Long ownerId) {
        List<PropertyDTO> propertiesDTO = propertyService.getPropertyByOwnerId(ownerId);
        if (propertiesDTO!=null)
            return ResponseEntity.ok(propertiesDTO);
        return ResponseEntity.badRequest().build();
    }

    @Override
    @GetMapping("/getAddressPropertyByOwnerId/{ownerId}/addresses")
    public ResponseEntity<List<GetAddressPropertyByOwnerIdDefaultResponseInnerDTO>> getAddressPropertyByOwnerId(Long ownerId) {

        List<GetAddressPropertyByOwnerIdDefaultResponseInnerDTO> addressesDTO = propertyService.getAddressPropertyByOwnerId(ownerId);
        if (addressesDTO != null && !addressesDTO.isEmpty()) {
            return ResponseEntity.ok(addressesDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PropertyDTO> updateProperty(Long propertyId) {
        PropertyDTO propertyDTO = propertyService.updatePropertyById(propertyId);
        if (propertyDTO!=null)
            return ResponseEntity.ok(propertyDTO);
        return ResponseEntity.badRequest().build();
    }
    @Override
    public ResponseEntity<PropertyDTO> deleteProperty(Long propertyId) {
        PropertyDTO propertyDTO = propertyService.deletePropertyById(propertyId);
        if (propertyDTO!=null)
            return ResponseEntity.ok(propertyDTO);
        return ResponseEntity.badRequest().build();
    }


}
