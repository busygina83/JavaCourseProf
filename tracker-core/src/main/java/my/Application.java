package my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
@ComponentScan({"application", "service", "queue","controller"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
