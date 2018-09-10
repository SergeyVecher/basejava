package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.ExistStorageException;
import com.javawebinar.basejava.webapp.exception.NotExistStorageException;
import com.javawebinar.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void update(Resume resume) {
        toUpdate(resume, getNotExistSearchKey(resume.getUuid()));
        LOG.info("update " + resume);
    }

    @Override
    public void save(Resume resume) {
        toSave(resume, getExistSearchKey(resume.getUuid()));
        LOG.info("save " + resume);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("update " + uuid);
        return toGet(getNotExistSearchKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        toDelete(getNotExistSearchKey(uuid));
        LOG.info("delete " + uuid);
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning(uuid + " is exist");
            throw new ExistStorageException(uuid);
        } else return searchKey;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning(uuid + " is not exist");
            throw new NotExistStorageException(uuid);
        } else return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = getNewList();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> getNewList();

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void toUpdate(Resume resume, SK searchKey);

    protected abstract void toSave(Resume resume, SK searchKey);

    protected abstract Resume toGet(SK searchKey);

    protected abstract void toDelete(SK searchKey);
}
