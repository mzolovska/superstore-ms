package com.superstore.clientservice.datamapperlayer;


import com.superstore.clientservice.datalayer.Client;
import com.superstore.clientservice.presentationlayer.ClientResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ClientResponseMapper {

    @Mapping(expression = "java(client.getClientIdentifier().getClientId())", target = "clientId")

    ClientResponseModel entityToResponseModel(Client client);

    List<ClientResponseModel> entityListToResponseModelList(List<Client> clients);

    /*@AfterMapping
    default void addLinks(@MappingTarget ClientResponseModel model, Client client) {
        //self link
        model.add(linkTo(methodOn(ClientController.class)
                .getClientByClientId(model.getClientId()))
                .withSelfRel());


        model.add(linkTo(methodOn(ClientController.class).getClients()).withRel("clients"));
    }*/
}
