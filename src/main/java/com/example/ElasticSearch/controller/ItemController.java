package com.example.ElasticSearch.controller;

import com.example.ElasticSearch.model.Item;
import com.example.ElasticSearch.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
