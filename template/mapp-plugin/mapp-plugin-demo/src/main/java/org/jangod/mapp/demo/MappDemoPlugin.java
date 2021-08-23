package {{packageName}}.{{pluginName}};

import org.jangod.iweb.mapp.MappPlugin;
import org.pf4j.PluginWrapper;

public class {{#firstToUpper name}}{{/firstToUpper}}{{#firstToUpper pluginName}}{{/firstToUpper}}Plugin extends MappPlugin {
    public {{#firstToUpper name}}{{/firstToUpper}}{{#firstToUpper pluginName}}{{/firstToUpper}}Plugin(PluginWrapper wrapper) {
        super(wrapper);
    }

}
