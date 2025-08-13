package com.enyoi.products.service;

import com.enyoi.products.model.Product;
import com.enyoi.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product saveNewProduct(String name){
        Product newProduct = new Product();
        newProduct.setStock(100);
        newProduct.setName(name);
        return productRepository.save(newProduct);
    }

    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    public Product updateStock(String id, Integer quantitySold){
        Product product= productRepository.findById(id).get();
        product.setStock(product.getStock() - quantitySold);
        return productRepository.save(product);

    }
}
