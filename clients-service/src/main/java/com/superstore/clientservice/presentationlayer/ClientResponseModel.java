package com.superstore.clientservice.presentationlayer;

import lombok.*;


@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public  class ClientResponseModel {

    private final String clientId;
    private final String firstName;
    private final String lastName;
    private final String purchase;

}
