package com.raj.spring.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * @author Rajasekar
 * 
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.raj.spring.repository" })
public abstract class JPACommonConfig {
	@Autowired
	Environment environment;

	public static final String UNDEFINED = "**UNDEFINED**";

	@Bean
	public abstract DataSource dataSource();

	private boolean showSql = false;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("com.raj.spring.entity",
				"com.raj.spring.repository");
		/*
		 * if (getJpaProperties() != null) {
		 * emf.setJpaProperties(getJpaProperties()); }
		 */
		Map<String, Object> opts = emf.getJpaPropertyMap();
		opts.put("hibernate.dialect",
				environment.getProperty("database.dialect"));
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		va.setShowSql(isShowSql());
		emf.setJpaVendorAdapter(va);
		return emf;
	}

	@Bean
	public EntityManager entityManger() {
		return entityManagerFactory().getObject().createEntityManager();
	}

	@Bean
	public org.springframework.orm.jpa.JpaTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setDataSource(dataSource());
		jpaTransactionManager
				.setJpaDialect(new org.springframework.orm.jpa.vendor.HibernateJpaDialect());
		return jpaTransactionManager;
	}

	public void setShowSql(boolean showSql) {
		this.showSql = showSql;
	}

	public boolean isShowSql() {
		return showSql;
	}
}
