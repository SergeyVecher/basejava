/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (Resume resume : storage) {
            resume = null;
        }
    }

    void save(Resume r) {
        for (Resume resume : storage) {
            if (resume == null) {
                resume=r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume r = null;
        for (Resume resume : storage) {
            if (resume.equals(uuid)) {
                r = resume;
            }
        } return r;
    }

    void delete(String uuid) {
        for (Resume resume : storage) {
            if (resume.equals(uuid)) {
                resume = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
    }
}
