package com.example.oracle.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ConfigDto {

    @Valid
    List<InnerDto> innerDtos;

    @NotNull
    public String message;
}
