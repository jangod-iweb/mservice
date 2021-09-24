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
import org.jangod.iweb.core.annotation.AepSecurity;
import org.jangod.iweb.core.bean.IUser;
import org.jangod.iweb.core.bean.ResultObject;
import org.jangod.iweb.core.dao.BusiException;
import org.jangod.iweb.util.ResultUtil;
import org.jangod.iweb.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author : ww
 * @date : 2021/1/14 10:21
 */
@Api(value = "文件服务", tags = {"文件服务"})
@RequestMapping("/file")
@RestController
public class FileController extends AbstractController {

    @Autowired
    private RemoteDiskApiService remoteDiskApiService;

    @ApiOperation("附件上传")
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
     * <p>获取网盘文件</p>
     *
     * @param
     * @return
     */
    @GetMapping(value = "/getToken")
    @ApiOperation("获取文件下载token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docId", value = "网盘docId", dataType = "String", required = true)
    })
    public ResultObject<String> getToken(String docId, HttpServletResponse response) {
        try {
            IUser user = this.getCurrentUser();
            ResultObject token = remoteDiskApiService.getToken(user.getId(), user.getName(), docId);
            JSONObject row = (JSONObject) token.getRow();
            return ResultUtil.successToObject(row.getString("token"));
        }catch (Exception e){
            log.error(docId+"文件下载失败！");
            e.printStackTrace();
            return ResultUtil.errorToObject("文件下载失败");
        }
    }

    /**
     * <p>获取网盘文件</p>
     *
     * @param
     * @return
     */
    @GetMapping(value = "/download")
    @ApiOperation("附件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "文件下载token", dataType = "String", required = true)
    })
    @AepSecurity(enable = false)
    public void download(String token, HttpServletResponse response) {
        try {
            if(StringUtil.isEmpty(token)){
                return ;
            }
            Response download = remoteDiskApiService.download(token);
            Response.Body body = download.body();
            InputStream is = body.asInputStream();
            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            is.close();
        }catch (Exception e){
            log.error(token+"文件下载失败！");
            e.printStackTrace();
        }
    }



}
