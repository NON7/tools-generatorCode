package com.codingtool.codegenerator.entity.java.com.codingtool.codegenerator.utils;

import com.codingtool.codegenerator.entity.java.com.codingtool.codegenerator.entity.Db;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 链接数据库
 */
@Slf4j
public class DbUtils {
    private static Connection connection;
    public static Connection getConnection(){
        return connection;
    }

    public static Connection initDb(Db db){
        if(connection==null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection= DriverManager.getConnection(db.getUrl(),db.getUsername(),db.getPassword());
            } catch (ClassNotFoundException |SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
