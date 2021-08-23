package {{packageName}}.{{pluginName}}.service.impl;

import {{packageName}}.{{pluginName}}.bean.entity.DemoNewsType;
import {{packageName}}.{{pluginName}}.mapper.DemoNewsTypeMapper;
import {{packageName}}.{{pluginName}}.service.IDemoNewsTypeService;
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
public class DemoNewsTypeServiceImpl extends ServiceImpl<DemoNewsTypeMapper, DemoNewsType> implements IDemoNewsTypeService {

}
