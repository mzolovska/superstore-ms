package com.superstore.clientservice.presentationlayer;


import com.superstore.clientservice.businesslayer.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientResponseModel>> getClients() {
        return ResponseEntity.status(HttpStatus.FOUND).body(clientService.getClients());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseModel> getClientByClientId(@PathVariable String clientId) {
        //log.debug("clientId is:" + clientId);
        return ResponseEntity.status(HttpStatus.FOUND).body(clientService.getClientByClientId(clientId));
    }

    @PostMapping()
    public ResponseEntity<ClientResponseModel> addClient(@RequestBody ClientRequestModel clientRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.addClient(clientRequestModel));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponseModel> updateClient(@RequestBody ClientRequestModel clientRequestModel, @PathVariable String clientId) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(clientRequestModel, clientId));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<ClientResponseModel> deleteClient(@PathVariable String clientId) {
       clientService.removeClient(clientId);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
