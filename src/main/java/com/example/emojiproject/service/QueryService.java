package com.example.emojiproject.service;

import com.example.emojiproject.model.Emoji;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QueryService {

    @Autowired
    ElasticsearchRestTemplate elasticsearchTemplate;

    public List<Emoji> getEmojis(String searchText)
    {
        Map<String,Float> fields1 = new HashMap<String,Float>();
        fields1.put("description",1f);
        fields1.put("aliases",3f);
        fields1.put("tags",4f);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(searchText+"*").fields(fields1))
                .build();

        SearchHits<Emoji> output =
                elasticsearchTemplate.search(searchQuery, Emoji.class, IndexCoordinates.of("emojis"));


        return output.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

}
