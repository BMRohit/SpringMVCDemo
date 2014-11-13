/**
 * 
 */
package com.example.init;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Java based configuration option enables you to write most of your Spring
 * configuration without XML but with the help of few Java-based annotations
 * explained below. 
 * 1. Annotating a class with the @Configuration indicates that
 * the class can be used by the Spring IoC container as a source of bean
 * definitions.
 *  2.Annotating a class with the @EnableTransactionManagement, it
 * enables Spring’s annotation-driven transaction management capability.
 * 3.Annotation @PropertySource(“classpath:application.properties”) – plugs in
 * property file which located in the resource folder.
 * 4.@ComponentScan("Base package name") tells Spring to scan those packages for
 * Annotations.
 * 
 * Configurations like Datasource, SessionFactory, Hibernate properties,
 * view resolver all done in this class.
 * @author Rohit
 */

@Configuration
@ComponentScan("com.example")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class WebAppConfig {
	
	private static final Logger logger = Logger.getLogger(WebAppConfig.class);
	
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_CREATE_TABLES = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String PROPERTY_VALUE_TABLE_CREATION = "hibernate.createtable";
	
	
	@Resource
	private Environment env;
	
	/**
	 * The @Bean annotation tells Spring that a method annotated with 
	 * @Bean will return an object that should be registered as a bean
	 * in the Spring application context. 
	 */
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		logger.debug("Data source bean created "+dataSource);
		logger.info("User of the Database "+(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME)));
		logger.debug("Database URL "+(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL)));
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		logger.debug("Session factory bean created "+sessionFactoryBean);
		return sessionFactoryBean;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_CREATE_TABLES, env.getRequiredProperty(PROPERTY_VALUE_TABLE_CREATION));
		logger.debug("Hibernate properties intialized ");
		return properties;	
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		logger.debug("Hibernate Transcation Manager bean created "+transactionManager);
		return transactionManager;
	}
	
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		logger.debug("View Resolver bean created "+resolver);
		return resolver;
	}

}
