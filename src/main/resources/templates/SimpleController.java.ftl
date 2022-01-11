package ${package.Controller};

import com.simple.common.mybatisplus.SimplePage;
import com.simple.common.result.Result;
import org.springframework.web.bind.annotation.*;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import ${packageQuery}.${table.entityName}PageQuery;

/**
 * ${table.comment!} 控制层
 *
 * @author ${author} ${date}
 */
@Validated
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final ${table.serviceName} ${table.name}Service;

    public ${table.controllerName}(${table.serviceName} ${table.name}Service) {
        this.${table.name}Service = ${table.name}Service;
    }

    /**
    * 【查询】分页查询
    * @param pageQuery 查询条件
    * @return ${table.comment}分页数据
    * @author ${author} ${date}
    */
    @GetMapping("/list")
    public Result<SimplePage<${table.entityName}>> list(${table.entityName}PageQuery pageQuery) {
        SimplePage<${table.entityName}> pageList = ${table.name}Service.pageList(pageQuery);
        return Result.success(pageList);
    }

    /**
    * 【新增】添加${table.comment}信息
    * @param ${table.name} 用户实体
    * @return 新增${table.comment}的主键
    * @author ${author} ${date}
    */
    @PostMapping("/insert")
    public Result<String> insert(@Validated @RequestBody ${table.entityName} ${table.name}) {
        return Result.success(${table.name}Service.insert(${table.name}));
    }

    /**
    * 【查询】根据主键Id查询${table.comment}信息
    * @param id 主键Id
    * @return 主键Id对应的${table.comment}信息
    * @author ${author} ${date}
    */
    @GetMapping("/getById")
    public Result<${table.entityName}> getById(@NotBlank(message = "主键id不允许为空") @RequestParam String id) {
        ${table.entityName} ${table.name} = ${table.name}Service.getById(id);
        return Result.success(${table.name});
    }

    /**
    * 【删除】指定主键Id的${table.comment}信息
    * @param id 主键Id
    * @return 成功返回 true，失败返回false
    * @author ${author} ${date}
    */
    @DeleteMapping("/deleteById")
    public Result<Boolean> deleteById(@NotBlank(message = "主键id不允许为空") @RequestParam String id) {
        return Result.success(${table.name}Service.removeById(id));
    }
}
</#if>
