package {{packageName}}.{{pluginName}}.manager;

import com.gitee.starblues.extension.mybatis.mybatisplus.ServiceImplWrapper;
import {{packageName}}.{{pluginName}}.bean.PubNews;
import {{packageName}}.{{pluginName}}.mapper.PubNewsMapper;
import org.springframework.stereotype.Component;

/**
 * @Description pub_news表操作能力封装（避免service注入service，容易造成循环依赖，以能力层下沉公用部分）
 * @Author chendeming
 * @Date 2021/9/24
 * @Version 1.0
 **/
@Component
public class PubNewsManager extends ServiceImplWrapper<PubNewsMapper, PubNews> {

    public PubNewsManager(PubNewsMapper baseMapper) {
        super(baseMapper);
    }


}
