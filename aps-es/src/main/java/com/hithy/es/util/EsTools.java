package com.hithy.es.util;

import com.hithy.es.entity.ApsProject;
import com.hithy.es.entity.EsEnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class EsTools {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void createNotExist(Class<? extends EsEnable> cls) {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(ApsProject.class);
        if (indexOperations.exists()) {
            log.info("{}------exist", cls);
            return;
        }
        indexOperations.create();
        Document mapping = indexOperations.createMapping();
        indexOperations.putMapping(mapping);
        log.info("{}------create", cls);
    }

}
