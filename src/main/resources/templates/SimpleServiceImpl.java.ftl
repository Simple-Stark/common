package ${package.ServiceImpl};

import com.simple.common.mybatisplus.SimplePage;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${packageQuery}.${table.entityName}PageQuery;
import ${packageResult}.${table.entityName}PageResult;
import ${packageVo}.${table.entityName}Vo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author} ${date}
 */
@Slf4j
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public String save${table.entityName}(${table.entityName}Vo vo) {
        ${table.entityName} ${table.name} = new ${table.entityName}();
        BeanUtils.copyProperties(vo,${table.name});
        saveOrUpdate(${table.name});
        <#list table.fields as field>
            <#if field.keyFlag>
        return ${table.name}.get${field.capitalName}();
                <#break>
            </#if>
        </#list>
    }

    @Override
    public ${table.entityName}Vo get${table.entityName}ById(String id) {
        ${table.entityName} ${table.name} = getById(id);
        Assert.notNull(${table.name},"该对象不存在，请核对后再试！");
        ${table.entityName}Vo vo = new ${table.entityName}Vo();
        BeanUtils.copyProperties(${table.name},vo);
        return vo;
    }

    @Override
    public SimplePage<${table.entityName}PageResult> pageList(${table.entityName}PageQuery pageQuery) {
        return baseMapper.pageList(new SimplePage<>(pageQuery), pageQuery);
    }
}