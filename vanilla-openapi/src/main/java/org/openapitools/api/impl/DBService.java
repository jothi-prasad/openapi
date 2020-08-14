package org.openapitools.api.impl;

import org.openapitools.model.User;
import org.openapitools.model.UserList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBService implements ObjectStoreConnection {

    @Override
    public void commitChanges() {

    }

    @Override
    public void rollbackChanges() {

    }

    @Override
    public void close() {

    }

    @Override
    public void setOperationTimeout(int opTimout) {

    }

    @Override
    public ObjectStoreConnection getConnection(boolean autoCommit, boolean readWrite) {
        return null;
    }

    @Override
    public UserList getDBUserlist(String type, String xRequestID) {
        User usr = new User();
        List<User> usrs = new ArrayList<>();
        UserList userlist = new UserList();
        usr.setName("jothip");
        usr.setUid(1234);
        usrs.add(usr);
        User usr1 = new User();
        usr1.setName("palakas");
        usr1.setUid(5678);
        usrs.add(usr1);
        userlist.setUsers(usrs);
        usr1 = new User();
        usr1.setName("Chandu");
        usr1.setUid(98765);
        usrs.add(usr1);
        userlist.setUsers(usrs);
        return userlist;
    }
}

