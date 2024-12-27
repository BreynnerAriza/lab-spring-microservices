package org.microservice.product.controllers;

import lombok.RequiredArgsConstructor;
import org.microservice.product.entities.Product;
import org.microservice.product.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> getProductById(@PathVariable Long idProduct) throws InterruptedException {
        if(idProduct == 10){
            throw new IllegalStateException("Producto no encontrado");
        }
        if(idProduct == 7){
            TimeUnit.SECONDS.sleep(5L);
        }

        Product product = productService.getProductById(idProduct);
        System.out.println("product = " + product);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(idProduct));
    }

}
