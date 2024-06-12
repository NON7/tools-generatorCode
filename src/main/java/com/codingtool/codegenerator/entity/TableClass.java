package com.codingtool.codegenerator.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class TableClass {
    private String tableName;
    private String modelName;
    private String serviceName;
    private String controllerName;
    private String mapperName;
    private String packageName;
    private List<ColumnClass> columns;

}
