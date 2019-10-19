package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected int getResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, resumesCounter, searchKey);
    }

    @Override
    protected void fillDeletedElement(int resumeIndex) {
        int numMoved = resumesCounter - resumeIndex - 1;
        if (numMoved > 0){
            System.arraycopy(storage, resumeIndex + 1, storage, resumesCounter, numMoved);
        }
    }

    @Override
    protected void insertElement(Resume resume, int resumeIndex) {
        int insertElementIndex = resumeIndex - 1;
                System.arraycopy(storage, insertElementIndex, storage, insertElementIndex + 1,
                                resumesCounter - insertElementIndex);
                storage[insertElementIndex] = resume;
    }


}
