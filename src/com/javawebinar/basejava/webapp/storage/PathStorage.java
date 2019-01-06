package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.StorageException;
import com.javawebinar.basejava.webapp.model.Resume;
import com.javawebinar.basejava.webapp.storage.serializer.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
    private StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        Objects.requireNonNull(dir, "directory must not be null");
        Objects.requireNonNull(streamSerializer);
        directory = Paths.get(dir);
        this.streamSerializer = streamSerializer;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::toDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void toUpdate(Resume resume, Path path) {
        try {
            streamSerializer.toWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", getFileName(path), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void toSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Path write error" + path, getFileName(path), e);
        }
        toUpdate(resume, path);
    }

    @Override
    protected Resume toGet(Path path) {
        try {
            return streamSerializer.toRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", getFileName(path), e);
        }
    }

    @Override
    protected void toDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getFilesList().map(this::toGet).collect(Collectors.toList());
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Path read error", e);
        }
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }
}
