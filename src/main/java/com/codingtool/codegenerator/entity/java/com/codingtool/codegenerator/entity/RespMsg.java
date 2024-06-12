package com.codingtool.codegenerator.entity.java.com.codingtool.codegenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespMsg {
    private Integer status;
    private String msg;
    private Object obj;

    public static RespMsg OK(String msg,Object obj){
        return new RespMsg(200,msg,obj);
    }


    public static RespMsg OK(String msg){
        return new RespMsg(200,msg,null);
    }

    public static RespMsg error(String msg,Object obj){
        return new RespMsg(500,msg,obj);
    }

    public static RespMsg error(String msg){
        return new RespMsg(500,msg,null);
    }






}
