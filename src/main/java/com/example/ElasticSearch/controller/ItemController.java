package com.example.ElasticSearch.controller;

import com.example.ElasticSearch.model.Item;
import com.example.ElasticSearch.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController
{
private final ItemService itemService;


@PostMapping
    public Item createItem(@RequestBody Item item)
{
    return itemService.itemCreate(item);
}

@PostMapping("/init-index")
    public Item addItemFromJson()
{
    return itemService.addItemFromJson();
}

    @GetMapping("/getAllData/{indexName}")
    public List<Item> getAllData(@PathVariable String indexName)
    {

        return itemService.getAllDataFromIndex(indexName);
    }
}
