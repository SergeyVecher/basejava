package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.StorageException;
import com.javawebinar.basejava.webapp.model.Resume;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private StrategyIO strategyIO;

    protected PathStorage(String dir, StrategyIO strategyIO) {
        Objects.requireNonNull(dir, "directory must not be null");
        Objects.requireNonNull(strategyIO);
        directory = Paths.get(dir);
        this.strategyIO = strategyIO;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
    }

    @Override
    public void clear() {
        getPathList().forEach(this::toDelete);
    }

    @Override
    public int size() {
        return (int) getPathList().count();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return new File(directory.toString(), uuid).toPath();
    }

    @Override
    protected void toUpdate(Resume resume, Path path) {
        try {
            strategyIO.toWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", path.getFileName().toString());
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void toSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Path write error" + path, path.getFileName().toString());
        }
        toUpdate(resume, path);
    }

    @Override
    protected Resume toGet(Path path) {
        try {
            return strategyIO.toRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString());
        }
    }

    @Override
    protected void toDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString());
        }
    }

    @Override
    protected List<Resume> getNewList() {
        return getPathList().map(this::toGet).collect(Collectors.toList());
    }

    private Stream<Path> getPathList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Path read error", null);
        }
    }
}
