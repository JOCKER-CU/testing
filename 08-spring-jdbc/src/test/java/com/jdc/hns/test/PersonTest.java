package com.jdc.hns.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.hns.dao.PersonDao;

@SpringJUnitConfig(locations = "/application.xml")
//@SpringJUnitConfig(classes = ApplicationConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class PersonTest {

	@Autowired
	private PersonDao dao;
	@Autowired
	private JdbcOperations jdbc;

	@ParameterizedTest
	@Sql(scripts = "classpath:/personTable.sql")
	@CsvSource("Sandar,30")
	@Order(1)
	void test1(String name, int age) {
		var res = dao.insertPerson(name, age);
		assertEquals(1, res);
	}

	@ParameterizedTest
	@DisplayName("1.Using execute with connectioncallback")
	@Sql(scripts = "classpath:/personTable.sql")
	@CsvSource("Thida,12")
	@Order(2)
	void test2(String name, int age) {
		var row = jdbc.execute((Connection con) -> {
			var stmt = con.createStatement();
			return stmt.executeUpdate("insert into person(name,age) values('%s',%d)".formatted(name, age));

		});

		assertEquals(1, row);
	}

	@ParameterizedTest
	@DisplayName("2.Using execute with statmentCallBack")
	@Sql(scripts = "classpath:/personTable.sql")
	@CsvSource("Wanna,30")
	@Order(3)
	void test3(String name, int age) {
		var row = jdbc.execute((Statement stmt) -> {
			return stmt.executeUpdate("insert into person(name,age) values('%s',%d)".formatted(name, age));
		});

		assertEquals(1, row);
	}

	@ParameterizedTest
	@DisplayName("3.Using execute with preparedStatementCallBack")
	@Sql(scripts = "classpath:/personTable.sql")
	@CsvSource("Wanna,30")
	@Order(3)
	void test4(String name, int age) {
		var row = jdbc.execute("insert into person(name,age) values(?,?)", (PreparedStatement stmt) -> {
			stmt.setString(1, name);
			stmt.setInt(2, age);
			return stmt.executeUpdate();
		});

		assertEquals(1, row);
	}

}