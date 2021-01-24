package com.dpapazikov.awsimageupload.DataStore;

import com.dpapazikov.awsimageupload.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeUserDataStore {

    private static final List<User> USERS = new ArrayList<>();

    static {
        USERS.add(new User(1,"Jane Smith", "Image1.jpg-aedc871b-2748-4435-9472-b3e054e2c320"));
        USERS.add(new User(2, "John Jones", "Image2.jpg-b55e8c1b-122c-4000-924b-210fd3195d64"));
    }

    public List<User> getUsers()
    {
        return USERS;
    }

    public User getUserById(int id)
    {
        return USERS.get(id-1);
    }

    public void setUserImage(int id, String imageURL)
    {
        USERS.get(id-1).setUserImageLink(imageURL);
    }

}
