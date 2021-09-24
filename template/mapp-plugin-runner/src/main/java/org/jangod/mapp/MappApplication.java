package {{packageName}};

import lombok.extern.slf4j.Slf4j;
import org.jangod.iweb.AbstractApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class {{#firstToUpper name}}{{/firstToUpper}}RunnerApplication extends AbstractApplication {

    public static void main(String[] args) {
        SpringApplication.run({{#firstToUpper name}}{{/firstToUpper}}RunnerApplication.class, args);
    }

}
