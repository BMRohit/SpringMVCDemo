/**
 * 
 */
package com.example.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Rohit
 *
 */
public class Initializer implements WebApplicationInitializer {

	private static final Logger logger = Logger.getLogger(Initializer.class);
	
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebAppConfig.class);
		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(ctx));
		ctx.setServletContext(container);
		// Register and map the dispatcher servlet
		Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);
		logger.debug("Inside the web application initializer");
		
	}

}
