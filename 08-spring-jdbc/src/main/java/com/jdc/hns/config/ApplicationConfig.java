package com.jdc.hns.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.jdc.hns.dto.Person;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

@Configuration
@ComponentScan(basePackages = { "com.jdc.mkt.dao", "com.jdc.mkt.factory" })
@PropertySource({ "/connection.properties", "/dmlPerson.properties" })
public class ApplicationConfig {

	@Value("${db.url}")
	private String url;
	@Value("${db.user}")
	private String user;
	@Value("${db.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		var con = new MysqlConnectionPoolDataSource();
		con.setUrl(url);
		con.setUser(user);
		con.setPassword(password);
		return con;
	}

	@Bean
	public JdbcTemplate template(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public RowMapper<Person> rowMapper() {
		return new BeanPropertyRowMapper<Person>(Person.class);
	}

}