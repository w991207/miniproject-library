package com.board.controller;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

    @Bean
    public WebDriverManager setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
        return WebDriverManager.getInstance(WebDriverManager.class);
    }
}

