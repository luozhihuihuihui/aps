package com.hithy.es.repository;

import com.hithy.es.entity.ApsProject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApsProjectRepository extends ElasticsearchRepository<ApsProject, String> {

}
