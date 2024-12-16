package org.microservice.items.services;

import org.microservice.items.models.Item;

import java.util.List;

public interface IItemService {

    List<Item> getAllItems();
    Item getItemById(long id);

}
