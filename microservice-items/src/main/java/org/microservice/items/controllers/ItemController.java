package org.microservice.items.controllers;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.microservice.commond.libs.entities.Product;
import org.microservice.items.models.Item;
import org.microservice.items.services.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/items")
@RefreshScope
public class ItemController {

    private final IItemService itemService;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private final Environment environment;

    @Value("${configuracion.texto}")
    private String text;

    public ItemController(
            @Qualifier("itemServiceWebClient") IItemService itemService,
            CircuitBreakerFactory circuitBreakerFactory,
            Environment enviroment
    ) {
        this.itemService = itemService;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.environment = enviroment;
    }

    @GetMapping("/fetch-config")
    public ResponseEntity<Map<String, String>> getConfigs(){
        Map<String, String> configs = new HashMap<>();
        configs.put("Texto", text);

        if(environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equals("dev")){
            configs.put("configuracion.autor.nombre", environment.getProperty("configuracion.autor.nombre"));
            configs.put("configuracion.autor.email", environment.getProperty("configuracion.autor.email"));
        }
        return ResponseEntity.ok(configs);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) {
        Item item = circuitBreakerFactory.create("items").run(() ->itemService.getItemById(id), e -> {
           Product product = new Product(id, "Producto NN", 80D, 8003);
           return new Item(product, 0);
        });
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @CircuitBreaker(name = "items", fallbackMethod = "getFallBackMethodProduct") //Solo sirve para la configuration realizada en el application yml PARA ELL0 ES NECESARIO LA DEPENDENCIA AOP
    @GetMapping("/declarative/{id}")
    public ResponseEntity<Item> getItemByIDeclarative(@PathVariable("id") Long id) {
        Item item = itemService.getItemById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @GetMapping("/declarative-time/{id}")
    @CircuitBreaker(name = "items")
    @TimeLimiter(name = "items", fallbackMethod = "getFallBackMethodProductTime")
    public CompletableFuture<ResponseEntity<Item>> getItemByIDeclarativeTime(@PathVariable("id") Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Item item = itemService.getItemById(id);
            if (item == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(item);
        });
    }

    public ResponseEntity<Item> getFallBackMethodProduct(Throwable throwable) {
        Product product = new Product(1L, "Producto NN DECLARATIVE", 80D, 8003);
        Item item = new Item(product, 0);
        return ResponseEntity.ok(item);
    }

    public CompletableFuture<ResponseEntity<Item>> getFallBackMethodProductTime(Throwable throwable) {
        return CompletableFuture.supplyAsync(() -> {
            Product product = new Product(1L, "Producto NN TIME", 80D, 8003);
            Item item = new Item(product, 0);
            return ResponseEntity.ok(item);
        });
    }

}
