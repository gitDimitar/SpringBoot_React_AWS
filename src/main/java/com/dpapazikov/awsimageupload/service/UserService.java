package com.dpapazikov.awsimageupload.service;

import com.dpapazikov.awsimageupload.config.BucketName;
import com.dpapazikov.awsimageupload.filestore.FileStore;
import com.dpapazikov.awsimageupload.model.User;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    private final UserDataAccessService userDataAccessService;
    private final FileStore fs;
    @Autowired
    public UserService(UserDataAccessService userDataAccessService, FileStore fs)
    {
        this.userDataAccessService = userDataAccessService;
        this.fs = fs;
    }

    public List<User> getUsers()
    {
        return userDataAccessService.getUsers();
    }

    public void uploadUserImage(int userId, MultipartFile file)
    {
        User uu;
        isEmpty(file);

        isImage(file);

        //TODO: Change this logic when real DB is implemented
        uu = userDataAccessService.getUserById(userId);
        if(uu == null)
        {
            return;
        }

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = getPathForUser(uu);
        String fileName = (file.getOriginalFilename() + "-" + UUID.randomUUID());
        try
        {
            fs.save(path, fileName, Optional.of(metadata),file.getInputStream());
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }

        userDataAccessService.setUserImage(userId, path);
        /*TODO:
           >> 1. Check if image is not empty
            >>2. Check if file is image
            >>3. Check weather user exists in DB
            <>4. Grab metadata from file
            5. Store image in S3 and update DB with S3 image link
         */
    }

    private String getPathForUser(User uu)
    {
        return String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), uu.getUserId());
    }

    public byte[] downloadUserImage(int userId)
    {
        User uu;

        //TODO: Change this logic when real DB is implemented
        uu = userDataAccessService.getUserById(userId);
        if(uu == null)
        {
            throw new IllegalStateException("User with ID:" + userId + " does not exist!");
        }

        return fs.download(getPathForUser(uu), uu.getUserImageLink().get());
    }

    private void isEmpty(MultipartFile file)
    {
        if(file.isEmpty())
        {
            throw (new IllegalStateException("File is empty"));
        }
    }

    private void isImage(MultipartFile file)
    {
        if(!Arrays.asList(ContentType.IMAGE_GIF.getMimeType(), ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType()).contains(file.getContentType()))
        {
            throw (new IllegalStateException("File content type is not as expected - " + file.getContentType()));
        }
    }


}
