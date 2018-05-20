import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_MAX_SIZE = 10000;
    private Resume[] storage = new Resume[STORAGE_MAX_SIZE];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void update(Resume resume) {
        for (Resume resume1 : storage) {
            if (resume.equals(resume1)) {
                System.out.println("This resume is in the database");
            } else {
                save(resume);
            }
        }
    }

    void save(Resume r) {
        if (size == STORAGE_MAX_SIZE) {
            System.out.println("Array overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            } else {
                System.out.println("This resume is not in the database");
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                size--;
                storage[i] = storage[size];
                storage[size] = null;
            } else {
                System.out.println("This resume is not in the database");
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
