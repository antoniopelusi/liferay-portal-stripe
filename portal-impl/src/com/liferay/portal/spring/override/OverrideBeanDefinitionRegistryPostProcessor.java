/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.spring.override;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author Tina Tian
 */
public class OverrideBeanDefinitionRegistryPostProcessor
	implements BeanDefinitionRegistryPostProcessor {

	public OverrideBeanDefinitionRegistryPostProcessor(Properties properties) {
		_properties = properties;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry beanDefinitionRegistry)
		throws BeansException {

		for (String key : _properties.stringPropertyNames()) {
			BeanDefinition beanDefinition =
				beanDefinitionRegistry.getBeanDefinition(key);

			if (beanDefinition != null) {
				beanDefinition.setBeanClassName(_properties.getProperty(key));
			}
		}
	}

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory configurableListableBeanFactory)
		throws BeansException {
	}

	private final Properties _properties;

}