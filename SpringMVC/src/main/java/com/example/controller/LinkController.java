package com.example.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkController {
	
	private static final Logger logger = Logger.getLogger(LinkController.class);
	
	
	@RequestMapping(value="/")
	public ModelAndView mainPage() {
		logger.debug("Requested for home page..");
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/index")
	public ModelAndView indexPage() {
		logger.debug("Requested for home page..");
		return new ModelAndView("home");
	}
}
