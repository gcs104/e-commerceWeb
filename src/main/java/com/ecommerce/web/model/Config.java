package com.ecommerce.web.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类，用来存放配置
 */
@Component
public class Config {
    @Value("${config.userIdAdd}")
    private int userIdAdd ;

    public int getUserIdAdd() {
        return userIdAdd;
    }
}
