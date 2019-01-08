package com.javawebinar.basejava.webapp.storage;


import com.javawebinar.basejava.webapp.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}
