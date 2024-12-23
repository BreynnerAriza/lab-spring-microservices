package org.microservice.items.services;

import lombok.RequiredArgsConstructor;
import org.microservice.items.clients.IProductFeignClient;
import org.microservice.items.models.Item;
import org.microservice.items.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("itemServiceFeign")
@RequiredArgsConstructor
public class ItemServiceFeign implements IItemService {

    private final IProductFeignClient productFeignClient;
    private final Random random = new Random();

    @Override
    public List<Item> getAllItems() {
        return productFeignClient.getAllProducts().stream()
                .map(p -> new Item(p, random.nextInt(10) + 1))
                .toList();
    }

    @Override
    public Item getItemById(long id) {
        Product product = productFeignClient.getProductById(id);
        return new Item(product, random.nextInt(10) + 1);
    }
}
