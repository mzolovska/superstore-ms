package com.superstore.clientservice.businesslayer;

import com.superstore.clientservice.datalayer.Client;
import com.superstore.clientservice.datalayer.ClientRepository;
import com.superstore.clientservice.datamapperlayer.ClientRequestMapper;
import com.superstore.clientservice.datamapperlayer.ClientResponseMapper;
import com.superstore.clientservice.presentationlayer.ClientRequestModel;
import com.superstore.clientservice.presentationlayer.ClientResponseModel;
import com.superstore.clientservice.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientResponseMapper clientResponseMapper;
    private ClientRequestMapper clientRequestMapper;


    public ClientServiceImpl(ClientRepository clientRepository, ClientResponseMapper clientResponseMapper, ClientRequestMapper clientRequestMapper) {
        this.clientRepository = clientRepository;
        this.clientResponseMapper = clientResponseMapper;
        this.clientRequestMapper = clientRequestMapper;
    }

    @Override
    public List<ClientResponseModel> getClients() {
        List<Client> clients = clientRepository.findAll();
        return clientResponseMapper.entityListToResponseModelList(clients);
    }

    @Override
    public ClientResponseModel getClientByClientId(String clientId) {
        //we can change this later to throw an exception if the client is not found, which will return a different HTTP status code
        Client client = clientRepository.findByClientIdentifier_ClientId(clientId);

        if(client == null){
            throw new NotFoundException("Unknown cllient: "+clientId);
        }
        return clientResponseMapper.entityToResponseModel(client);
    }

    @Override
    public ClientResponseModel addClient(ClientRequestModel clientRequestModel) {
        Client client = clientRequestMapper.requestModelToEntity(clientRequestModel);

        return clientResponseMapper.entityToResponseModel(clientRepository.save(client));
    }

    @Override
    public ClientResponseModel updateClient(ClientRequestModel clientRequestModel, String clientId) {

        Client existingClient = clientRepository.findByClientIdentifier_ClientId(clientId);

        //check if a client with the provided UUID exists in the system. If it doesn't, return null
        //later, when we implement exception handling, we'll return an exception
        if (existingClient == null) {
            throw new NotFoundException("Unknown clientId: " + clientId);
        }
        Client updatedClient = clientRequestMapper.requestModelToEntity(clientRequestModel);
        updatedClient.setId(existingClient.getId());
        updatedClient.setClientIdentifier(existingClient.getClientIdentifier());


        Client response = clientRepository.save(updatedClient);
        return clientResponseMapper.entityToResponseModel(response);
    }

    @Override
    public void removeClient(String clientId) {
        Client existingClient = clientRepository.findByClientIdentifier_ClientId(clientId);

        if (existingClient == null) {
            throw new NotFoundException("Unknown clientId: " + clientId);
        }

        clientRepository.delete(existingClient);
    }
}
