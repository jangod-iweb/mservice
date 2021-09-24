package {{packageName}}.{{pluginName}}.properties;

import com.gitee.starblues.annotation.ConfigDefinition;
import lombok.Data;
import org.jangod.iweb.mapp.config.MappMpAndResourceConfig;

@Data
@ConfigDefinition(fileName="{{name}}-plugin-{{pluginName}}.yml")
public class MappDemoProperties extends MappMpAndResourceConfig {
    private String dsId;
}
