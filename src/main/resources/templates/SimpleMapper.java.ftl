package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if mapperAnnotation>
    import org.apache.ibatis.annotations.Mapper;
</#if>
import com.simple.common.mybatisplus.SimplePage;
import ${packageQuery}.${table.entityName}PageQuery;
import ${packageResult}.${table.entityName}PageResult;
import org.apache.ibatis.annotations.Param;

/**
 * ${table.comment!} Mapper 接口
 * @author ${author} ${date}
 */
<#if mapperAnnotation>
    @Mapper
</#if>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    /**
     * 【查询】${table.entityName}分页查询列表
     * @param page 分页封装
     * @param query 查询条件
     * @return 分页数据
     * @author ${author} ${date}
     */
    SimplePage<${table.entityName}PageResult> pageList(SimplePage<${table.entityName}PageResult> page, @Param("query") ${table.entityName}PageQuery query);

}
