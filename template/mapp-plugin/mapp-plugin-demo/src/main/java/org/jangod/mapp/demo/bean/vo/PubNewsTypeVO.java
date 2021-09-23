package {{packageName}}.{{pluginName}}.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import {{packageName}}.{{pluginName}}.bean.PubNewsType;

import java.util.List;

/**
 * @Description 新闻分类
 * @Author chendeming
 * @Date 2021/9/18
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PubNewsType对象", description="")
public class PubNewsTypeVO extends PubNewsType {

    @ApiModelProperty(value = "子集")
    private List<PubNewsTypeVO> pubNewsTypes;

}
