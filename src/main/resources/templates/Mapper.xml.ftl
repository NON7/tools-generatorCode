<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${mapperName}">

    <resultMap id="BaseResultMap" type="${packageName}.model.${modelName}">
        <#list columns as column>
            <<#if column.primary??>id<#else>result</#if> column="${column.columnName}" property="${column.propertyName?uncap_first}" jdbcType="<#if column.type='INT'>INTEGER<#elseif column.type='DATETIME'>TIMESTAMP<#elseif column.type='TEXT'>VARCHAR<#else>${column.type}</#if>" />
        </#list>
    </resultMap>

    <sql id="baseColumns">
        <#list columns as column>
            <#if column_index==0>${column.columnName}<#else>,${column.columnName}</#if>
        </#list>
    </sql>

    <select id="getAll${modelName}s" resultMap="BaseResultMap">
        select * from ${tableName};
    </select>


    <select id="find${modelName}ById" parameterType="Integer" resultMap="BaseResultMap">
        select *
        from ${tableName}
        where id=${r'#{id}'}
    </select>

    <insert id="add${modelName}"  parameterType="${packageName}.model.${modelName}" resultMap="BaseResultMap">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list columns as column>
                <#if (column.type?upper_case)=='STRING'>
                    <if test="${column.columnName} != null ">
                        ${column.columnName},
                    </if>
                <#else>
                    <if test="${column.columnName} != null">
                        ${column.columnName},
                    </if>
                </#if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list columns as column>
                <#if (column.type?upper_case)=='STRING'>
                    <if test="${column.columnName} != null ">
                        ${r'#{'}${column.columnName}${r'}'}
                </if>
                <#else>
                    <if test="${column.columnName} != null">
                        ${r'#{'}${column.columnName}${r'}'},
                    </if>
                </#if>
            </#list>
		</trim>
    </insert>

    <delete id="deleteById" parameterType="Integer">
        delete
        from ${tableName}
        where id=${r'#{id}'}
    </delete>

<#--    <update id="updateById" parameterType="${packageName}.model.${modelName}" resultType="Integer" >-->
<#--        update ${tableName}-->
<#--        set sname = #{sname},classId = #{classId},-->
<#--        birthday = #{birthday}, email = #{email}-->
<#--        where id = #{id}-->
<#--    </update>-->

</mapper>
