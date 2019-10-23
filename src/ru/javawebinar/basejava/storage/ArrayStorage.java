package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < resumesCounter; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void fillDeletedElement(int resumeIndex) {
        storage[resumeIndex] = storage[resumesCounter - 1];
    }

    @Override
    protected void insertElement(Resume resume, int resumeIndex) {
        storage[resumesCounter] = resume;
    }
}
