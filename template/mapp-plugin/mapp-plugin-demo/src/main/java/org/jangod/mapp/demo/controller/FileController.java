package {{packageName}}.{{pluginName}}.controller;


import com.alibaba.fastjson.JSONObject;
import com.inspur.bsp.bean.vo.file.FileVo;
import feign.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.jangod.csp.rc.feign.RemoteDiskApiService;
import org.jangod.iweb.core.action.AbstractController;
import org.jangod.iweb.core.bean.IUser;
import org.jangod.iweb.core.bean.ResultObject;
import org.jangod.iweb.core.dao.BusiException;
import org.jangod.iweb.util.ResultUtil;
import org.jangod.iweb.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : ww
 * @date : 2021/1/14 10:21
 */
@Api(value = "文件上传", tags = {"文件上传"})
@RequestMapping("/file")
@RestController
public class FileController extends AbstractController {

    @Autowired
    private RemoteDiskApiService remoteDiskApiService;

    @ApiOperation("图片上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "maxSize", value = "附件最大上传大小", dataType = "string", required = false)
    })
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public ResultObject<FileVo> upload(@RequestParam("file") MultipartFile file, @RequestParam(value = "maxSize", required = false) String maxSize) {
        IUser user = this.getCurrentUser();
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (StringUtil.isNotBlank(maxSize)) {
            if ((file.getSize() / 1024) > Long.parseLong(maxSize)) {
                return ResultUtil.errorToObject("文件大小超过限制");
            }
        }
        if (file.isEmpty() || file.getSize() == 0) {
            throw new BusiException("上传失败,不能为空");
        }
        return remoteDiskApiService.upload(file, user.getId(),  fileType,"/news");
    }



    /**
     * 获取网盘文件
     *
     * @param
     * @return
     */
    @PostMapping(value = "/download")
    @ApiOperation("附件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docId", value = "网盘docId", dataType = "String", required = true)
    })
    public ResultObject getPic(String docId) {
        IUser user = this.getCurrentUser();
        ResultObject token = remoteDiskApiService.getToken(user.getId(), user.getName(), docId);
        JSONObject row = (JSONObject)token.getRow();
        Response download = remoteDiskApiService.download(row.getString("token"));
        return ResultUtil.successToObject(download);
    }

}
