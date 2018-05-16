import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        // Arrays.fill used instead of the cycle FOR;
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (size == 9999) {  // Check out of bounds array
            System.out.println("Array overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    // Second version
//        try {
//            storage[size] = r;
//            size++;
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Array overflow");
//        }
//    }

    Resume get(String uuid) {
        // Without temporary variables
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
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
