package org.openapitools.api.impl;

import org.openapitools.model.User;
import org.openapitools.model.UserList;

import java.io.Closeable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ObjectStoreConnection extends Closeable {

    // Transaction commands

    void commitChanges();

    void rollbackChanges();

    void close();

    void setOperationTimeout(int opTimout);

    ObjectStoreConnection getConnection(boolean autoCommit, boolean readWrite);

    UserList getDBUserlist(String type, String xRequestID);

}
