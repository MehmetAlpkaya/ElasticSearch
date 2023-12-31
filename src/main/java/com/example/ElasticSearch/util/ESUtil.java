package com.example.ElasticSearch.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ESUtil
{

    public static Query matchIndex(String indexName) {
        return Query.of(q -> q.matchAll(new MatchAllQuery.Builder().build()));
    }
}
