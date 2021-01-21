package com.dpapazikov.awsimageupload.config;

public enum BucketName {

    PROFILE_IMAGE("dpap-image-upload-bucket");
    private final String bucketName;

    BucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getBucketName()
    {
        return bucketName;
    }
}
