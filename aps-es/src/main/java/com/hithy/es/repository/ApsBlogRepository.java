package com.hithy.es.repository;

import com.hithy.es.entity.ApsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ApsBlogRepository extends ElasticsearchRepository<ApsBlog, String> {
}
