package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage{

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int resumesCounter = 0;
    protected int resumeIndex = 0;

    public int size() {
        return resumesCounter;
    }

    public Resume get(String uuid) {
        resumeIndex = getResumeIndex(uuid);
        if (resumeIndex != -1){
            return storage[resumeIndex];
        }
        System.out.println("ERROR: No resume " + uuid + " in the database");
        return null;
    }

    protected abstract int getResumeIndex(String uuid);

}
