package com.enyoi.orders.service;

import com.enyoi.orders.config.EnvsFacade;
import com.enyoi.orders.dto.ClientResponseDto;
import com.enyoi.orders.dto.GenerateNewOrderDto;
import com.enyoi.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    RestTemplate restTemplate;

    @Mock
    EnvsFacade envsFacade;

    @InjectMocks
    OrderService orderService;

    @Test
    void shouldCreateNewOrder(){
        //Arranfge
        when(envsFacade.getClientHostEnv()).thenReturn("localhost:8080");

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null),
                eq(ClientResponseDto.class))).thenReturn(getInstanceOfResponse());
        //Acta
        orderService.createNewOrder(getDtoInstance());

        verify(orderRepository).save(any());
        //verify(orderRepository,atLeastOnce()).save(any());
        //verify(orderRepository,times(1)).save(any());
    }

    @Test
    void shouldThrowAnException(){
        when(envsFacade.getClientHostEnv()).thenThrow(new RuntimeException("ERROR"));
        assertThrows(RuntimeException.class,()->orderService.createNewOrder(getDtoInstance()));
        verifyNoInteractions(orderRepository);
    }

    private GenerateNewOrderDto getDtoInstance() {
        GenerateNewOrderDto dto = new GenerateNewOrderDto();
        dto.setClientEmail("prueba@gmail.com");
        // Add other necessary fields for GenerateNewOrderDto if your OrderService needs them
        // For example:
        // dto.setProductId("prod123");
        // dto.setQuantity(2);
        return dto;
    }

    private ResponseEntity<ClientResponseDto> getInstanceOfResponse() {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setId("123");
        // Add other necessary fields for ClientResponseDto if your OrderService uses them
        // For example:
        // dto.setName("Test Client");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
