package com.example.ElasticSearch.service;

import com.example.ElasticSearch.model.Item;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class JsonDataService
{
    private final ObjectMapper objectMapper;

    public JsonDataService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Item> readItemFromJson()
    {
        try
        {
            ClassPathResource resource =new ClassPathResource("data/item.json");
            InputStream inputStream=resource.getInputStream();
            return objectMapper.readValue(inputStream, new TypeReference<List<Item>>() {
            });
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
