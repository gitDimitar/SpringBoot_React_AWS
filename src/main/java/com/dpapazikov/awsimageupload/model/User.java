package com.dpapazikov.awsimageupload.model;

import java.util.Objects;
import java.util.Optional;

public class User {

    private int userId;
    private String userName;
    //S3 link-key
    private String userImageLink;

    public User()
    {
    }

    public User(int userId, String userName, String userImageLink)
    {
        this.userId = userId;
        this.userName = userName;
        this.userImageLink = userImageLink;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Optional<String> getUserImageLink()
    {
        return Optional.ofNullable(userImageLink);
    }

    public void setUserImageLink(String userImageLink)
    {
        this.userImageLink = userImageLink;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        User user = (User) o;
        return userId == user.userId &&
                userName.equals(user.userName) &&
                userImageLink.equals(user.userImageLink);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, userName, userImageLink);
    }
}
