package com.lhs.board_project.repository.elastic;

import com.lhs.board_project.document.BoardDoc;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardSearchRepository extends ElasticsearchRepository<BoardDoc, Long> {

//    @Query("{ \"query\" : { \"bool\" : { \"must\" : [ { \"query_string\" : { \"query\" : \"*:content*\", \"fields\" : [ \"content\" ] }, \"analyze_wildcard\": true } ] } }}")
    List<BoardDoc> findByContentContaining(String content);
}
