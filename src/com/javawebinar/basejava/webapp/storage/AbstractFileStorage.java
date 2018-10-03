package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.StorageException;
import com.javawebinar.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


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
        List<File> fileList = getFileList(directory);
        fileList.forEach(this::toDelete);
    }

    @Override
    public int size() {
        String[] listFiles = directory.list();
        if (listFiles == null) {
            throw new StorageException("Directory read error", null);
        }
        return listFiles.length;
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
            return toRead(file);
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
    protected List<Resume> getNewList() {
        List<File> fileList = getFileList(directory);
        List<Resume> listOut = new ArrayList<>();
        fileList.forEach(x -> listOut.add(toGet(x)));
        return listOut;
    }

    private List<File> getFileList(File file) {
        if (file.listFiles() == null) {
            throw new StorageException("Directory read error", null);
        } else return Arrays.asList(file.listFiles());
    }

    protected abstract void toWrite(Resume r, File file) throws IOException;

    protected abstract Resume toRead(File file) throws IOException;
}
