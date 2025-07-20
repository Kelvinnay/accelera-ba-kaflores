package com.enyoi.orders.service;

import com.enyoi.orders.config.EnvsFacade;
import com.enyoi.orders.dto.*;
import com.enyoi.orders.model.Order;
import com.enyoi.orders.model.ProductOrder;
import com.enyoi.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final EnvsFacade envsFacade;

    public Order createNewOrder(GenerateNewOrderDto dto){
        //GENERO LA URL
       // String url = envsFacade.getClientHostEnv() + envsFacade.getClientPath() + dto.getClientEmail();
       // log.info("URL CLIENTE --> "+ url);
        String clientId = getClientId(dto.getClientEmail());

        Order order = new Order();
        order.setClientId(clientId);

        List<ProductOrder> productOrderList = new ArrayList<>();
        for(ProductShop productShop: dto.getProductShopList()){
            ProductOrder productOrder = new ProductOrder();
            String productId = getProductId(productShop.getName());
            productOrder.setOrder(order);
            productOrder.setProductId(productId);
            productOrderList.add(productOrder);
            log.info("productId --> "+ productId);
            log.info("order --> "+ order);
            log.info("productOrder --> "+ productOrder);
        }

        order.setProductOrderList(productOrderList);

        //LLAMO AL MICROSERVICIO DE CLIENTES
        return orderRepository.save(order);
    }

    /*
    public Order createNewOrder(GenerateNewOrderDto dto){
        //GENERO LA URL
        String url = envsFacade.getClientHostEnv()+dto.getClientEmail();
        String clientId = getClientId(dto.getClientEmail());

        //LLAMO MS CLIENTE
        ResponseEntity<ClientResponseDto> responseEntityDto = restTemplate.exchange(
                url, HttpMethod.GET,null,ClientResponseDto.class
        );
        if(responseEntityDto.getStatusCode().is2xxSuccessful()){
            System.out.println("response "+responseEntityDto.getBody());
            ClientResponseDto bodyResponse = responseEntityDto.getBody();
            Order order = new Order();
            order.setClientId(bodyResponse.getId());
            orderRepository.save(order);
            return order;
        }

        throw new RuntimeException("Error");

        //0. Verifico que la respuesta fue correcta
        //1. obtener cliente
        //2. si clientegaurdo bien sino exception


        //return null;
    }
    //restTemplate.exchange()
    //1. Cual es su URL?  ---> http://localhost:8081/api/v1/client/email.com
    //2. Cual es el verbo ---> HttpMethod.GET
    //3. Cual es el contenido que va enviar   --> null
    //4. Cuales son los headers que va enviar  -->null
    //5. Cual es la respuesta esperada? ---> ClientResponseDto.class
*/


    private String getClientId(String clientEmail){
        String correlationId = getCorrelationId(); // Get correlation ID
        String url = envsFacade.getClientHostEnv() + envsFacade.getClientPath() + clientEmail;
        log.info("URL CLIENTE --> "+ url);
        ResponseEntity<ClientResponseDto> responseEntityDto = restTemplate.exchange(url, HttpMethod.GET, null, ClientResponseDto.class);

        log.info("Outgoing HTTP Request:  logType={}, targetService={}, httpMethod={}, requestUrl={}, clientEmail={}, correlationId={}",
                "outgoingHttpRequest", "ClientService", HttpMethod.GET.name(), url, clientEmail, correlationId
        );

        log.info("Outgoing HTTP Response: logType={}, targetService={}, httpMethod={}, requestUrl={},statusCode={}, correlationId={},responseBodySnippet={}",
                "outgoingHttpResponse", "ClientService", HttpMethod.GET.name(), url, responseEntityDto.getStatusCode().value(), correlationId,
                responseEntityDto.getBody() != null ? responseEntityDto.getBody().toString().substring(0, Math.min(responseEntityDto.getBody().toString().length(), 200)) : "N/A" // Snippet for safety
        );
        log.info("ClientResponseDto --> "+ responseEntityDto.getBody());
        return responseEntityDto.getBody().getId();
    }
    /**/

    private String getProductId(String productName){
        String correlationId = getCorrelationId(); // Get correlation ID
        String url = "http://localhost:8082/api/v1/product/" + productName;
        log.info("URL PRODUCT --> "+ url);
        ResponseEntity<ProductResponseDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                ProductResponseDto.class
        );
        log.info("Outgoing HTTP Request: logType={}, targetService={}, httpMethod={}, requestUrl={}, productName={}, correlationId={}",
                "outgoingHttpRequest",
                "ProductService",
                HttpMethod.GET.name(),
                url,
                productName,
                correlationId
        );
        log.info("Outgoing HTTP Response:  logType={}, targetService={}, httpMethod={}, requestUrl={},statusCode={}, correlationId={},responseBodySnippet={}",
                "outgoingHttpResponse",
                "ProductService",
                HttpMethod.GET.name(),
                url,
                responseEntity.getStatusCode().value(),
                correlationId,
                responseEntity.getBody() != null ? responseEntity.getBody().toString().substring(0, Math.min(responseEntity.getBody().toString().length(), 200)) : "N/A" // Snippet for safety
        );
        return responseEntity.getBody().getId();
    }


   public Order createNewOrderCreatingNewClient(CreateNewOrderCreatingNewClientDto dto){
        //GENERO LA URL
        String url = "http://localhost:8081/api/v1/client";
       log.info("URL PRODUCT --> %s", url);

        HttpEntity<CreateNewOrderCreatingNewClientDto> request = new HttpEntity<>(dto);
        System.out.println("DTO------->"+dto);

        //LLAMO A MS CLIENTES CON URL verbo post contenido y esperando ClientResponseDto
        ResponseEntity<ClientResponseDto> responseEntityDto = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                ClientResponseDto.class
                );
        ClientResponseDto bodyResponse = responseEntityDto.getBody();
        Order order = new Order();
        order.setClientId(bodyResponse.getId());
        orderRepository.save(order);
        return order;
        //1. Llamar al micro declientes para guardarle un nuevo cliente
        //2. crear la nueva orden
    }
    private String getCorrelationId() {
        // For demonstration, generating a new one. In production, prefer
        // to propagate one from the incoming request.
        return UUID.randomUUID().toString();
    }
}
