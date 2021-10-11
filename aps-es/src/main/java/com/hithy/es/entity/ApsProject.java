package com.hithy.es.entity;


import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "aps_project", shards = 1, replicas = 2)
public class ApsProject implements EsEnable{
    /**
     * 项目id
     */
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    /**
     * 项目名
     */
    @Field(type = FieldType.Text)
    private String name;
    /**
     * 项目描述
     */
    @Field(type = FieldType.Text)
    private String description;
    /**
     * 用户id
     */
    @Field(type = FieldType.Keyword)
    private String user;
    /**
     * 停用
     */
    @Field(type = FieldType.Keyword)
    private boolean close;
}
