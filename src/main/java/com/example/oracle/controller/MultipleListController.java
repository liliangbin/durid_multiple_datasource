package com.example.oracle.controller;

import com.example.oracle.dto.ConfigDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/name")
@Slf4j
public class MultipleListController {
    @PostMapping
    public Object getList(@Valid @RequestBody ConfigDto configDto) {
        log.info(configDto.message);
        System.out.println(CollectionUtils.isEmpty(configDto.getInnerDtos()));
        return "hello world";
    }
}
