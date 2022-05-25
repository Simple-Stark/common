package ${package.Controller};

import com.simple.common.mybatisplus.SimplePage;
import com.simple.common.result.Result;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${packageResult}.${table.entityName}PageResult;
import ${packageQuery}.${table.entityName}PageQuery;
import ${packageVo}.${table.entityName}Vo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

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
    public Result<SimplePage<${table.entityName}PageResult>> list(${table.entityName}PageQuery pageQuery) {
        SimplePage<${table.entityName}PageResult> pageList = ${table.name}Service.pageList(pageQuery);
        return Result.success(pageList);
    }

    /**
    * 【保存】新增或修改${table.comment}信息
    * @param vo ${table.comment}信息
    * @return 保存成功后${table.comment}的主键
    * @author ${author} ${date}
    */
    @PostMapping("/save")
    public Result<String> save(@Validated @RequestBody ${table.entityName}Vo vo) {
        return Result.success(${table.name}Service.save${table.entityName}(vo));
    }

    /**
    * 【查询】根据主键Id查询${table.comment}信息
    * @param id 主键Id
    * @return 主键Id对应的${table.comment}信息
    * @author ${author} ${date}
    */
    @GetMapping("/getById")
    public Result<${table.entityName}Vo> getById(@NotBlank(message = "主键id不允许为空") @RequestParam String id) {
        ${table.entityName}Vo vo = ${table.name}Service.get${table.entityName}ById(id);
        return Result.success(vo);
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
