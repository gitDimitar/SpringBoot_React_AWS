package com.dpapazikov.awsimageupload.controller;

import com.dpapazikov.awsimageupload.model.User;
import com.dpapazikov.awsimageupload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getUsers()
    {
        return userService.getUsers();
    }

    @PostMapping(path = "/user/{userId}/image/upload" ,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public void uploadUserImage(@PathVariable("userId") int userId, @RequestParam("file") MultipartFile file)
    {
        userService.uploadUserImage(userId, file);
    }
}
