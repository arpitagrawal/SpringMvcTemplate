/*******************************************************************************
 * Copyright 2013 Mradul Pandey
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.mradul.template.log.postprocessors;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.mradul.template.log.InjectLogger;

public class LoggerInjector implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public Object postProcessBeforeInitialization(final Object bean,
			String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				if (field.getAnnotation(InjectLogger.class) != null) {
					Logger logger = LoggerFactory.getLogger(bean.getClass());
					field.set(bean, logger);
				}
			}
		});
		return bean;
	}
}
