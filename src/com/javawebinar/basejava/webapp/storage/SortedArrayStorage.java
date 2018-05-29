package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void fillDeletedElement(int index) {
//        int numMoved = size - index;
//        if (numMoved > 0) {
//            System.arraycopy(storage, index + 1, storage, index, numMoved);
//        }
        storage[index] = storage[size];
        Arrays.sort(storage,0,size);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        //int insertIdx = -index - 1;
        //System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[-index-1] = resume;
        Arrays.sort(storage,0,size);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
