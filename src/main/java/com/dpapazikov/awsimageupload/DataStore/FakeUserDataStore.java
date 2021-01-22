package com.dpapazikov.awsimageupload.DataStore;

import com.dpapazikov.awsimageupload.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeUserDataStore {

    private static final List<User> USERS = new ArrayList<>();

    static {
        USERS.add(new User(1,"Jane Smith", null));
        USERS.add(new User(2, "John Jones", null));
    }

    public List<User> getUsers()
    {
        return USERS;
    }

}
