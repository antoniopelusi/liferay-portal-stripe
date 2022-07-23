package ${packagePath}.service.impl;

import ${apiPackagePath}.service.${entity.name}${sessionTypeName}Service;
import ${packagePath}.service.base.${entity.name}${sessionTypeName}ServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

<#if stringUtil.equals(sessionTypeName, "Local")>
/**
 * @author ${author}
 */
<#else>
/**
 * @author ${author}
 */
</#if>

<#if dependencyInjectorDS>
	@Component(
		<#if stringUtil.equals(sessionTypeName, "Local")>
			property = "model.class.name=${apiPackagePath}.model.${entity.name}"
		<#else>
			property = {
				"json.web.service.context.name=${portletShortName?lower_case}",
				"json.web.service.context.path=${entity.name}"
			}
		</#if>,
		service = AopService.class
	)
</#if>
public class ${entity.name}${sessionTypeName}ServiceImpl extends ${entity.name}${sessionTypeName}ServiceBaseImpl {
}