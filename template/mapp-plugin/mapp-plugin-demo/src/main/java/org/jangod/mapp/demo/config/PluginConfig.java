package {{packageName}}.{{pluginName}}.config;

import com.gitee.starblues.annotation.ConfigDefinition;
import lombok.Data;
import org.jangod.iweb.mapp.config.MappMpAndResourceConfig;

@Data
@ConfigDefinition(fileName="{{name}}-plugin-{{pluginName}}.yml")
public class PluginConfig extends MappMpAndResourceConfig {
    private String dsId;
}
