package com.superstore.clientservice.datalayer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //private identifier


    @Embedded
    private ClientIdentifier clientIdentifier; //public identifier

    private String firstName;
    private String lastName;
    private String purchase;


    public Client() {
        this.clientIdentifier = new ClientIdentifier();
    }

}
