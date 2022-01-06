package com.webdev.dataviewer.api;

import com.webdev.dataviewer.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("query")
public class QueryController {

    Logger logger = LoggerFactory.getLogger(QueryController.class);


    @PostMapping("run")
    public QueryResult<?> run(@RequestBody RunQueryRequest request) {

        logger.info(request.toString());
        return new QueryResult<>("asd");
    }

    private final record RunQueryRequest(Integer connectionId,
                                         Integer queryId,
                                         String queryString,
                                         Map<String, String> queryParams) {

    }

}
