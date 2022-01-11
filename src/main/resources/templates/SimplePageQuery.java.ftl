package ${packageQuery};

<#list table.importPackages as pkg>
    <#if pkg?contains("com.baomidou.mybatisplus")>
    <#else >
import ${pkg};
    </#if>
</#list>
import com.simple.common.mybatisplus.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*
* ${table.comment!} 分页查询条件
*
* @author ${author} ${date}
*/
@Data
@EqualsAndHashCode(callSuper = true)
<#if entitySerialVersionUID>
public class ${table.entityName}PageQuery extends BasePage implements Serializable {
<#else>
public class ${table.entityName} extends BasePage {
</#if>

<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    <#if field.comment!?length gt 0>
    /**
    * ${field.comment}
    */
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
