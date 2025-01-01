package be.lokapi.controller;

import be.lokapi.api.PropertiesApi;
import be.lokapi.model.GetAddressPropertyByOwnerIdDefaultResponseInnerDTO;
import be.lokapi.model.PropertyDTO;
import be.lokapi.service.IPropertyService;
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
    public ResponseEntity<PropertyDTO> deleteProperty(PropertyDTO propertyDTO) {
        return null;
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
    @PutMapping("/updatePropertyById/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyById(@PathVariable Long propertyId) {
        PropertyDTO propertyDTOUpdated = propertyService.updatePropertyById(propertyId);
        if (propertyDTOUpdated!=null)
            return ResponseEntity.ok(propertyDTOUpdated);
        return ResponseEntity.badRequest().build();
    }

    @Override
    @PutMapping("/updateProperty")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO) {
        PropertyDTO propertyDTOUpdated = propertyService.updateProperty(propertyDTO);
        if (propertyDTOUpdated!=null)
            return ResponseEntity.ok(propertyDTOUpdated);
        return ResponseEntity.badRequest().build();
    }




    @Override
    @DeleteMapping("/deletePropertyById/{propertyId}")
    public ResponseEntity<Void> deletePropertyById(@PathVariable Long propertyId) {
        propertyService.deletePropertyById(propertyId);
        return ResponseEntity.ok(null);
    }


}
