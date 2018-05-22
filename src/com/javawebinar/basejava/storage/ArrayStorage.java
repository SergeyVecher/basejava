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
        System.out.println("All resumes were deleted successfully");
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            printUpdateOrSaveResume("Update", resume.getUuid());
        } else {
            printResumeIsNotExist(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        if (size == STORAGE_MAX_SIZE) {
            System.out.println("This database is overflow");
        } else if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Resume with uuid = " + resume.getUuid() + " is already in the database");
        } else {
            storage[size] = resume;
            size++;
            printUpdateOrSaveResume("Save", resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        printResumeIsNotExist(uuid);
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
            System.out.println("Resume with uuid = " + uuid + " was deleted successfully");
        } else {
            printResumeIsNotExist(uuid);
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

    private void printResumeIsNotExist(String uuid) {
        System.out.println("In the database there is not resume with uuid = " + uuid);
    }

    private void printUpdateOrSaveResume(String updateOrSave, String uuid) {
        System.out.println(updateOrSave + " resume with uuid = " + uuid + "  was completed successfully");
    }
}
