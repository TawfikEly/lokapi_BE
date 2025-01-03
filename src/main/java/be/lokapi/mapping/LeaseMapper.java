package be.lokapi.mapping;

import be.lokapi.entity.Lease;
import be.lokapi.model.LeaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaseMapper {

    LeaseMapper INSTANCE = Mappers.getMapper(LeaseMapper.class);

    @Mapping(source = "tenant.id", target = "tenant.id")
    @Mapping(source = "owner.id", target = "owner.id")
    @Mapping(target = "property", ignore = true)
    LeaseDTO toDto(Lease lease);

    @Mapping(source = "tenant.id", target = "tenant.id")
    @Mapping(source = "owner.id", target = "owner.id")
    Lease toEntity(LeaseDTO leaseDTO);
}