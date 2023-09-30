package com.example.ElasticSearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.ElasticSearch.model.Item;
import com.example.ElasticSearch.repository.ItemRepository;
import com.example.ElasticSearch.util.ESUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j// for logging
@RequiredArgsConstructor
public class ItemService
{
    private final ItemRepository itemRepository;
    private final JsonDataService jsonDataService;
    private final ElasticsearchClient elasticsearchClient; // tüm sorgularımızı yapacağımız yer


    public Item itemCreate(Item item)
    {
        return itemRepository.save(item);
    }

    public Item addItemFromJson()
    {
        log.info("adding items from json");
        List<Item> itemList=jsonDataService.readItemFromJson();
        itemRepository.saveAll(itemList); // En baştan beri ES eklemediysek bile sonradan saveAll() ile ekleyebiliriz
        return null;
    }

    public List<Item> getAllDataFromIndex(String indexName)// var dönüş tipi belirsiz olan
    {
        var query = ESUtil.matchIndex(indexName);
        log.info("ES query {}", query.toString());
        SearchResponse<Item> response=null;
        try {
            response=elasticsearchClient.search( //
                    q->q.index(indexName).query(query), Item.class// istediğimiz index'e git.
            );//Es client respons tipidir
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("ES response {}",response);
        return extractItemFromResponse(response);
    }
    public List<Item> extractItemFromResponse(SearchResponse<Item> response )
    {
        return response
            .hits()// Eşleşen veriler hits'e gelir
            .hits()// Bir ana başlık olarak düşünürsek bu hits() ise ana başlık altında bir başlık
            .stream()
            .map(Hit::source)
            .collect(Collectors.toList());

    }
}
