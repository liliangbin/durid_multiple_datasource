package com.example.oracle.rdbs.folder;

import org.apache.catalina.LifecycleState;

import java.util.List;

 class FileAndFolder {

    long id;
    long parent;
    String name;
}

public class DoneFolder {
    long id;
    List<DoneFolder> items;
    String name;
    long parent;

    public DoneFolder(long id, String name, long parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }
}