package com.javawebinar.basejava.webapp;

import com.javawebinar.basejava.webapp.exception.ExistStorageException;
import com.javawebinar.basejava.webapp.exception.NotExistStorageException;
import com.javawebinar.basejava.webapp.model.Resume;
import com.javawebinar.basejava.webapp.storage.SortedArrayStorage;
import com.javawebinar.basejava.webapp.storage.Storage;

public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Alex");
        Resume r2 = new Resume("uuid2", "Bond");
        Resume r3 = new Resume("uuid3", "Clare");
        Resume r4 = new Resume("uuid4", "Dan");


        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        try {
            ARRAY_STORAGE.save(r1);
        } catch (ExistStorageException e) {
            e.printStackTrace();
        }
        ARRAY_STORAGE.update(r1);
        try {
            ARRAY_STORAGE.update(r4);
        } catch (NotExistStorageException e) {
            e.printStackTrace();
        }

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        try {
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (NotExistStorageException e) {
            e.printStackTrace();
        }

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
