package {{packageName}}.{{pluginName}}.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import {{packageName}}.{{pluginName}}.bean.PubNews;
import {{packageName}}.{{pluginName}}.bean.vo.PubNewsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mark
 * @since 2021-08-24
 */
@Mapper
public interface PubNewsMapper extends BaseMapper<PubNews> {

    List<PubNewsVO> listNews(Map<String,Object> param);

    PubNewsVO getNewsInfo(@Param("id") String id);

}
