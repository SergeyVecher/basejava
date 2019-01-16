package com.javawebinar.basejava.webapp;

import com.javawebinar.basejava.webapp.storage.SqlStorage;
import com.javawebinar.basejava.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPERTIES_FILE = new File("config/resume.properties");
    private static final Config INSTANCE = new Config();
    private File storageDir;
    private final Storage storage;

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            storageDir = new File(properties.getProperty("storage.dir"));
            storage = new SqlStorage(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("invalid config fail" + PROPERTIES_FILE.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }
}
