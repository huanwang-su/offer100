package com.yiguo.service;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yiguo.bean.Enterprise;
import com.yiguo.bean.Job;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by liyue on 2018/3/2.
 */
public class BatchServiceTest extends BaseServiceTest {

    @Test
    public void getJob() {
        MultipartFile file = null;
        String key="http://7xsn1m.com1.z0.glb.clouddn.com/";
        Configuration cfg = new Configuration(Zone.zone0());
        if (file!=null) {
            //...其他参数参考类注释
            String path=null;
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
                key=key+putRet.hash;
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
        }

    }
}