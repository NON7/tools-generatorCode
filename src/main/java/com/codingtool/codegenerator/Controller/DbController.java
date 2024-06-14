package com.codingtool.codegenerator.Controller;

import com.codingtool.codegenerator.entity.Db;
import com.codingtool.codegenerator.entity.GeneratorReq;
import com.codingtool.codegenerator.entity.RespMsg;
import com.codingtool.codegenerator.entity.TableClass;
import com.codingtool.codegenerator.service.GeneratorCodeService;
import com.codingtool.codegenerator.utils.DbUtils;
import com.google.common.base.CaseFormat;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
public class DbController {

    @Autowired
    GeneratorCodeService generatorCodeService;

    /**
     * 数据库连接
     * @param db
     * @return
     */
    @PostMapping("/connect")
    public RespMsg connect(@RequestBody Db db){
        log.info("url:"+db.getUrl());
        log.info("username:"+db.getUsername());
        log.info("password:"+db.getPassword());
        Connection connection = DbUtils.initDb(db);
        if (connection!=null) {
            return  RespMsg.OK("数据库链接成功");
        }else{
            return RespMsg.error("数据库链接失败");
        }

    }

    /**
     * 设置需要生成文件的名称
     * @param map
     * @return
     */
    @PostMapping("/config")
    public RespMsg Config(@RequestBody Map<String,String> map){
        try {
            String packageName=map.get("packageName");
            Connection connection = DbUtils.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, null);
            ArrayList<TableClass> tableClassesList = new ArrayList<>();
            while(tables.next()){
                TableClass tableClass = new TableClass();
                tableClass.setPackageName(packageName);
                String table_name=tables.getString("TABLE_NAME");
                String module_name= CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,table_name);
                tableClass.setTableName(table_name);
                tableClass.setModelName(module_name);
                tableClass.setControllerName(module_name+"Controller");
                tableClass.setServiceName(module_name+"Service");
                tableClass.setMapperName(module_name+"Mapper");
                tableClassesList.add(tableClass);
            }
            return RespMsg.OK("数据库信息读取成功",tableClassesList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping ("/generatorCode")
    public RespMsg generatorCode(@RequestBody List<TableClass> tableClassList){
        return generatorCodeService.generateCode(tableClassList,"");
    }


}
