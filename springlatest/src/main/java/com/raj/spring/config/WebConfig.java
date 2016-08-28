package com.raj.spring.config;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.raj.spring.repository.DepartmentRepository;

/**
 * @author Rajasekar
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.raj.spring.*" })
@EnableJpaRepositories(basePackageClasses = DepartmentRepository.class)
@EnableTransactionManagement
@PropertySource("classpath:/springlatest.properties")
public class WebConfig {

	Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Autowired
	private Environment environment;

	@Bean
	public DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(environment.getProperty("database.url"));
		ds.setDriverClassName(environment
				.getProperty("database.driverClassName"));
		logger.debug(environment.getProperty("database.driverClassName"));
		ds.setUsername(environment.getProperty("database.username"));
		logger.debug("userName: ", environment.getProperty("database.username"));
		ds.setPassword(environment.getProperty("database.password"));
		logger.debug("password: ", environment.getProperty("database.password"));
		ds.setValidationQueryTimeout(5);
		//ds.setValidationQuery("select 1 from DUAL");
		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan("com.raj.spring.entity",
				"com.raj.spring.repository");
		// let Hibernate know which database we're using.
		// note that this is vendor specific, not JPA

		Map<String, Object> opts = emf.getJpaPropertyMap();
		opts.put("hibernate.dialect",
				environment.getProperty("hibernate.dialect"));
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(va);
		return emf;
	}

/*	@Bean
	public DatabasePopulator databasePopulator(DataSource dataSource) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(true);
		populator.setIgnoreFailedDrops(true);
		populator.addScript(new ClassPathResource(
				"/META-INF/spring/mydata-dml.sql"));
		try {
			populator.populate(dataSource.getConnection());
		} catch (SQLException ignored) {
			logger.error("Error:", ignored);
		}
		return populator;
	}*/

	@Bean
	public org.springframework.orm.jpa.JpaTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setDataSource(getDataSource());
		jpaTransactionManager
				.setJpaDialect(new org.springframework.orm.jpa.vendor.HibernateJpaDialect());
		return jpaTransactionManager;
	}
}
