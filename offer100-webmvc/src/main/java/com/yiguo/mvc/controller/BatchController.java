package com.yiguo.mvc.controller;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.yiguo.bean.Job;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by liyue on 2018/3/5.
 */
@Controller
@Api(value = "API - 图床", description = "图片上传详情")
@RequestMapping("/picture")
public class BatchController {

    @ApiOperation(value = "picture", notes = "图床")
    @ResponseBody
    @RequestMapping(value = "/{file}", method = {RequestMethod.GET})
    public String managePicture(@PathVariable MultipartFile file) {
        String key = LocalDateTime.now().getNano() + file.getOriginalFilename();
        String keys = "http://7xsn1m.com1.z0.glb.clouddn.com/";
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "k0OLNF5VWZyUkPZzyEliBCNm0EJ_BLFkU2a_OnHW";
        String secretKey = "udIpolzD6D2NpFEP7b-kdjEi5v-9EP0N1OXpc2zi";
        String bucket = "zlwwh";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(file.getBytes(), key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            keys = keys + key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  keys;
    }
}
