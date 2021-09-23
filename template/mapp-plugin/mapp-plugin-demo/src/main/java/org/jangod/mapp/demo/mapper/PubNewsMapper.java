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

    @Select("SELECT n.ID,n.COMPANY_ID,n.TITLE,n.TOP_FLAG,LOGO,n.SOURCE,TYPE,n.STATUS,n.DEPT_ID,n.CREATION_TIME,n.LAST_MODIFITION_TIME,n.CONTENT,t.NAME TYPE_NAME " +
            " FROM PUB_NEWS n LEFT JOIN PUB_NEWS_TYPE t ON n.type=t.ID where n.ID='${id}'")
    PubNewsVO getNewsInfo(@Param("id") String id);
}
