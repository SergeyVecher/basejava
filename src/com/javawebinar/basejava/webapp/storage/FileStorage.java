package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.StorageException;
import com.javawebinar.basejava.webapp.model.Resume;
import com.javawebinar.basejava.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private StreamSerializer streamSerializer;

    protected FileStorage(File directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(streamSerializer);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.streamSerializer = streamSerializer;
    }

    @Override
    public void clear() {
        getFileList().forEach(this::toDelete);
    }

    @Override
    public int size() {
        return (int) getFileList().count();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void toUpdate(Resume r, File file) {
        try {
            streamSerializer.toWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void toSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        toUpdate(resume, file);
    }

    @Override
    protected Resume toGet(File file) {
        try {
            return streamSerializer.toRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void toDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getFileList().map(this::toGet).collect(Collectors.toList());
    }

    private Stream<File> getFileList() {
        if (directory.listFiles() == null) {
            throw new StorageException("Directory read error");
        } else return Stream.of(directory.listFiles());
    }
}
