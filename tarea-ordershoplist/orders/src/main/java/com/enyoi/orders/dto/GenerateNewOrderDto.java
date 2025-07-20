package com.enyoi.orders.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenerateNewOrderDto {
    private String clientEmail;
    private List<ProductShop> productShopList;
}
