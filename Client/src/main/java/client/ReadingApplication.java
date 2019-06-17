package client;

import com.netflix.hystrix.HystrixRequestLog;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCacheAspect;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
public class ReadingApplication {

    @Autowired
    BookService bookService;

    @RequestMapping("/to-read/{id}")
    public String toRead(@PathVariable("id") String id) throws Exception {
        HystrixRequestContext c = HystrixRequestContext.initializeContext();
        String s = bookService.readingList(id);
        HystrixRequestLog.getCurrentRequest();
        c.shutdown();
        return s;
    }

    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }

    @Bean
    public HystrixCacheAspect hystrixCacheAspect() {
        return new HystrixCacheAspect();
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadingApplication.class, args);
    }
}
