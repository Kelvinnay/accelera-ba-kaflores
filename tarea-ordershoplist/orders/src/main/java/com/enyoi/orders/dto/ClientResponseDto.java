package com.enyoi.orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) //CAULQUIER CAMPO QUE NO CONOZCA SIMPLEMENTE IGNORE
public class ClientResponseDto {
    String id;
//    @JsonProperty("email")// EN EL JSON ESTE CAMPO SE LLAMA EMAIL
//    String mail; //PERO PARA MI, LO LLAMO MAIL

}
