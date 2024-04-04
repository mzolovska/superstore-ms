package com.superstore.clientservice.datamapperlayer;

import com.superstore.clientservice.datalayer.Client;
import com.superstore.clientservice.presentationlayer.ClientRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClientRequestMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "clientIdentifier", ignore = true),
    })
    Client requestModelToEntity(ClientRequestModel clientRequestModel);
}
