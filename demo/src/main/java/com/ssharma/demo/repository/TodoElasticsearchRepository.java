package com.ssharma.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ssharma.demo.TodoElasticSearch;

public interface TodoElasticsearchRepository extends ElasticsearchRepository<TodoElasticSearch, String> {
}
