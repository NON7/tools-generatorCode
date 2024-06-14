package ${packageName}.controller;

import ${packageName}.model.${modelName};
import ${packageName}.service.${serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("${modelName?lower_case}")
public class ${controllerName}{

@Autowired
${serviceName} ${serviceName?uncap_first};

//查找全部
@GetMapping("/getAll")
public List<${modelName}> getAll${modelName}s(){
    return ${serviceName?uncap_first}.getAll${modelName}s();
}

//根据id查找
@GetMapping("/findById/${r'${id}'}")
public List<${modelName}> find${modelName}ById(@PathVariable Integer id){
    return ${serviceName?uncap_first}.find${modelName}ById(id);
}

//添加
@PostMapping("/add")
public List<${modelName}> add${modelName}(@RequestBody ${modelName} ${modelName?lower_case}){
    return ${serviceName?uncap_first}.add${modelName}(${modelName?lower_case});
}

//删除
@DeleteMapping("/delete/${r'${id}'}")
public List<${modelName}> delete${modelName}(@PathVariable Integer id){
    Integer i=${serviceName?uncap_first}.delete${modelName}(id);
    if(i>0){
        return RespMsg.OK("删除成功");
    }else{
        return RespMsg.error("删除失败");
    }
}

//修改
@PutMapping("/update")
public List<${modelName}> update${modelName}(@RequestBody ${modelName} ${modelName?lower_case}){
    Integer i= ${serviceName?uncap_first}.update${modelName}(${modelName?lower_case});
    if(i>0){
        return RespMsg.OK("修改成功");
    }else{
        return RespMsg.error("修改失败");
    }
}

}
