package com.example.oracle.datasource.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatasourceContextHolder.getDatasource();
    }
}
