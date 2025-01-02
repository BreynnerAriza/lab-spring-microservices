package org.microservice.product.services;


import org.microservice.commond.libs.entities.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(long id);
    List<Product> getAllProducts();

}
