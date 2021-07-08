package ru.netology.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("ru.netology.conditional")

public class JavaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile",name = "dev",havingValue = "true")
    public SystemProfile devProfile(){
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile",name = "dev",havingValue = "false")
    public SystemProfile prodProfile(){
        return new ProductionProfile();
    }

}
