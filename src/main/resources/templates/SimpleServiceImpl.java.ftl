package ${package.ServiceImpl};

import com.simple.common.mybatisplus.SimplePage;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${packageQuery}.${table.entityName}PageQuery;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author} ${date}
 */
@Slf4j
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public String insert(${table.entityName} ${table.name}) {
        save(${table.name});
        <#list table.fields as field>
            <#if field.keyFlag>
        return ${table.name}.get${field.name ? cap_first}();
                <#break>
            </#if>
        </#list>
    }

    @Override
    public SimplePage<${table.entityName}> pageList(${table.entityName}PageQuery pageQuery) {
        return page(new SimplePage<>(pageQuery));
    }
}
