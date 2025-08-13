package com.enyoi.products.controller;

import com.enyoi.products.dto.SaveNewProductDto;
import com.enyoi.products.dto.UpdateProductDto;
import com.enyoi.products.model.Product;
import com.enyoi.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody SaveNewProductDto dto){
        Product response = productService.saveNewProduct(dto.getName());

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByname(@PathVariable("name") String email){
        Product response = productService.getProductByName(email);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody UpdateProductDto dto){
        Product response = productService.updateStock(id,dto.getQuantitySold());

        //return new ResponseEntity<>(response, HttpStatus.CREATED);
        return ResponseEntity.ok(response);// otra forma de retornar

    }
}

