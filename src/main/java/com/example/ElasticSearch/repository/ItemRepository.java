package com.example.ElasticSearch.repository;

import com.example.ElasticSearch.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


public interface ItemRepository extends ElasticsearchRepository<Item,String>
{


}
