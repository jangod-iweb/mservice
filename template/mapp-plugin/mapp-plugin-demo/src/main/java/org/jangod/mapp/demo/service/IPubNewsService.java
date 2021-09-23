package {{packageName}}.{{pluginName}}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import {{packageName}}.{{pluginName}}.bean.PubNews;
import {{packageName}}.{{pluginName}}.bean.vo.PubNewsVO;
import {{packageName}}.{{pluginName}}.mapper.PubNewsMapper;
import org.jangod.iweb.core.bean.IUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mark
 * @since 2021-08-24
 */
public interface IPubNewsService extends IService<PubNews> {
    /**
     * <p>分页查询新闻资讯列表</p>
     * @param companyId 公司id
     * @param params 筛选条件
     * @param page 页数
     * @param rows 页大小
     * */
    List<PubNewsVO> paginateNews(String companyId, Map<String,Object> params, int page, int rows);

    /**
     * <p>获取新闻资讯</p>
     * @param companyId 公司id
     * @param newsId 新闻资讯id
     * */
    PubNewsVO getNewsInfo(String companyId, String newsId);

    /**
     * <p>保存新闻资讯</p>
     * @param companyId 公司id
     * @param user 当前操作人信息
     * @param data 新闻资讯
     * */
    void saveNews(String companyId, IUser user, PubNews data);

}
