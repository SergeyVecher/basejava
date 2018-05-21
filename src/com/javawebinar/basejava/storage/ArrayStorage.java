package com.javawebinar.basejava.storage;

import com.javawebinar.basejava.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_MAX_SIZE = 10000;
    private Resume[] storage = new Resume[STORAGE_MAX_SIZE];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println(" All resumes were deleted successfully");
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Update resume " + resume.getUuid() + " was completed successfully");
        } else {
            printInTheDatabaseThereIsNotThisResume(resume.getUuid());
        }
    }

    public void save(Resume r) {
        if (size == STORAGE_MAX_SIZE) {
            System.out.println("This database is overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        printInTheDatabaseThereIsNotThisResume(uuid);
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
            System.out.println(uuid + " resume was deleted successfully");
        } else {
            printInTheDatabaseThereIsNotThisResume(uuid);
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public String size() {
        return "This is actual size = " + size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    private void printInTheDatabaseThereIsNotThisResume(String uuid) {
        System.out.println("In the database there is not " + uuid + " resume");
    }
}
