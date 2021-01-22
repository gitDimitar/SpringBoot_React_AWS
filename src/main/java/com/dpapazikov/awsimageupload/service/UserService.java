package com.dpapazikov.awsimageupload.service;

import com.dpapazikov.awsimageupload.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserService {

    private final UserDataAccessService userDataAccessService;

    @Autowired
    public UserService(UserDataAccessService userDataAccessService)
    {
        this.userDataAccessService = userDataAccessService;
    }

    public List<User> getUsers()
    {
        return userDataAccessService.getUsers();
    }

    public void uploadUserImage(int userId, MultipartFile file)
    {
        /*TODO:
            1. Check if image is not empty
            2. Check if file is image
            3. Check weather user exists in DB
            4. Grab metadata from file
            5. Store image in S3 and update DB with S3 image link
         */
    }
}
