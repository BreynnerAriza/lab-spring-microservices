package org.microservice.items.services;

import lombok.RequiredArgsConstructor;
import org.microservice.items.models.Item;
import org.microservice.items.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service("itemServiceWebClient")
@RequiredArgsConstructor
public class ItemServiceWebClient implements IItemService{

    @Value("${config.urlbase.webclient.product}")
    private String urlBase;

    private final WebClient.Builder webClientBuilder;
    private final Random random = new Random();

    @Override
    public List<Item> getAllItems() {
        return webClientBuilder.build()
                .get()
                .uri(urlBase + "/api/v1/product")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class)
                .map(p -> new Item(p, random.nextInt(10) + 1))
                .collectList()
                .block();
    }

    @Override
    public Item getItemById(long id) {
        Map<String, Long> params = Map.of("id", id);
        return webClientBuilder.build()
                .get()
                .uri(urlBase + "/api/v1/product/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class)
                .map(p -> new Item(p, random.nextInt(10) + 1))
                .block();
    }
}
