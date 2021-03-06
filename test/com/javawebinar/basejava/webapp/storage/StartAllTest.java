package com.javawebinar.basejava.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
        MapSearchKeyResumeStorageTest.class,
        PathStorageTest.class,
        FileStorageTest.class,
        SqlStorageTest.class
})
public class StartAllTest {
}
