package org.microservice.items.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Product product;
    private Integer quantity;

    public double getTotal(){
        return product == null ? 0 : product.getPrice() * quantity;
    }

}