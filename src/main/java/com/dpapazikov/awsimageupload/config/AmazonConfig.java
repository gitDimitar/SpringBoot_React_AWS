package com.dpapazikov.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 s3()
    {
        AWSCredentials cred = new BasicAWSCredentials("AKIAIIK5HX7ZKMRIHZ7A", "B3KJcy13n7mlr1TR1Zkx7KqETiDj/UKRM1Xf0YF5");
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider((cred)))
                .build();
    }
}
