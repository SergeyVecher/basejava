package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.Config;


public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }
}
