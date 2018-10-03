package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.StorageException;
import com.javawebinar.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(dir, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::toDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected void toUpdate(Resume r, Path Path) {

    }

    @Override
    protected boolean isExist(Path Path) {
        return false;
    }

    @Override
    protected void toSave(Resume resume, Path Path) {
    }

    @Override
    protected Resume toGet(Path Path) {
        return null;
    }

    @Override
    protected void toDelete(Path Path) {
    }

    @Override
    protected List<Resume> getNewList() {
        List<Path> PathList = getPathList(directory);
        List<Resume> listOut = new ArrayList<>();
        PathList.forEach(x -> listOut.add(toGet(x)));
        return listOut;
    }

    private List<Path> getPathList(Path Path) {
        return null;
    }

    protected abstract void toWrite(Resume r, OutputStream outputStream) throws IOException;

    protected abstract Resume toRead(InputStream inputStream) throws IOException;
}
