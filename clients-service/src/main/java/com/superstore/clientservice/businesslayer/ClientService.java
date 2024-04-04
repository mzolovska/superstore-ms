package com.superstore.clientservice.businesslayer;


import com.superstore.clientservice.presentationlayer.ClientRequestModel;
import com.superstore.clientservice.presentationlayer.ClientResponseModel;

import java.util.List;

public interface ClientService {

    List<ClientResponseModel> getClients();
    ClientResponseModel getClientByClientId(String clientId);
    ClientResponseModel addClient(ClientRequestModel clientRequestModel);
    ClientResponseModel updateClient(ClientRequestModel updatedClient, String clientId);
    void removeClient(String clientId);
}
