package ${packageVo};

<#list table.importPackages as pkg>
    <#if pkg?contains("com.baomidou.mybatisplus") || pkg?contains("BaseEntity") >
    <#else >
import ${pkg};
    </#if>
</#list>
import lombok.Data;

/**
*
* ${table.comment!} Vo
*
* @author ${author} ${date}
*/
@Data
public class ${table.entityName}Vo implements Serializable {

    private static final long serialVersionUID = 1L;

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
