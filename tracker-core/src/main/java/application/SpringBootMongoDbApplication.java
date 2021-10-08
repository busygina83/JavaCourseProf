package application;

import my.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
//@SpringBootApplication(scanBasePackages={"application","controller","queue","repository","service"})
@ComponentScan({"application","queue"})
@EnableMongoRepositories
public class SpringBootMongoDbApplication {

//    @Autowired
//    PointRepository pointRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);
    }

}
