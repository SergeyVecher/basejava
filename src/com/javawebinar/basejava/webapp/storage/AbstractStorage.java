package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.ExistStorageException;
import com.javawebinar.basejava.webapp.exception.NotExistStorageException;
import com.javawebinar.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        toUpdate(resume, getNotExistSearchKey(resume.getUuid()));
    }

    @Override
    public void save(Resume resume) {
        toSave(resume, getExistSearchKey(resume.getUuid()));
    }

    @Override
    public Resume get(String uuid) {
        return toGet(getNotExistSearchKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        toDelete(getNotExistSearchKey(uuid));
    }

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else return searchKey;
    }

    private Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getNewList();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> getNewList();

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void toUpdate(Resume resume, Object searchKey);

    protected abstract void toSave(Resume resume, Object searchKey);

    protected abstract Resume toGet(Object searchKey);

    protected abstract void toDelete(Object searchKey);
}
