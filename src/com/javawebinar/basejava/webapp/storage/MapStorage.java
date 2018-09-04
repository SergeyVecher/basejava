package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new TreeMap<>();

    @Override
    protected void toUpdate(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected void toSave(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected Resume toGet(Object searchKey) {
        return map.get((String) searchKey);
    }

    @Override
    protected void toDelete(Object searchKey) {
        map.remove((String) searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey((String) searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
