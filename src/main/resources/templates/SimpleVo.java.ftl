package ${packageVo};

<#list table.importPackages as pkg>
    <#if pkg?contains("com.baomidou.mybatisplus") || pkg?contains("BaseEntity") >
    <#else >
import ${pkg};
    </#if>
</#list>
import lombok.Data;

import javax.validation.constraints.*;

/**
 * ${table.comment!} Vo
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
    <#if field.keyFlag>
    <#elseif field.propertyType?contains("String")>
    @NotBlank(message = "${field.propertyName}不允许为空！")
    <#elseif field.propertyType?contains("Integer")>
    @Min(value = 0,message = "${field.propertyName}最小不能小于0！")
    <#elseif field.propertyType?contains("Date")>
    @NotNull(message = "${field.propertyName}不允许为空！")
    <#else >
    @NotNull(message = "${field.propertyName}不允许为空！")
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
