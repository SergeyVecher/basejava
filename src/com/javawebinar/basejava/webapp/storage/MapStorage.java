package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> map = new TreeMap<>();

    @Override
    protected void toUpdate(Resume resume, String searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected void toSave(Resume resume, String searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected Resume toGet(String searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void toDelete(String searchKey) {
        map.remove(searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
