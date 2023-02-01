package com.wuwq.yygh.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.common.util
 * @ClassName:OssUtils
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-18 14:35
 * @Version: 1.0
 */
public class OssUtils {


    private static final String ENDPOINT = "";
    private static final String ACCESSKEYID = "";
    private static final String ACCESSKEYSECRET = "";
    private static final String BUKETNAME = "";
    private static final String FOLDER = "";



    public static String uploadImage(MultipartFile file){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        String filename = file.getOriginalFilename();
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        filename = FOLDER + prefix + filename;
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpg");
        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(BUKETNAME, filename, inputStream, metadata);
        // 关闭OSSClient。
        ossClient.shutdown();
        String imgUrl = "https://" + BUKETNAME + "." + ENDPOINT + "/" + filename;
        return imgUrl;
    }

}
