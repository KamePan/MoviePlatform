package cn.edu.ecnu.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class FileService {

    @Value("${aliyun.oss.file.endpoint}")
    private  String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private  String accessKeyId;
    @Value("${aliyun.oss.file.keysecret}")
    private  String accessKeySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private  String bucketName;

    public String upload(MultipartFile file) {

        String url = null;

        try {
            //创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //保证文件名唯一，去掉uuid中的'-'
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;

            //把文件按日期分类，构建日期路径：avatar/2019/02/26/文件名
            Date time = Calendar.getInstance().getTime();
            String datePath = new SimpleDateFormat("yyyy/MM/dd").format(time);

            //拼接
            fileName=datePath+"/"+fileName;

            //调用oss方法上传到阿里云
            //第一个参数：Bucket名称
            //第二个参数：上传到oss文件路径和文件名称
            //第三个参数：上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            //把上传后把文件url返回
            //https://xppll.oss-cn-beijing.aliyuncs.com/01.jpg
            url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            //关闭OSSClient
            ossClient.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }
}
