package com.example.oracle.lamada;

import com.alibaba.druid.support.json.JSONUtils;
import org.w3c.dom.ls.LSOutput;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatasourceDetailsTest {
    public static void main(String[] args) {
        List<DatasourceDetails> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lists.add(new DatasourceDetails(i, i + "fdsf"));
            if (i == 4 || i == 5) {
                lists.get(i).setFolderId("sdfsfd");
            }
        }

        List<DatasourceDetails> lis = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lis.add(new DatasourceDetails(i, i + "fdsf"));
            if (i == 2 || i == 6) {
                lis.get(i).setFolderId("sdfsfd");
            }
        }

        lists.forEach(datasourceDetails -> {

            List<DatasourceDetails> infs = lis.stream()
                    .filter(e -> e.getFolderId() == datasourceDetails.getFolderId() && e.getFolderId() != null)
                    .collect(Collectors.toList());
            datasourceDetails.setItems(infs);

        });


        System.out.println("done");
        System.out.println("fdsf");
    }


}
