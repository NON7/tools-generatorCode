package com.codingtool.codegenerator.entity.java.com.codingtool.codegenerator.service;

import com.codingtool.codegenerator.entity.java.com.codingtool.codegenerator.entity.ColumnClass;
import com.codingtool.codegenerator.entity.java.com.codingtool.codegenerator.entity.RespMsg;
import com.codingtool.codegenerator.entity.java.com.codingtool.codegenerator.entity.TableClass;
import com.codingtool.codegenerator.entity.java.com.codingtool.codegenerator.utils.DbUtils;
import com.google.common.base.CaseFormat;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneratorCodeService {
    Configuration cfg=null;

    {
        cfg=new Configuration(Configuration.VERSION_2_3_30);
        cfg.setTemplateLoader(new ClassTemplateLoader(GeneratorCodeService.class,"/templates"));
        cfg.setDefaultEncoding("UTF-8");
    }

    public RespMsg generateCode(List<TableClass> tableClassList, String realPath){
        try {
            Template controllerTemplate = cfg.getTemplate("Controller.java.ftl");
            Template mapperJavaTemplate = cfg.getTemplate("Mapper.java.ftl");
            Template mapperXmlTemplate = cfg.getTemplate("Mapper.xml.ftl");
            Template modelTemplate = cfg.getTemplate("Model.java.ftl");
            Template serviceTemplate = cfg.getTemplate("Service.java.ftl");
            Connection connection = DbUtils.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            for (TableClass tableClass : tableClassList) {
                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableClass.getTableName(), null);
                ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableClass.getTableName());
                List<ColumnClass> columnClassList=new ArrayList<>();
                while (columns.next()) {
                    String column_name = columns.getString("COLUMN_NAME");
                    String type_name = columns.getString("TYPE_NAME");
                    String remarks = columns.getString("REMARKS");
                    ColumnClass columnClass = new ColumnClass();
                    columnClass.setColumnName(column_name);
                    columnClass.setType(type_name);
                    columnClass.setRemark(remarks);
                    columnClass.setPropertyName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,column_name));
                    primaryKeys.first();
                    while (primaryKeys.next()) {
                        String pkName = primaryKeys.getString("COLUMN_NAME");
                        if (column_name.equals(pkName)) {
                            columnClass.setIsPrimary(true);
                        }
                    }
                    columnClassList.add(columnClass);
                }
                tableClass.setColumns(columnClassList);
                String path=realPath+"/"+tableClass.getPackageName().replace(".","/");
                generate(modelTemplate,tableClass,path+"/model/");
                generate(controllerTemplate,tableClass,path+"/controller/");
                generate(mapperJavaTemplate,tableClass,path+"/mapper/");
                generate(serviceTemplate,tableClass,path+"/service/");
                generate(mapperXmlTemplate,tableClass,path+"/mapper/");
            }
            return RespMsg.OK("代码已生成："+realPath);
        } catch (IOException | SQLException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    private void generate(Template template, TableClass tableClass, String path) throws IOException, TemplateException {
        File folder=new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName=path+"/"+tableClass.getModelName()+template.getName().replace(".ftl","").replace("Model","");
        FileOutputStream fileOutputStream=new FileOutputStream(fileName);
        OutputStreamWriter out=new OutputStreamWriter(fileOutputStream);
        template.process(tableClass,out);
        out.close();
        fileOutputStream.close();



    }

}
