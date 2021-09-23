package {{packageName}}.{{pluginName}}.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import {{packageName}}.{{pluginName}}.bean.PubNews;

/**
 * @Description 新闻资讯
 * @Author chendeming
 * @Date 2021/9/18
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="新闻资讯", description="")
public class PubNewsVO extends PubNews {

    @ApiModelProperty(value = "部门名称 xx公司/xx部/xx")
    private String deptName;

    @ApiModelProperty(value = "新闻类型名称")
    private String typeName;

}
