package com.codingtool.codegenerator.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 数据库字段与bean字段对应
 */
@Data
@ToString
public class ColumnClass {
    //java属性中的名字
    private String propertyName;
    //数据库中的名字
    private String columnName;
    //字段类型
    private String type;
    //备注
    private String remark;
    //是否为主键
    private Boolean isPrimary;

}
