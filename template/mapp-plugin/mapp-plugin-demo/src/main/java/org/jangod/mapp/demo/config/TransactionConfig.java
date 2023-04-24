package {{packageName}}.{{pluginName}}.config;

import org.jangod.iweb.mapp.config.MappTransactionConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description 开启事务
 * @Author demingchen
 * @Date 2022/4/14
 * @Version 1.0
 **/
@EnableTransactionManagement
@Configuration
public class TransactionConfig extends MappTransactionConfig {


}
