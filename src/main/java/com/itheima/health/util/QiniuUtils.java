package com.itheima.health.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @author zhangmeng
 * @description 七牛云工具类
 * @date 2019/9/26
 **/
@Slf4j
@Setter
@Getter
public class QiniuUtils {
    private String accessKey;
    private String secretKey;
    private String urlPrefix;
    private String bucket;

    /**
     * 上传到七牛云
     *
     * @param is             上传内容的输入流
     * @param uploadFileName
     */
    public void upload2Qiniu(InputStream is, String uploadFileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(is, uploadFileName, upToken, null, null);
            //解析上传成功的结果
            log.info(response.bodyString());
            // 访问路径
            log.info("{}/{}", urlPrefix, uploadFileName);
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.error(r.toString());
            try {
                log.error(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
                log.error("", ex2);
            }
            throw new RuntimeException(ex);
        }
    }

    /**
     * 文件不存在，则不作任何操作
     * @param fileName
     */
    public void deleteFileFromQiniu(String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            if(612 == ex.code()){
                // 文件不存在，则无需任何操作，直接返回
                log.info("[七牛云工具类-删除]重复删除，跳过:{}",fileName );
            }else {
                //如果遇到异常，说明删除失败
                log.error("code:{}", ex.code());
                log.error(ex.response.toString());
                throw new RuntimeException(ex);
            }
        }
    }
}
