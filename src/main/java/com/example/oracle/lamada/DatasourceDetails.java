package com.example.oracle.lamada;

import lombok.Data;

import java.util.List;

@Data
public class DatasourceDetails {
    private int id;
    private String folderId;
    private String test;

    public DatasourceDetails(int id,String test) {
        this.id = id;
        this.test = test;
    }
    public List<DatasourceDetails> Items;
}
