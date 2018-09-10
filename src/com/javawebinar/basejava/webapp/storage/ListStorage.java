package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected void toUpdate(Resume resume, Integer searchKey) {
        list.set(searchKey, resume);
    }

    @Override
    protected void toSave(Resume resume, Integer searchKey) {
        list.add(resume);
    }

    @Override
    protected Resume toGet(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected void toDelete(Integer searchKey) {
        list.remove((searchKey).intValue());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected List<Resume> getNewList() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
