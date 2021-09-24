package {{packageName}}.{{pluginName}}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import {{packageName}}.{{pluginName}}.bean.PubNews;
import {{packageName}}.{{pluginName}}.bean.PubNewsType;
import {{packageName}}.{{pluginName}}.bean.vo.PubNewsTypeVO;
import org.jangod.iweb.core.bean.IUser;
import org.jangod.iweb.core.bean.Result;

import java.util.List;

/**
 * <p>列举新闻类型</p>
 *
 * @author mark
 * @since 2021-08-24
 */
public interface IPubNewsTypeService {

    /**
     * <p>列举新闻类型</p>
     * @param companyId 公司id
     * */
    List<PubNewsTypeVO> listNewsType(String companyId);

    /**
     * <p>保存新闻资讯类型</p>
     * @param companyId 公司id
     * @param user 当前操作人信息
     * @param data 新闻资讯类型
     * */
    String saveNewsType(String companyId, IUser user, PubNewsType data);

    /**
     * <p>删除新闻资讯类型</p>
     * @param companyId 公司id
     * @param newsTypeId 新闻类型id
     * */
    Result removeNewsType(String companyId, String newsTypeId);
}
