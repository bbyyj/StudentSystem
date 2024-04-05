package com.example.backend.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Component
public class TencentCOSUtil {

    private static String secretId;
    private static String secretKey;
    private static String rootSrc;
    private static String bucketAddr;
    private static String bucketName;

    @Value("${tencent.secretId}")
    public void setSecretId(String secretId) {
        TencentCOSUtil.secretId = secretId;
    }

    @Value("${tencent.secretKey}")
    public void setSecretKey(String secretKey) {
        TencentCOSUtil.secretKey = secretKey;
    }

    @Value("${tencent.rootSrc}")
    public void setRootSrc(String rootSrc) {
        TencentCOSUtil.rootSrc = rootSrc;
    }

    @Value("${tencent.bucketAddr}")
    public void setBucketAddr(String bucketAddr) {
        TencentCOSUtil.bucketAddr = bucketAddr;
    }

    @Value("${tencent.bucketName}")
    public void setBucketName(String bucketName) {
        TencentCOSUtil.bucketName = bucketName;
    }

    private COSClient getCosClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(bucketAddr);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    public String upLoadFile(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();

            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + fileType;
            String key = "images/" + fileName;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setContentType(fileType);

            PutObjectResult putResult = getCosClient().putObject(bucketName, key, inputStream, objectMetadata);
            String url = rootSrc + key;
            getCosClient().shutdown();

            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
