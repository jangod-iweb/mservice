package {{packageName}}.{{pluginName}}.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description pub_news表操作bean
 * @Author chendeming
 * @Date 2021/9/18
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PubNews对象", description="")
public class PubNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "公司ID")
    private String companyId;

    @ApiModelProperty(value = "新闻标题")
    private String title;

    @ApiModelProperty(value = "是否置顶")
    private String topFlag;

    @ApiModelProperty(value = "LOGO 网盘地址")
    private String logo;

    @ApiModelProperty(value = "信息来源")
    private String source;

    @ApiModelProperty(value = "新闻类型")
    private String type;

    @ApiModelProperty(value = "新闻内容")
    private String content;

    @ApiModelProperty(value = "状态(1:上线，0:下线)")
    private String status;

    @ApiModelProperty(value = "部门ID")
    private String deptId;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;

    @ApiModelProperty(value = "最后修改人")
    private String lastModifitor;

    @ApiModelProperty(value = "最后修改时间")
    private Date lastModifitionTime;


}
