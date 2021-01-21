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
        File keys = new File("C:\\GitRepo\rootkey.csv");
        try
        {
            Scanner s = new Scanner(keys);
            String accessKey = s.nextLine();
            accessKey = accessKey.substring(accessKey.indexOf('='), accessKey.length()-1);
            String secretKey = s.nextLine();
            secretKey = secretKey.substring(secretKey.indexOf('='), secretKey.length()-1);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        AWSCredentials cred = new BasicAWSCredentials("accessKey", "secretKey");
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider((cred)))
                .build();
    }
}
