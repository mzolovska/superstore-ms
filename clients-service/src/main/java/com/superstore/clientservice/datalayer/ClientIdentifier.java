package com.superstore.clientservice.datalayer;

import jakarta.persistence.Embeddable;

import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class ClientIdentifier {

    //private UUID clientId;
    public String clientId;


    ClientIdentifier() {
        this.clientId = UUID.randomUUID().toString();
    }

    public ClientIdentifier(String clientId) {
        this.clientId=clientId;
    }

}
