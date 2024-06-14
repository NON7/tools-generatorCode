package ${packageName}.mapper;

import ${packageName}.model.${modelName};
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ${mapperName}{

    //查找全部
    List<${modelName}> getAll${modelName}s();

    //根据id查询
    public List<${modelName}> find${modelName}ById(Integer id);

    //增加
    public List<${modelName}> add${modelName}(${modelName} ${modelName?lower_case});

    //删除
    public Integer delete${modelName}(@Param("id") Integer id);

    //更新
    public Integer update${modelName}(${modelName} ${modelName?lower_case});
}
