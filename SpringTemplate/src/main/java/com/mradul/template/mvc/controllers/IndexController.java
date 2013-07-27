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
package com.mradul.template.mvc.controllers;

import org.slf4j.Logger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mradul.template.log.InjectLogger;

@Controller
@RequestMapping("/index")
@DependsOn("loggerInjector")
public class IndexController {

	@InjectLogger
	Logger logger;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		logger.info("Spring 3 MVC Hello World");
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "index";
	}
}
