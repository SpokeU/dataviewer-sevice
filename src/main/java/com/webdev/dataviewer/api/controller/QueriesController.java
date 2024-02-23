package com.webdev.dataviewer.api.controller;

import com.webdev.dataviewer.QueryResult;
import com.webdev.dataviewer.exception.ResourceNotFoundException;
import com.webdev.dataviewer.api.model.request.RunQueryRequest;
import com.webdev.dataviewer.entity.QueryEntity;
import com.webdev.dataviewer.repository.QueryRepository;
import com.webdev.dataviewer.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("queries")
@RequiredArgsConstructor
public class QueriesController implements CrudController<QueryEntity> {

    Logger logger = LoggerFactory.getLogger(QueriesController.class);

    private final QueryRepository queryRepository;

    private final QueryService queryService;

    @Override
    public QueryEntity create(QueryEntity query) {
        return queryRepository.save(query);
    }

    @Override
    public QueryEntity findById(Integer id) {
        return queryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("query", id));
    }

    @Override
    public List<QueryEntity> findAll() {
        return queryRepository.findAll();
    }

    @Override
    public QueryEntity update(Integer id, QueryEntity query) {
        return queryRepository.findById(id).map(q -> {
            query.setId(id);
            return queryRepository.save(query);
        }).orElseThrow(() -> {
            throw new ResourceNotFoundException("query", id);
        });
    }

    @Override
    public void delete(Integer id) {
        queryRepository.deleteById(id);
    }

    @PostMapping("run")
    public QueryResult<?> run(@RequestBody RunQueryRequest request) {
        logger.info(request.toString());
        return queryService.runQuery(request.connectionId(), request.queryString(), request.queryParams());
    }

}
