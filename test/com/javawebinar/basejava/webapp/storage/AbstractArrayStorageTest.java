package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.ExistStorageException;
import com.javawebinar.basejava.webapp.exception.NotExistStorageException;
import com.javawebinar.basejava.webapp.exception.StorageException;
import com.javawebinar.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private Resume resume1 = new Resume("uuid1");
    private Resume resume2 = new Resume("uuid2");
    private Resume resume3 = new Resume("uuid3");
    private Resume resume4 = new Resume("uuid4");

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void update() {
        Resume resume5 = new Resume("uuid3");
        storage.update(resume5);
        assertEquals(resume5, storage.get("uuid3"));
    }

    @Test
    public void save() {
        storage.save(resume4);
        assertEquals(4, storage.size());
        assertEquals(resume4, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume2);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        storage.clear();
        for (int i = 0; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
    }

    @Test
    public void get() {
        assertEquals(resume1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("xxx");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("xxx");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
        storage.get("uuid1");
    }

    @Test
    public void getAll() {
        Resume[] copyStorage = storage.getAll();
        assertEquals(storage.size(), copyStorage.length);
        assertEquals(storage.get("uuid1"), copyStorage[0]);
        assertEquals(storage.get("uuid2"), copyStorage[1]);
        assertEquals(storage.get("uuid3"), copyStorage[2]);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}