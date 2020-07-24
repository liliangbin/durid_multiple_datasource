package com.example.oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.oracle.rdbs.*;
@SpringBootApplication
@RestController
public class OracleApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleApplication.class, args);
    }

    @GetMapping
    public Object name() {
        System.out.println("run ");
        return " test";
    }

    @GetMapping("/test")
    public Object tst() {
        DataProvider provider = new DataProvider();
        return provider.update();
    }
    @GetMapping("/oracle")
    public Object getOracle(){
        OracleDatProvider d = new OracleDatProvider();
        return d.connectDatabase();
    }
}
