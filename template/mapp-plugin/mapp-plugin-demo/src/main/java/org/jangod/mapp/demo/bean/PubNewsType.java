package {{packageName}}.{{pluginName}}.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description pub_news_type表操作bean
 * @Author chendeming
 * @Date 2021/9/18
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PubNewsType对象", description="")
public class PubNewsType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "公司ID")
    private String companyId;

    @ApiModelProperty(value = "父级ID")
    private String parentId;

    @ApiModelProperty(value = "类型名称")
    private String name;

    @ApiModelProperty(value = "是否使用")
    private String status;


}
