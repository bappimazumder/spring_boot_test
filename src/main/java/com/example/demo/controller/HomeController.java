/**
 * 
 */
package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author bappi
 *
 */
@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class); 
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String showHome() {
		LOGGER.debug("Render in Home Page");		
		return "home";
	}
	
	
}
