package queues;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QueueMain {
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(QueueContext.class);
    }
}
