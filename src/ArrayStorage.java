import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int resumesCounter = 0;
    private int resumeIndex = 0;

    void clear() {
        Arrays.fill(storage, 0, resumesCounter, null);
        resumesCounter = 0;
    }

    void save(Resume resume) {
        if (checkingTheSameResume(resume) != -1) {
            System.out.println("Resume " + resume.uuid + " already in the database");
        } else if (resumesCounter < storage.length) {
            storage[resumesCounter] = resume;
            resumesCounter++;
        } else {
            System.out.println("ERROR: The database is full");
        }
    }

    void update(Resume resume) {
        resumeIndex = checkingTheSameResume(resume);
        if (resumeIndex != -1) {
            storage[resumeIndex] = resume;
        } else {
            System.out.println("ERROR: There is no such resume in the database");
        }
    }

    Resume get(String uuid) {
        resumeIndex = getResumeIndex(uuid);
        if (resumeIndex != -1){
            return storage[resumeIndex];
        }else {
            System.out.println("ERROR: No resume " + uuid + " in the database");
            return null;
        }
    }

    void delete(String uuid) {
        resumeIndex = getResumeIndex(uuid);
        if (resumeIndex != -1) {
            storage[resumeIndex] = storage[resumesCounter - 1];
            storage[resumesCounter - 1] = null;
            resumesCounter--;
        } else {
            System.out.println("ERROR: There is no resume with this uuid in the database");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[resumesCounter];
        System.arraycopy(storage, 0, allResumes, 0, resumesCounter);
        return allResumes;
    }

    int size() {
        return resumesCounter;
    }

    private int checkingTheSameResume(Resume resume) {
        for (int i = 0; i < resumesCounter; i++) {
            if (storage[i].uuid.equals(resume.uuid)) {
                return i;
            }
        }
        return -1;
    }

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < resumesCounter; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
