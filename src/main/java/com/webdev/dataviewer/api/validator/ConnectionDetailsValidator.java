package com.webdev.dataviewer.api.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConnectionDetailsValidator {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*private ObjectMapper mapper = new ObjectMapper();

    private Map<String, Class<? extends ConnectionDetails>> connectionDetailsClassMap;*/


    public Integer validateId(Integer id) {
        logger.info("Validation");
        return id;
    }

/*    private ConnectionDetails toConnectionDetails(String connectionType, Map<String, ? extends Object> connectionDetailsMap) {
        Class<? extends ConnectionDetails> connectionDetailsClass = connectionDetailsClassMap.get(connectionType);
        ConnectionDetails connectionDetails = mapper.convertValue(connectionDetailsMap, connectionDetailsClass);
        return connectionDetails;
    }*/

}
