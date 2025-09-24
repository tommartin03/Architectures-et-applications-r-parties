package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import services.Facade;
import services.Service1;
import services.Service2;

@Configuration
public class ResourceAndBeanConfig {

    @Bean
    public Service1 service1() {
        return new Service1();
    }

    @Bean Service2 service2() {
        return new Service2();
    }

    @Bean
    public Facade facade(Service1 service1, Service2 service2) {
        return new Facade(service1, service2);
    }


}
