package {{packageName}}.{{pluginName}}.properties;

import com.gitee.starblues.annotation.ConfigDefinition;
import lombok.Data;
import org.jangod.iweb.mapp.config.MappMpAndResourceConfig;

@Data
@ConfigDefinition(fileName="chendeming-plugin-news.yml")
public class MappDemoProperties extends MappMpAndResourceConfig {
    private String dsId;
}
