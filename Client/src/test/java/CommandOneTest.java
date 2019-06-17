import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import hystrix.CommandOne;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandOneTest {



    @Test
    void test() {
        HystrixRequestContext.initializeContext();
        CommandOne commandOne = null;
        for (int i = 0; i < 3; i++) {
            commandOne = new CommandOne("t");
            commandOne.execute();
            System.out.println(commandOne.isResponseFromCache());
        }
//        Assertions.assertEquals(10, CommandOne.runCount);
    }
}
