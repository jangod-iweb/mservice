package {{packageName}}.{{pluginName}}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gitee.starblues.extension.mybatis.mybatisplus.ServiceImplWrapper;
import org.apache.commons.lang.StringUtils;
import {{packageName}}.{{pluginName}}.bean.PubNews;
import {{packageName}}.{{pluginName}}.bean.PubNewsType;
import {{packageName}}.{{pluginName}}.bean.vo.PubNewsTypeVO;
import {{packageName}}.{{pluginName}}.mapper.PubNewsTypeMapper;
import {{packageName}}.{{pluginName}}.service.IPubNewsTypeService;
import org.jangod.iweb.core.bean.IUser;
import org.jangod.iweb.core.bean.Result;
import org.jangod.iweb.db.util.BeanUtils;
import org.jangod.iweb.util.ResultUtil;
import org.jangod.iweb.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>新闻类型服务</p>
 *
 * @author mark
 * @since 2021-08-24
 */
@Transactional
@Service
public class PubNewsTypeServiceImpl extends ServiceImplWrapper<PubNewsTypeMapper,PubNewsType> implements IPubNewsTypeService {

    public PubNewsTypeServiceImpl(PubNewsTypeMapper baseMapper) {
        super(baseMapper);
    }

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<PubNewsTypeVO> listNewsType(String companyId) {
        /**
         * 查询当前公司的资讯类型列表
         */
        LambdaQueryWrapper<PubNewsType> wrapper = new QueryWrapper<PubNewsType>().lambda();
        wrapper.eq(PubNewsType::getCompanyId,companyId);
        wrapper.eq(PubNewsType::getStatus,"1");
        List<PubNewsType> list = this.list(wrapper);

        List<PubNewsTypeVO> childlist = new ArrayList<>();
        List<PubNewsTypeVO> parentlist = new ArrayList<>();
        for (PubNewsType pubNewsType : list){
            PubNewsTypeVO pubNewsTypeVO = BeanUtils.convert(pubNewsType, PubNewsTypeVO.class);
            if(StringUtils.equals("#",pubNewsType.getParentId())){
                //获取父级ID为#的根节点
                parentlist.add(pubNewsTypeVO);
            }else {
                childlist.add(pubNewsTypeVO);
            }
        }
        for (PubNewsTypeVO pubNewsTypeVO : parentlist){
            //递归遍历组装树
            List<PubNewsTypeVO> pubNewsTypes = this.getChildList(childlist,pubNewsTypeVO.getId());
            pubNewsTypeVO.setPubNewsTypes(pubNewsTypes);
        }
        return parentlist;
    }


    @Override
    public String saveNewsType(String companyId, IUser user, PubNewsType data) {
        String id = data.getId();
        if(StringUtils.isNotEmpty(id)){
            this.updateById(data);
        }else {
            id = Tools.genId()+"";
            data.setId(id);
            data.setCompanyId(companyId);
            this.save(data);
        }
        return id;
    }

    @Override
    public Result removeNewsType(String companyId, String id) {
        /**
         * 查询是否存在子项 有则不可删除
         */
        LambdaQueryWrapper<PubNewsType> queryWrapper = new QueryWrapper<PubNewsType>().lambda();
        queryWrapper.eq(PubNewsType::getParentId,id);
        List<PubNewsType> list = this.list(queryWrapper);
        if( list != null && list.size() > 0 ){
            return ResultUtil.error("当前节点存在子节点,不可删除");
        }
        // 查询节点是否已使用 有则不可删除
        LambdaQueryWrapper<PubNews> queryWrapper1 = new QueryWrapper<PubNews>().lambda();
        queryWrapper1.eq(PubNews::getType,id);
        // 软删除
        PubNewsType update = new PubNewsType();
        update.setStatus("0");
        update.setCompanyId(companyId);
        update.setId(id);
        baseMapper.updateStatus(update);
        return  ResultUtil.success("节点删除成功");
    }

    /**
     * <p>组装资讯类型树</p>
     * @param list 新闻资讯类型列表
     * @param id 新闻资讯id
     * @return
     */
    private List<PubNewsTypeVO> getChildList(List<PubNewsTypeVO> list, String id){
        List<PubNewsTypeVO> childList = new ArrayList<>();
        for (PubNewsTypeVO newsTypeVO : list){
            if(StringUtils.equals(newsTypeVO.getParentId(),id)){
                childList.add(newsTypeVO);
            }
        }
        if(childList == null || childList.size() == 0){
            return new ArrayList<PubNewsTypeVO>();
        }
        for (PubNewsTypeVO newsTypeVO:childList){
            newsTypeVO.setPubNewsTypes(getChildList(list,newsTypeVO.getId()));
        }
        return childList;
    }
}
