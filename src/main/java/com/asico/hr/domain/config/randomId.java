package com.asico.hr.domain.config;


import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @author m.tavana
 * @version 1.0
 * @since 2024
 */
@Configuration
public class randomId {

    public String generateNodeId() {

        Random random = new Random();
        int randomNumber = random.nextInt(1001);

        String nodeId = "NODE/" + randomNumber;

        return nodeId;

    }

    public String generateRouteID() {

        Random random = new Random();
        int randomNumber = random.nextInt(1001);
        String routeId = "ROUTE/"+ randomNumber;

        return routeId;

    }
}
