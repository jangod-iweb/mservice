package {{packageName}}.{{pluginName}}.mapper;

import org.apache.ibatis.annotations.Mapper;
import {{packageName}}.{{pluginName}}.bean.entity.DemoNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mark
 * @since 2021-08-23
 */
@Mapper
public interface DemoNewsMapper extends BaseMapper<DemoNews> {

}
