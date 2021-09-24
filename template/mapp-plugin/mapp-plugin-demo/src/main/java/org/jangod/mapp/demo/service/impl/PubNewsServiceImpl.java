package {{packageName}}.{{pluginName}}.service.impl;

import com.gitee.starblues.extension.mybatis.mybatisplus.ServiceImplWrapper;
import com.github.pagehelper.PageHelper;
import com.inspur.bsp.bean.dto.bsp.dept.SaasSearchDeptDto;
import com.inspur.bsp.bean.vo.bsp.dept.SaasDeptVo;
import com.inspur.bsp.feign.RemoteBspService;
import org.apache.commons.lang.StringUtils;
import {{packageName}}.{{pluginName}}.bean.PubNews;
import {{packageName}}.{{pluginName}}.bean.vo.PubNewsVO;
import {{packageName}}.{{pluginName}}.mapper.PubNewsMapper;
import {{packageName}}.{{pluginName}}.service.IPubNewsService;
import org.jangod.iweb.core.bean.IUser;
import org.jangod.iweb.core.bean.ResultList;
import org.jangod.iweb.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>新闻资讯服务</p>
 *
 * @author chendeming
 * @since 2021-09-22
 */
@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
@Service
public class PubNewsServiceImpl extends ServiceImplWrapper<PubNewsMapper, PubNews> implements IPubNewsService {

    @Autowired
    private RemoteBspService remoteBspService;

    public PubNewsServiceImpl(PubNewsMapper baseMapper) {
        super(baseMapper);
    }

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<PubNewsVO> paginateNews(String companyId, Map<String,Object> params, int page, int rows) {
        try{
            params.put("companyId",companyId);
            // 尽量在service层解决转换问题，sql转换往往存在数据库兼容性问题以及性能问题
            if(params.containsKey("title") && StringUtils.isNotBlank((String)params.get("title"))){
                params.put("title","%"+params.get("title")+"%");
            }
            // 分页查询
            PageHelper.startPage(page,rows);
            List<PubNewsVO> list = baseMapper.listNews(params);
            // 结果格式化
            if(list != null && list.size() > 0){
                /**
                 * 根据公司id获取当前公司部门列表
                 * 获取部门名称
                 */
                SaasSearchDeptDto deptQueryDTO = new SaasSearchDeptDto();
                deptQueryDTO.setCompanyId(companyId);
                ResultList<SaasDeptVo> depts = remoteBspService.deptAll(deptQueryDTO);
                for (PubNewsVO pubNewsVO : list){
                    String deptName = this.getFullDeptName(depts.getRows(),pubNewsVO.getDeptId(),"");
                    pubNewsVO.setDeptName(deptName);
                }
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    @Override
    public PubNewsVO getNewsInfo(String companyId, String newsId) {
        try{
            PubNewsVO pubNewsVO = baseMapper.getNewsInfo(newsId);
            if(pubNewsVO == null){
                throw new RuntimeException("当前ID为查到对应数据");
            }
            SaasSearchDeptDto deptQueryDTO = new SaasSearchDeptDto();
            deptQueryDTO.setCompanyId(companyId);
            ResultList<SaasDeptVo> depts = remoteBspService.deptAll(deptQueryDTO);
            String deptName = this.getFullDeptName(depts.getRows(),pubNewsVO.getDeptId(),"");
            pubNewsVO.setDeptName(deptName);
            return pubNewsVO;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void saveNews(String companyId, IUser user, PubNews data) {
        if(StringUtils.isNotEmpty(data.getId())){
            data.setLastModifitionTime(new Date());
            data.setLastModifitor(user.getId());
            updateById(data);
        }else {
            data.setId(Tools.genId()+"");
            data.setCompanyId(companyId);
            data.setCreationTime(new Date());
            data.setCreator(user.getId());
            data.setLastModifitionTime(new Date());
            data.setLastModifitor(user.getId());
            save(data);
        }
    }

    /**
     * 组装部门名称  格式为  xx公司/xx部/xx
     * @param depts 部门列表
     * @param deptId 部门id
     * @param deptName 部门名称
     * @return
     */
    private String getFullDeptName(List<SaasDeptVo> depts, String deptId, String deptName){
        if(depts == null || depts.size() < 1){
            return "";
        }
        if(!StringUtils.equals(deptId,"#")){ //判断父级是否为根节点#  如果为# 表示当前部门路线结束 跳出
            for(SaasDeptVo dept : depts){
                if( StringUtils.equals(deptId,dept.getId()) ){
                    if(StringUtils.isNotEmpty(deptName)){
                        deptName = dept.getName()+"/"+deptName;
                    }else {
                        deptName = dept.getName();
                    }
                    return this.getFullDeptName(depts,dept.getParentId(),deptName);
                }
            }
        }
        return deptName;
    }
}
