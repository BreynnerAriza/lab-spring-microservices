package org.microservice.product.services;

import lombok.AllArgsConstructor;
import org.microservice.product.entities.Product;
import org.microservice.product.repositories.ProductRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductService implements IProductService {

    private final Environment environment;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> {
                    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    return product;
                })
                .toList();
    }

}
