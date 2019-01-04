package com.javawebinar.basejava.webapp.storage.serializer;

import com.javawebinar.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public interface StreamSerializer {
    void toWrite(Resume resume, OutputStream outputStream) throws IOException;

    Resume toRead(InputStream inputStream) throws IOException;
}

