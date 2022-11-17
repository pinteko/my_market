package com.korsuk.my_market.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:secret.properties")
public class PropertiesWithConfig {

}
