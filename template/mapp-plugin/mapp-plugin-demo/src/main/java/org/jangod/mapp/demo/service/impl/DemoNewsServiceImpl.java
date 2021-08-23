package {{packageName}}.{{pluginName}}.service.impl;

import {{packageName}}.{{pluginName}}.bean.entity.DemoNews;
import {{packageName}}.{{pluginName}}.mapper.DemoNewsMapper;
import {{packageName}}.{{pluginName}}.service.IDemoNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mark
 * @since 2021-08-23
 */
@Service
public class DemoNewsServiceImpl extends ServiceImpl<DemoNewsMapper, DemoNews> implements IDemoNewsService {

}
