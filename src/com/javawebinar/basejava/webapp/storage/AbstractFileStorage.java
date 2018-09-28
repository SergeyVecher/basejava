package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.StorageException;
import com.javawebinar.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.*;


public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        List<File> fileList = Arrays.asList(Objects.requireNonNull(directory.listFiles()));
        fileList.forEach(this::toDelete);
    }

    @Override
    public int size() {
        List<File> fileList = Arrays.asList(Objects.requireNonNull(directory.listFiles()));
        return fileList.size();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void toUpdate(Resume r, File file) {
        try {
            toWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
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
            toWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume toGet(File file) {
        try {
            return toRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void toDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("IO error", file.getName());
        }
    }

    @Override
    protected List<Resume> getNewList() {
        List<File> fileList = Arrays.asList(Objects.requireNonNull(directory.listFiles()));
        List<Resume> listOut = new ArrayList<>();
        fileList.forEach(x -> listOut.add(toGet(x)));
        return listOut;
    }


    protected abstract void toWrite(Resume r, File file) throws IOException;

    protected abstract Resume toRead(File file) throws IOException;
}
