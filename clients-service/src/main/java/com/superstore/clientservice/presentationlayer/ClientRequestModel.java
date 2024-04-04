package com.superstore.clientservice.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientRequestModel {

    private final String firstName;
    private final String lastName;
    private final String purchase;

}
