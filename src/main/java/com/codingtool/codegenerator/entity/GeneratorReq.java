package com.codingtool.codegenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class GeneratorReq {
    private List<TableClass> tableClassList;
}
