package {{packageName}}.{{pluginName}}.config;

import com.gitee.starblues.annotation.ConfigDefinition;
import org.jangod.iweb.extension.feign.SpringBootFeignConfig;


@ConfigDefinition
public class FeignConfig implements SpringBootFeignConfig {

    @Override
    public String[] basePackages() {
        return new String[]{"org.jangod.csp.rc.feign","com.inspur.bsp.feign"};
    }
}
