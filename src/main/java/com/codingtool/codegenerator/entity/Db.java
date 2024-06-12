package com.codingtool.codegenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据库链接信息
 */
@Data
@AllArgsConstructor
public class Db {
    private String username;
    private String password;
    private String url;

}
