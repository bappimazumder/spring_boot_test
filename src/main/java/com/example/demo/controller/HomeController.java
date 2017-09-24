/**
 * 
 */
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author bappi
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String showHome() {
		
		return "home";
	}
}
