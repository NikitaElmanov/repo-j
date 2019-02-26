package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

/**
 * чёт не работает, go to {@link GreetingController}
 */

@Controller
public class AnotherController {

    public static final String template = "Hello %s !";
    public final AtomicLong counter = new AtomicLong();

    @RequestMapping("/gre")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World", required = false) String name){
        return new Greeting(
                counter.incrementAndGet(),
                String.format(template, name));
    }

}
