package ${package.Service};

import com.simple.common.mybatisplus.SimplePage;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * ${table.comment!} 服务接口
 *
 * @author ${author} ${date}
 */
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 【新增】 一条 ${table.entityName} 数据
     * @param ${table.name} ${table.comment} 信息
     * @return 主键Id
     * @author ${author} ${date}
     */
    String insert(${table.entityName} ${table.name});

    /**
     * 【查询】${table.entityName}分页查询列表
     * @param vo 查询条件
     * @return 分页数据
     * @author ${author} ${date}
     */
    SimplePage<${table.entityName}> pageList(${table.entityName}PageVo vo);
}
