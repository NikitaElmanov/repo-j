package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    public static final String template = "Hello %s !";
    public final AtomicLong counter = new AtomicLong();

    @RequestMapping("/g")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World", required = false) String name){
        return new Greeting(
                counter.incrementAndGet(),
                String.format(template, name));
    }

}
