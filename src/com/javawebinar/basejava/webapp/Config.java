package com.javawebinar.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPERTIES_FILE = new File("config/resume.properties");
    private static final Config INSTANCE = new Config();
    private File storageDir;

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            storageDir = new File(properties.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("invalid config fail" + PROPERTIES_FILE.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }
}
