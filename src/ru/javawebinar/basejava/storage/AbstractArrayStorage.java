package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int resumesCounter = 0;
    protected int resumeIndex = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumesCounter, null);
        resumesCounter = 0;
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, resumesCounter);
    }

    public void save(Resume resume) {
        resumeIndex = getResumeIndex(resume.getUuid());
        if (resumeIndex > 0) {
            System.out.println("Resume " + resume.getUuid() + " already in the database");
        } else if (resumesCounter == STORAGE_LIMIT) {
            System.out.println("ERROR: The database is full");
        } else {
            insertElement(resume, resumeIndex);
            storage[resumesCounter] = resume;
            resumesCounter++;
        }
    }

    public void delete(String uuid) {
        resumeIndex = getResumeIndex(uuid);
        if (resumeIndex < 0) {
            System.out.println("ERROR: There is no resume with this uuid in the database");
        } else {
            fillDeletedElement(resumeIndex);
            storage[resumesCounter - 1] = null;
            resumesCounter--;
        }
    }

    public int size() {
        return resumesCounter;
    }

    public Resume get(String uuid) {
        resumeIndex = getResumeIndex(uuid);
        if (resumeIndex > 0){
            return storage[resumeIndex];
        }
        System.out.println("ERROR: No resume " + uuid + " in the database");
        return null;
    }

    protected abstract int getResumeIndex(String uuid);

    protected abstract void fillDeletedElement(int resumeIndex);

    protected abstract void insertElement(Resume resume, int resumeIndex);
}
