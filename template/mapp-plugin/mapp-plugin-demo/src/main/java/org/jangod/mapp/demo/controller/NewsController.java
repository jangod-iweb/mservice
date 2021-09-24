package {{packageName}}.{{pluginName}}.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import {{packageName}}.{{pluginName}}.bean.PubNews;
import {{packageName}}.{{pluginName}}.bean.PubNewsType;
import {{packageName}}.{{pluginName}}.bean.vo.PubNewsTypeVO;
import {{packageName}}.{{pluginName}}.bean.vo.PubNewsVO;
import {{packageName}}.{{pluginName}}.service.IPubNewsService;
import {{packageName}}.{{pluginName}}.service.IPubNewsTypeService;
import org.jangod.iweb.core.action.AbstractController;
import org.jangod.iweb.core.bean.IUser;
import org.jangod.iweb.core.bean.Result;
import org.jangod.iweb.core.bean.ResultList;
import org.jangod.iweb.core.bean.ResultObject;
import org.jangod.iweb.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mark
 * @since 2021-08-24
 */
@RestController
@Api(tags = {"新闻资讯"})
@RequestMapping("/news")
public class NewsController extends AbstractController {

    @Autowired
    private IPubNewsService pubNewsService;
    @Autowired
    private IPubNewsTypeService pubNewsTypeService;

    /**
     * 查询新闻资讯列表
     * @param page
     * @param rows
     * @return
     */
    @ApiOperation("查询新闻资讯列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "deptId", value = "部门ID", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态(1上线 0下线)", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "类型ID", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true, paramType = "query", dataType = "int", example = "1"),
            @ApiImplicitParam(name = "rows", value = "页大小", required = true, paramType = "query", dataType = "int", example = "10")
    })
    @GetMapping("/paginateNews")
    public ResultList<PubNewsVO> paginateNews(@RequestParam int page,@RequestParam int rows){
        try{
            // 做插件要有租户的概念，不同的公司应该看到不一样的东西
            String companyId = getCompanyId();
            // 获取查询参数
            Map<String,Object> query = this.getRequestParams();
            List<PubNewsVO> list = pubNewsService.paginateNews(companyId,query,page,rows);
            return ResultUtil.successToList(list);
        }catch (Exception e){
            return ResultUtil.errorToList("查询失败："+e.getMessage());
        }
    }

    /**
     * 查询新闻资讯详情
     * @return
     */
    @ApiOperation("查询新闻资讯详情")
    @GetMapping("/getNewsInfo")
    public ResultObject getNewsInfo(@ApiParam("新闻资讯ID") @RequestParam(value = "id") String newsId){
        try{
            // 做插件要有租户的概念，不同的公司应该看到不一样的东西
            String companyId = getCompanyId();
            if(StringUtils.isEmpty(companyId)){
                return ResultUtil.errorToObject("companyId为空,用户未登录");
            }
            PubNewsVO pubNewsVo = pubNewsService.getNewsInfo(companyId,newsId);
            return ResultUtil.successToObject(pubNewsVo);
        }catch (Exception e){
            return ResultUtil.errorToObject("查询失败："+e.getMessage());
        }
    }

    /**
     * 保存新闻资讯详情
     * @return
     */
    @ApiOperation("保存新闻资讯")
    @PostMapping("/saveNews")
    public Result saveNews(@RequestBody PubNews data){
        try{
            // 做插件要有租户的概念，不同的公司应该看到不一样的东西
            IUser user = this.getCurrentUser();
            String companyId = getCompanyId();
            if(StringUtils.isEmpty(companyId)){
                return ResultUtil.errorToObject("companyId为空,用户未登录");
            }
            pubNewsService.saveNews(companyId,user,data);
            return ResultUtil.success("保存成功");
        }catch (Exception e){
            return ResultUtil.error("保存失败："+e.getMessage());
        }
    }

    /**
     * 删除新闻资讯
     * @return
     */
    @ApiOperation("删除新闻资讯")
    @PostMapping("/delNews")
    public Result delNews(@ApiParam("新闻资讯ID") @RequestParam(value = "id") String id){
        try{
            if(StringUtils.isEmpty(id)){
                return ResultUtil.error("删除失败，id参数未定义");
            }
            String companyId = getCompanyId();
            if(StringUtils.isEmpty(companyId)){
                return ResultUtil.error("companyId为空,用户未登录");
            }
            // 尽量用lambda，属性修改的时候好处理
            LambdaQueryWrapper<PubNews> wrapper = new QueryWrapper<PubNews>().lambda();
            wrapper.eq(PubNews::getId,id);
            wrapper.eq(PubNews::getCompanyId,companyId);
            pubNewsService.remove(wrapper);
            return ResultUtil.success("新闻资讯删除成功！");
        }catch (Exception e){
            return ResultUtil.error("新闻资讯删除失败："+e.getMessage());
        }
    }


    /**
     * 查询新闻类型树
     */
    @ApiOperation("查询新闻资讯类型列表")
    @GetMapping("/listNewsType")
    public ResultList<PubNewsTypeVO> listNewsType(){
        try{
            String companyId = getCompanyId();
            if(StringUtils.isEmpty(companyId)){
                return ResultUtil.errorToList("companyId为空,用户未登录");
            }
            List<PubNewsTypeVO> list = pubNewsTypeService.listNewsType(companyId);
            return ResultUtil.successToList(list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.errorToList("查询失败："+e.getMessage());
        }
    }
    /**
     * 保存新闻类型
     */
    @ApiOperation("保存新闻资讯类型")
    @PostMapping("/saveNewsType")
    public ResultObject<String> saveNewsType(@RequestBody PubNewsType data){
        try{
            String companyId = getCompanyId();
            if(StringUtils.isEmpty(companyId)){
                return ResultUtil.errorToObject("companyId为空,用户未登录");
            }
            String id = pubNewsTypeService.saveNewsType(companyId,this.getCurrentUser(),data);
            return ResultUtil.successToObject(id);
        }catch (Exception e){
            return ResultUtil.errorToObject("保存失败："+e.getMessage());
        }
    }

    /**
     * 删除新闻类型
     */
    @ApiOperation("删除新闻资讯类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "新闻类型ID",required = true, paramType = "query", dataType = "string")
    })
    @PostMapping("/delNewsType")
    public Result delNewsType(@RequestParam String id){
        try{
            if(StringUtils.isEmpty(id)){
                return ResultUtil.errorToList("删除失败，id参数未定义");
            }
            String companyId = getCompanyId();
            if(StringUtils.isEmpty(companyId)){
                return ResultUtil.errorToList("companyId为空,用户未登录");
            }
            pubNewsTypeService.removeNewsType(companyId,id);
            return ResultUtil.success("删除成功");
        }catch (Exception e){
            log.error("新闻资讯类型删除失败！");
            e.printStackTrace();
            return ResultUtil.error("删除失败："+e.getMessage());
        }
    }

    /**
     * <p>获取用户公司id</p>
     * */
    private String getCompanyId(){
        IUser user = this.getCurrentUser();
        if(user == null){
            return null;
        }
        String companyId = user.getAttrMap().get("companyId");
        return companyId;
    }

}

