package com.raj.spring.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * @author Rajasekar
 * 
 */
@Configuration
@Profile("HSQL_EMBEDDED")
@PropertySource("classpath:/META-INF/spring/hsql.properties")
public class JPAHSQLEmbeddedConfig extends JPACommonConfig {

	Logger logger = LoggerFactory.getLogger(JPAHSQLEmbeddedConfig.class);

	@Autowired
	Environment environment;

	JPAHSQLEmbeddedConfig() {
		this.setShowSql(true);
	}

	@Override
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		String url = environment.getProperty("database.url");
		String driverClassName = environment
				.getProperty("database.driverClassName");
		String userName = environment.getProperty("database.username");
		ds.setUrl(url);
		ds.setDriverClassName(driverClassName);
		ds.setUsername(userName);
		logger.debug("Connecting to database with properties");
		logger.debug("userName: ", userName);
		ds.setPassword(environment.getProperty("database.password"));
		ds.setValidationQueryTimeout(5);
		logger.debug("URL[{}] Driver Class[{}] userName[{}]", url,
				driverClassName, userName);
		return ds;
	}

	@Bean
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
	}
}
