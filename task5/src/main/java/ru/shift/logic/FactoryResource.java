package ru.shift.logic;

import ru.shift.model.Resource;

public class FactoryResource {
    private static long idResource = 0;

    public synchronized static Resource createResource() {
        return new Resource(idResource++);
    }
}