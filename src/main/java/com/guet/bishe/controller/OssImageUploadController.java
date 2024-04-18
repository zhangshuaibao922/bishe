package com.guet.bishe.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;


@Api(tags = "上传功能接口")
@RestController
@RequestMapping("/aliyun")
public class OssImageUploadController {


    private static final String endpoint="https://oss-cn-hongkong.aliyuncs.com";


    private static final String accessKeyId="LTAI5tNmSxaPFBVcCbVi5VSV";


    private static final String accessKeySecret="y0d3yr6Q0889ZjCvCascrMzYG6UMOO";

    private static final String bucketName="bishe-zhangshuaibao922";


    @PostMapping("/upload/{examId}")
    public String uploadImages(@PathVariable String examId,@RequestParam("images") MultipartFile[] images) {
        // 创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        System.out.println("开始上传");
        try {
            for (MultipartFile image : images) {
                // 获取原始文件名
                String originalFilename = image.getOriginalFilename();
                String[] split = originalFilename.split("/");
                String objectName=examId+"/resource/"+split[1];
                System.out.println(split[1]);
                // 上传文件流
                InputStream inputStream = image.getInputStream();

                // 设置文件元信息
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(inputStream.available());

                // 上传文件
                PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);
                System.out.println("成功上传文件：" + objectName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        } finally {
            // 关闭 OSS 客户端
            ossClient.shutdown();
        }

        return "上传成功";
    }
}
