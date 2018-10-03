package com.javawebinar.basejava.webapp.storage;

import com.javawebinar.basejava.webapp.exception.ExistStorageException;
import com.javawebinar.basejava.webapp.exception.NotExistStorageException;
import com.javawebinar.basejava.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR =
            new File("D:\\java\\project\\basejava\\src\\com\\javawebinar\\basejava\\webapp\\storage\\basket");
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static Resume resume1 = new Resume(UUID_1, "Alex");
    private static Resume resume2 = new Resume(UUID_2, "Bond");
    private static Resume resume3 = new Resume(UUID_3, "Clare");
    private static Resume resume4 = new Resume(UUID_4, "Dan");

    protected Storage storage;

    static {
        resume1.addContact(ContactType.EMAIL, "email");
        resume1.addContact(ContactType.PHONE, "phone number");
        resume1.addContact(ContactType.GITHUB, "profile GitHub");
        resume1.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(new ContentSection("Achievement one"),
                new ContentSection("Achievement two"))));
        resume1.addSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(new Company("University one",
                        "address", Arrays.asList(new Company.PeriodInCompany(2003, Month.MAY, 2008, Month.MAY, "student"),
                new Company.PeriodInCompany(2003, Month.MAY, 2008, Month.MAY, "student"))),
                new Company("University two",
                        "address", Arrays.asList(new Company.PeriodInCompany(2011, Month.MAY, 2014, Month.MAY, "student"),
                        new Company.PeriodInCompany(2011, Month.MAY, 2014, Month.MAY, "student"))))));
    }

    protected AbstractStorageTest(Storage storage) {
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
        storage.update(resume3);
        assertEquals(resume3, storage.get(UUID_3));
    }

    @Test
    public void save() {
        storage.save(resume4);
        assertEquals(4, storage.size());
        assertEquals(resume4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume2);
    }

    @Test
    public void get() {
        assertEquals(resume1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test
    public void getAll() {
        List<Resume> copyStorage = Arrays.asList(resume1, resume2, resume3);
        assertEquals(copyStorage, storage.getAllSorted());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}