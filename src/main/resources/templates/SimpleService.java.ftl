package ${package.Service};

import com.simple.common.mybatisplus.SimplePage;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import ${packageQuery}.${table.entityName}PageQuery;
import ${packageResult}.${table.entityName}PageResult;
import ${packageVo}.${table.entityName}Vo;

/**
 * ${table.comment!} 服务接口
 *
 * @author ${author} ${date}
 */
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * 【保存】新增或修改${table.comment}信息
    * @param vo ${table.comment}信息
    * @return 保存成功后${table.comment}的主键
    * @author ${author} ${date}
    */
    String save${table.entityName}(${table.entityName}Vo vo);

    /**
    * 【查询】根据主键查询${table.comment}信息
    * @param id 主键Id
    * @return ${table.entityName}Vo 对象
    * @author ${author} ${date}
    */
    ${table.entityName}Vo get${table.entityName}ById(String id);

    /**
     * 【查询】${table.comment}分页查询列表
     * @param pageQuery 查询条件
     * @return ${table.comment}分页数据
     * @author ${author} ${date}
     */
    SimplePage<${table.entityName}PageResult> pageList(${table.entityName}PageQuery pageQuery);
}
