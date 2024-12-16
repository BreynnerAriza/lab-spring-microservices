package org.microservice.items.clients;

import org.microservice.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-product", path ="/api/v1/product" )
public interface IProductFeignClient {

    @GetMapping
    List<Product> getAllProducts();

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id);

}
