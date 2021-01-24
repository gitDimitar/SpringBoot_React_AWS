package com.dpapazikov.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 s3()
    {
        String accessKey = "";
        String secretKey = "";
        File keys = new File("C:\\GitRepo\\rootkey.csv");
        try
        {
            Scanner s = new Scanner(keys);
            accessKey = s.nextLine();
            accessKey = accessKey.substring(accessKey.indexOf('=')+1, accessKey.length());
            System.out.println("Accesss key : " + accessKey);
            secretKey = s.nextLine();
            secretKey = secretKey.substring(secretKey.indexOf('=')+1, secretKey.length());
            System.out.println("Secret key : " + secretKey);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        AWSCredentials cred = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("eu-west-1")
                .withCredentials(new AWSStaticCredentialsProvider((cred)))
                .build();
    }
}
