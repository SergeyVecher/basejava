package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.ExistStorageException;
import com.javawebinar.basejava.webapp.exception.NotExistStorageException;
import com.javawebinar.basejava.webapp.model.Resume;


public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        toUpdate(resume, getIsExistOrNotExistSearchKey(resume.getUuid(), false));
    }

    @Override
    public void save(Resume resume) {
        toSave(resume, getIsExistOrNotExistSearchKey(resume.getUuid(), true));
    }

    @Override
    public Resume get(String uuid) {
        return toGet(getIsExistOrNotExistSearchKey(uuid, false));
    }

    @Override
    public void delete(String uuid) {
        toDelete(getIsExistOrNotExistSearchKey(uuid, false));
    }

    private Object getIsExistOrNotExistSearchKey(String uuid, boolean saveOrNot) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey) & saveOrNot) {
            throw new ExistStorageException(uuid);
        } else if (!isExist(searchKey) & !saveOrNot) {
            throw new NotExistStorageException(uuid);
        } else return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void toUpdate(Resume resume, Object searchKey);

    protected abstract void toSave(Resume resume, Object searchKey);

    protected abstract Resume toGet(Object searchKey);

    protected abstract void toDelete(Object isExistOrNotExistSearchKey);
}
