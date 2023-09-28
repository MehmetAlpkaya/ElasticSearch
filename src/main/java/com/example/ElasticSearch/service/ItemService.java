package com.example.ElasticSearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.JsonData;
import com.example.ElasticSearch.model.Item;
import com.example.ElasticSearch.repository.ItemRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j// for logging
@RequiredArgsConstructor
public class ItemService
{
    private final ItemRepository itemRepository;
    private final JsonDataService jsonDataService;
    private final ElasticsearchClient elasticsearchClient; // tüm sorgularımızı yapacağımız yer

    @PostMapping
    public Item itemCreate(@RequestBody Item item)
    {
        return itemRepository.save(item);
    }

}
