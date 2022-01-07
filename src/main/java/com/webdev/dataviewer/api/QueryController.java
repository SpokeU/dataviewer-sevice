package com.webdev.dataviewer.api;

import com.webdev.dataviewer.QueryResult;
import com.webdev.dataviewer.exception.ResourceNotFoundException;
import com.webdev.dataviewer.model.entity.QueryEntity;
import com.webdev.dataviewer.repository.QueryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("queries")
@RequiredArgsConstructor
public class QueryController implements CrudController<QueryEntity> {

    Logger logger = LoggerFactory.getLogger(QueryController.class);

    private final QueryRepository queryRepository;

    @PostMapping("run")
    public QueryResult<?> run(@RequestBody RunQueryRequest request) {
        logger.info(request.toString());
        return new QueryResult<>("asd");
    }

    @Override
    public QueryEntity create(QueryEntity query) {
        return queryRepository.save(query);
    }

    @Override
    public QueryEntity getById(Integer id) {
        return queryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("query", id));
    }

    @Override
    public List<QueryEntity> getAll() {
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

    private final record RunQueryRequest(Integer connectionId,
                                         Integer queryId,
                                         String queryString,
                                         Map<String, String> queryParams) {

    }

}
