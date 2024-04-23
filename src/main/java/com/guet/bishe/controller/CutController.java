package com.guet.bishe.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guet.bishe.entity.Answer;
import com.guet.bishe.entity.Paper;
import com.guet.bishe.entity.Response;
import com.guet.bishe.mapper.AnswerMapper;
import com.guet.bishe.mapper.PaperMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/4/20 15:01
 */
@Api(tags = "图片切割功能接口")
@RestController
@RequestMapping("/cut")
public class CutController {


    private static final String endpoint = "https://oss-cn-hongkong.aliyuncs.com";
    private static final String accessKeyId = "LTAI5tNmSxaPFBVcCbVi5VSV";
    private static final String accessKeySecret = "y0d3yr6Q0889ZjCvCascrMzYG6UMOO";
    private static final String bucketName = "bishe-zhangshuaibao922";
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private AnswerMapper ansMapper;

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("/{paperClassId}/{examId}")
    public Response<Boolean> cutting(@PathVariable String paperClassId, @PathVariable String examId) {
        // 创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        System.out.println("开始上传");
        Response<Boolean> objectResponse = new Response<>();
        try {
            List<Paper> papers = paperMapper.selectList(new LambdaQueryWrapper<Paper>().eq(Paper::getExamId, examId).orderByAsc(Paper::getSequence));
            for (Paper paper : papers) {
                if (paperClassId.equals("1")) {
                    if (paper.getSequence().equals("1")) {
                        String objectName = paper.getPaperUrl();
                        InputStream inputStream = ossClient.getObject(bucketName, objectName).getObjectContent();
                        if (inputStream != null) {
                            BufferedImage image = ImageIO.read(inputStream);
                            //计算每个小图的宽度和高度
                            int[] chunkWidth = new int[]{0, 210, 427, 720};
                            int count = 0;
                            BufferedImage[] imgs = new BufferedImage[3];
                            for (int y = 0; y < 3; y++) {
                                //设置小图的大小和类型
                                imgs[count] = new BufferedImage(image.getWidth(), chunkWidth[y + 1] - chunkWidth[y], image.getType()); //容易产生内存溢出，将JVM内存调大。
                                //写入图像内容
                                Graphics2D gr = imgs[count++].createGraphics();
                                gr.drawImage(image,
                                        0, 0,
                                        image.getWidth(), chunkWidth[y + 1] - chunkWidth[y],
                                        0, chunkWidth[y],
                                        image.getWidth(), chunkWidth[y + 1],
                                        null);
                                gr.dispose();
                            }
                            //修改paper中的cut
                            paper.setCut("1");
                            paperMapper.updateById(paper);
                            //上传到oss和数据库字段
                            String newObjectName = paper.getExamId() + "/questions/" + paper.getStudentId() + "/" + paper.getSequence() + "/";
                            for (int i = 0; i < 3; i++) {
                                //获取图片的二进制数据
                                // 将 BufferedImage 转换为 InputStream
                                InputStream inputStream1 = bufferedImageToInputStream(imgs[i]);
                                //获取图片的元数据
                                ObjectMetadata objectMetadata = new ObjectMetadata();
                                objectMetadata.setContentLength(inputStream1.available());
                                objectMetadata.setContentType("image/jpg");
                                //上传图片
                                PutObjectResult putObjectResult = ossClient.putObject(bucketName, newObjectName + String.valueOf(i + 1) + ".jpg", inputStream1);
                                System.out.println("上传成功");

                                //保存到answer表中
                                LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                                answerLambdaQueryWrapper.eq(Answer::getPaperId, paper.getPaperId()).eq(Answer::getAnswerId, String.valueOf(i + 1));
                                Answer oldanswer = ansMapper.selectOne(answerLambdaQueryWrapper);
                                Answer answer1 = new Answer();
                                answer1.setAnswerUrl(newObjectName + String.valueOf(i + 1) + ".jpg");
                                answer1.setAnswerId(String.valueOf(i + 1));
                                answer1.setPaperId(paper.getPaperId());
                                if (oldanswer == null) {
                                    ansMapper.insert(answer1);
                                } else {
                                    answer1.setId(oldanswer.getId());
                                    ansMapper.updateById(answer1);
                                }
                            }
                        }
                    } else if (paper.getSequence().equals("2")) {
                        String objectName = paper.getPaperUrl();
                        InputStream inputStream = ossClient.getObject(bucketName, objectName).getObjectContent();
                        if (inputStream != null) {
                            BufferedImage image = ImageIO.read(inputStream);
                            //计算每个小图的宽度和高度
                            int[] chunkWidth = new int[]{0, 340, 720};
                            int count = 0;
                            BufferedImage[] imgs = new BufferedImage[2];
                            for (int y = 0; y < 2; y++) {
                                //设置小图的大小和类型
                                imgs[count] = new BufferedImage(image.getWidth(), chunkWidth[y + 1] - chunkWidth[y], image.getType()); //容易产生内存溢出，将JVM内存调大。
                                //写入图像内容
                                Graphics2D gr = imgs[count++].createGraphics();
                                gr.drawImage(image,
                                        0, 0,
                                        image.getWidth(), chunkWidth[y + 1] - chunkWidth[y],
                                        0, chunkWidth[y],
                                        image.getWidth(), chunkWidth[y + 1],
                                        null);
                                gr.dispose();
                            }
                            //修改paper中的cut
                            paper.setCut("1");
                            paperMapper.updateById(paper);
                            //上传到oss和数据库字段
                            String newObjectName = paper.getExamId() + "/questions/" + paper.getStudentId() + "/" + paper.getSequence() + "/";
                            for (int i = 0; i < 2; i++) {
                                //获取图片的二进制数据
                                // 将 BufferedImage 转换为 InputStream
                                InputStream inputStream1 = bufferedImageToInputStream(imgs[i]);
                                //获取图片的元数据
                                ObjectMetadata objectMetadata = new ObjectMetadata();
                                objectMetadata.setContentLength(inputStream1.available());
                                objectMetadata.setContentType("image/jpg");
                                //上传图片
                                PutObjectResult putObjectResult = ossClient.putObject(bucketName, newObjectName + String.valueOf(i + 4) + ".jpg", inputStream1);
                                System.out.println("上传成功");

                                //保存到answer表中
                                LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                                answerLambdaQueryWrapper.eq(Answer::getPaperId, paper.getPaperId()).eq(Answer::getAnswerId, String.valueOf(i + 4));
                                Answer oldanswer = ansMapper.selectOne(answerLambdaQueryWrapper);
                                Answer answer1 = new Answer();
                                answer1.setAnswerUrl(newObjectName + String.valueOf(i + 4) + ".jpg");
                                answer1.setAnswerId(String.valueOf(i + 4));
                                answer1.setPaperId(paper.getPaperId());
                                if (oldanswer == null) {
                                    ansMapper.insert(answer1);
                                } else {
                                    answer1.setId(oldanswer.getId());
                                    ansMapper.updateById(answer1);
                                }
                            }
                        }
                    }
                }
                else if (paperClassId.equals("2")) {
                    String objectName = paper.getPaperUrl();
                    InputStream inputStream = ossClient.getObject(bucketName, objectName).getObjectContent();
                    if (inputStream != null) {
                        //修改paper中的cut
                        paper.setCut("1");
                        paperMapper.updateById(paper);
                        //上传到oss和数据库字段
                        String newObjectName = paper.getExamId() + "/questions/" + paper.getStudentId() + "/" + paper.getSequence() + "/";
                        //获取图片的二进制数据
                        // 将 BufferedImage 转换为 InputStream
                        //获取图片的元数据
                        ObjectMetadata objectMetadata = new ObjectMetadata();
                        objectMetadata.setContentLength(inputStream.available());
                        objectMetadata.setContentType("image/jpg");
                        //上传图片
                        PutObjectResult putObjectResult = ossClient.putObject(bucketName, newObjectName + "1" + ".jpg", inputStream);
                        System.out.println("上传成功");

                        //保存到answer表中
                        LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        answerLambdaQueryWrapper.eq(Answer::getPaperId, paper.getPaperId()).eq(Answer::getAnswerId, "1");
                        Answer oldanswer = ansMapper.selectOne(answerLambdaQueryWrapper);
                        Answer answer1 = new Answer();
                        answer1.setAnswerUrl(newObjectName + "1" + ".jpg");
                        answer1.setAnswerId("1");
                        answer1.setPaperId(paper.getPaperId());
                        if (oldanswer == null) {
                            ansMapper.insert(answer1);
                        } else {
                            answer1.setId(oldanswer.getId());
                            ansMapper.updateById(answer1);
                        }

                    }
                }
                else if(paperClassId.equals("3")){
                    if (paper.getSequence().equals("1")){
                        String objectName = paper.getPaperUrl();
                        InputStream inputStream = ossClient.getObject(bucketName, objectName).getObjectContent();
                        BufferedImage image = ImageIO.read(inputStream);
                        //计算每个小图的宽度和高度
                        int[] chunkWidth = new int[]{0, 580, 1100, 1651};//580 520 550
                        int count = 0;
                        BufferedImage imgs[] = new BufferedImage[3];
                        for (int y = 0; y < 3; y++) {
                            //设置小图的大小和类型
                            imgs[count] = new BufferedImage(chunkWidth[y + 1] - chunkWidth[y], image.getHeight(), image.getType()); //容易产生内存溢出，将JVM内存调大。
                            //写入图像内容
                            Graphics2D gr = imgs[count++].createGraphics();
                            gr.drawImage(image,
                                    0, 0,
                                    chunkWidth[y + 1] - chunkWidth[y], image.getHeight(),
                                    chunkWidth[y], 0,
                                    chunkWidth[y + 1], image.getHeight(),
                                    null);
                            gr.dispose();
                        }
                        count=0;
                        int[][] chunkHeight=new int[][]{{0,533,633,813,1133},{0,120,410,1133},{0,120,1133}};
                        BufferedImage[] imgList = new BufferedImage[9];
                        for (int i = 0; i < chunkHeight.length; i++) {
                            for (int j = 0; j < chunkHeight[i].length-1; j++) {
                                imgList[count]=new BufferedImage(imgs[i].getWidth(),chunkHeight[i][j+1]-chunkHeight[i][j],imgs[i].getType());
                                Graphics2D graphics = imgList[count++].createGraphics();
                                graphics.drawImage(imgs[i],
                                        0,0,
                                        imgs[i].getWidth(),chunkHeight[i][j+1]-chunkHeight[i][j],
                                        0,chunkHeight[i][j],
                                        imgs[i].getWidth(),chunkHeight[i][j+1],
                                        null
                                );
                                graphics.dispose();
                            }
                        }
                        BufferedImage img2 = imgList[3];
                        BufferedImage img5 = imgList[5];
                        // 创建合并后的图片
                        int combinedWidth = Math.max(img2.getWidth(),img5.getWidth());
                        int combinedHeight = img2.getHeight()+img5.getHeight();
                        BufferedImage combinedImg = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_RGB);
                        // 将两张图片绘制到合并后的图片中
                        combinedImg.createGraphics().drawImage(img2, 0, 0, null);
                        combinedImg.createGraphics().drawImage(img5, 0, img2.getHeight(), null);
                        BufferedImage[] newImages = new BufferedImage[8];
                        int index=0;
                        for (int i = 0; i < imgList.length; i++) {
                            if(i!=3&&i!=5){
                                newImages[index++]=imgList[i];
                            }else if(i==3){
                                newImages[index++]=combinedImg;
                            }
                        }

                        //修改paper中的cut
                        paper.setCut("1");
                        paperMapper.updateById(paper);

                        //上传到oss和数据库字段
                        String newObjectName = paper.getExamId() + "/questions/" + paper.getStudentId() + "/" + paper.getSequence() + "/";
                        int num=1;
                        for (int i = 0; i < newImages.length; i++) {
                            //获取图片的二进制数据
                            // 将 BufferedImage 转换为 InputStream
                            InputStream inputStream1 = bufferedImageToInputStream(newImages[i]);
                            //获取图片的元数据
                            ObjectMetadata objectMetadata = new ObjectMetadata();
                            objectMetadata.setContentLength(inputStream1.available());
                            objectMetadata.setContentType("image/jpg");
                            //上传图片
                            PutObjectResult putObjectResult = ossClient.putObject(bucketName, newObjectName + String.valueOf(i + 1) + ".jpg", inputStream1);
                            System.out.println("上传成功");

                            if(i==1||i==2||i==3||i==5||i==7){
                                LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                                answerLambdaQueryWrapper.eq(Answer::getPaperId, paper.getPaperId()).eq(Answer::getAnswerId, String.valueOf(num));
                                Answer oldanswer = ansMapper.selectOne(answerLambdaQueryWrapper);
                                Answer answer1 = new Answer();
                                answer1.setAnswerUrl(newObjectName + String.valueOf(i+1) + ".jpg");
                                answer1.setAnswerId(String.valueOf(num));
                                answer1.setPaperId(paper.getPaperId());
                                if (oldanswer == null) {
                                    ansMapper.insert(answer1);
                                } else {
                                    answer1.setId(oldanswer.getId());
                                    ansMapper.updateById(answer1);
                                }
                                num++;
                            }
                            //保存到answer表中
                        }

                    }else if(paper.getSequence().equals("2")){
                        String objectName = paper.getPaperUrl();
                        InputStream inputStream = ossClient.getObject(bucketName, objectName).getObjectContent();
                        BufferedImage originalImage = ImageIO.read(inputStream);

                        // 切割宽度范围
                        int[] chunkWidth = {0, 555, 1090, 1650}; // 根据需要调整切割的宽度范围

                        // 创建切割后的图片数组
                        BufferedImage[] splitImages = new BufferedImage[3];

                        // 循环进行切割
                        for (int i = 0; i < 3; i++) {
                            // 计算切割后的图片宽度
                            int width = chunkWidth[i + 1] - chunkWidth[i];

                            // 创建新的图片
                            splitImages[i] = new BufferedImage(width, originalImage.getHeight(), originalImage.getType());

                            // 绘制切割后的图片
                            Graphics2D g2d = splitImages[i].createGraphics();
                            g2d.drawImage(originalImage,
                                    0, 0,
                                    width, originalImage.getHeight(),
                                    chunkWidth[i], 0,
                                    chunkWidth[i + 1], originalImage.getHeight(),
                                    null);
                            g2d.dispose();
                        }
                        //修改paper中的cut
                        paper.setCut("1");
                        paperMapper.updateById(paper);
                        //上传到oss和数据库字段
                        String newObjectName = paper.getExamId() + "/questions/" + paper.getStudentId() + "/" + paper.getSequence() + "/";
                        for (int i = 0; i < splitImages.length; i++) {
                            //获取图片的二进制数据
                            // 将 BufferedImage 转换为 InputStream
                            InputStream inputStream1 = bufferedImageToInputStream(splitImages[i]);
                            //获取图片的元数据
                            ObjectMetadata objectMetadata = new ObjectMetadata();
                            objectMetadata.setContentLength(inputStream1.available());
                            objectMetadata.setContentType("image/jpg");
                            //上传图片
                            PutObjectResult putObjectResult = ossClient.putObject(bucketName, newObjectName + String.valueOf(i + 6) + ".jpg", inputStream1);
                            System.out.println("上传成功");

                            //保存到answer表中
                            LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
                            answerLambdaQueryWrapper.eq(Answer::getPaperId, paper.getPaperId()).eq(Answer::getAnswerId, String.valueOf(i + 6));
                            Answer oldanswer = ansMapper.selectOne(answerLambdaQueryWrapper);
                            Answer answer1 = new Answer();
                            answer1.setAnswerUrl(newObjectName + String.valueOf(i + 6) + ".jpg");
                            answer1.setAnswerId(String.valueOf(i + 6));
                            answer1.setPaperId(paper.getPaperId());
                            if (oldanswer == null) {
                                ansMapper.insert(answer1);
                            } else {
                                answer1.setId(oldanswer.getId());
                                ansMapper.updateById(answer1);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭 OSS 客户端
            ossClient.shutdown();
        }
        objectResponse.setData(true);
        return objectResponse;
    }

    /**
     * 将BufferedImage转换为InputStream
     *
     * @param image
     * @return
     */
    public InputStream bufferedImageToInputStream(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }

}
