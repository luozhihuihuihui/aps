package com.hithy.es.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "aps_blog", shards = 1, replicas = 2)
public class ApsBlog implements EsEnable{
    /**
     * 问题id
     */
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    /**
     * 问题
     */
    @Field(type = FieldType.Text)
    private String title;
    /**
     * 回答内容,只保留文字,详情存mysql
     */
    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Date)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
