package {{packageName}};
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
@Slf4j
public class {{#firstToUpper name}}{{/firstToUpper}}Application implements ApplicationListener<WebServerInitializedEvent> {

    public static void main(String[] args) {
        SpringApplication.run({{#firstToUpper name}}{{/firstToUpper}}Application.class, args);
    }
    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        log.info("启动成功，监听在端口"+webServerInitializedEvent.getWebServer().getPort());
    }
}
