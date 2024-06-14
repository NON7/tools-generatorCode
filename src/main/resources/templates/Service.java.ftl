package ${packageName}.service;

import ${packageName}.model.${modelName};
import ${packageName}.mapper.${mapperName};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ${serviceName}{

@Autowired
${mapperName} ${mapperName?uncap_first};

//查找全部
public List<${modelName}> getAll${modelName}s(){
return ${mapperName?uncap_first}.getAll${modelName}s();
}

//根据id查询
public List<${modelName}> find${modelName}ById(Integer id){
    return ${mapperName?uncap_first}.find${modelName}ById(id);
}

//增加
public List<${modelName}> add${modelName}(${modelName} ${modelName?lower_case}){
    return ${mapperName?uncap_first}.add${modelName}(${modelName?lower_case});
}

//删除
public Integer delete${modelName}(Integer id){
    return ${mapperName?uncap_first}.delete${modelName}(id);
}

//更新
public Integer update${modelName}(${modelName} ${modelName?lower_case}){
return ${mapperName?uncap_first}.update${modelName}(${modelName?lower_case});
}

}
