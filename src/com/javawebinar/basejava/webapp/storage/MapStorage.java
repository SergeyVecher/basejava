package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private Map<Object, Resume> map = new TreeMap<>();

    @Override
    protected void toUpdate(Resume resume, Object searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected void toSave(Resume resume, Object searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected Resume toGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void toDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }
}
