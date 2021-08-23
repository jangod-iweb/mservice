package {{packageName}}.{{pluginName}}.controller;


import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jangod.iweb.core.bean.ResultList;
import org.jangod.iweb.util.ResultUtil;
import {{packageName}}.{{pluginName}}.bean.entity.DemoNews;
import {{packageName}}.{{pluginName}}.service.IDemoNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.jangod.iweb.core.action.AbstractController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mark
 * @since 2021-08-23
 */
@RestController
@RequestMapping("/{{pluginName}}/demoNews")
@Api(value = "新闻咨询",tags = "新闻咨询")
public class DemoNewsController extends AbstractController {
    @Autowired
    private IDemoNewsService iDemoNewsService;

    @ApiOperation("查询新闻")
    @GetMapping("/query")
    public ResultList<DemoNews> query(@ApiParam("分页") @RequestParam int page, @ApiParam("每页数量")  @RequestParam int rows){
        PageHelper.startPage(page,rows);
        List<DemoNews> list = iDemoNewsService.list();
        return ResultUtil.successToList(list);
    }
}

