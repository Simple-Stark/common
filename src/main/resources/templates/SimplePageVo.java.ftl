package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import com.simple.common.mybatisplus.BasePage;
import lombok.Data;

/**
*
* ${table.comment!} 分页Vo
*
* @author ${author} ${date}
*/
@Dat
<#if entitySerialVersionUID>
public class ${table.entityName}PageVo extends BasePage implements Serializable {
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
