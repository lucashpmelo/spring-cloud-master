package client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @HystrixCommand(commandKey = "AA", threadPoolKey = "BOOK", fallbackMethod = "reliable",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "111111")
            })
    @CacheResult()
    public String readingList(@CacheKey  String test) throws Exception {
        if ("test".equals(test)) {
            throw new Exception();
        }
        return test;
    }

    public String getCacheKey(String key) {
        return key + "1";
    }

    public String reliable() {
        return "Cloud Native Java (O'Reilly)";
    }

    public String reliable(String s) {
        return "Cloud Native Java (O'Reilly)";
    }

}
