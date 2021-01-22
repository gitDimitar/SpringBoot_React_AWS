package com.dpapazikov.awsimageupload.service;

import com.dpapazikov.awsimageupload.DataStore.FakeUserDataStore;
import com.dpapazikov.awsimageupload.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class UserDataAccessService {

    private final FakeUserDataStore fakeUserDataStore;

    @Autowired
    public UserDataAccessService(FakeUserDataStore fakeUserDataStore)
    {
        this.fakeUserDataStore = fakeUserDataStore;
    }

    public List<User> getUsers()
    {
        return fakeUserDataStore.getUsers();
    }
}
