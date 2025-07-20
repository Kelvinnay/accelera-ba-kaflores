package com.enyoi.orders.config;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvsFacadeImpl implements EnvsFacade{

    @Value("${apps.envs.client.host}")
    private String host; //variable base de entorno //NO LO PUEDO MOCKEAR

    @Override
    public String getClientHostEnv(){
        return host;
    }

    @Override
    public String getClientPath() {
        return "api/v1/client/";
    }

}
