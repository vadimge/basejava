package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

//    private static final int STORAGE_LIMIT = 10_000;
//
//    private Resume[] storage = new Resume[STORAGE_LIMIT];
//    private int resumesCounter = 0;
//    private int resumeIndex = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumesCounter, null);
        resumesCounter = 0;
    }

    public void save(Resume resume) {
        if (getResumeIndex(resume.getUuid()) != -1) {
            System.out.println("Resume " + resume.getUuid() + " already in the database");
        } else if (resumesCounter < STORAGE_LIMIT) {
            storage[resumesCounter] = resume;
            resumesCounter++;
        } else {
            System.out.println("ERROR: The database is full");
        }
    }

    public void update(Resume resume) {
        resumeIndex = getResumeIndex(resume.getUuid());
        if (resumeIndex != -1) {
            storage[resumeIndex] = resume;
            System.out.println("Resume " + resume.getUuid() + " updated");
        } else {
            System.out.println("ERROR: There is no such resume in the database");
        }
    }

    public void delete(String uuid) {
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
    public Resume[] getAll() {
//        Resume[] allResumes = new Resume[resumesCounter];
//        System.arraycopy(storage, 0, allResumes, 0, resumesCounter);
//        return allResumes;
        return Arrays.copyOfRange(storage, 0, resumesCounter);
    }

    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < resumesCounter; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
