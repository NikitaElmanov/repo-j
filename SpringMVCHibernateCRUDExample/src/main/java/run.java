import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.Flag;
import org.arpit.java2blog.service.CountryService;
import org.arpit.java2blog.service.FlagService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class run {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-servlet.xml");

        CountryService countryService = (CountryService) context.getBean("countryService");
        FlagService flagService = (FlagService) context.getBean("flagService");

        List<Flag> listOfFlags = flagService.getAllFlags();

        Country Russia = new Country("Russia", 150000);
        Country France = new Country("France", 70000);

        Russia.setFlag(listOfFlags.get(1));
        France.setFlag(listOfFlags.get(0));

        countryService.addCountry(Russia);
        countryService.addCountry(France);
    }

}
