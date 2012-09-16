package com.iatb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Person;

@Repository("personDao")
public class PersonDao {
	private SimpleJdbcTemplate jdbcTemplate;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	public List<Person> getAllPerson() {
		String sql = "select id, first_name, last_name, money from person";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(Person.class)/* new PersonRowMap()*/);
	}
	/**
	 * 新增人员
	 * @param firstName
	 * @param lastName
	 * @param money
	 * @return
	 */
	public int addPerson(String firstName, String lastName, Double money){
	   String sql = "insert into person(first_name, last_name, money) values " +
				"(:firstName, :lastName, :money)";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstName", firstName);
		parameters.put("lastName", lastName);
		parameters.put("money", money);
		return jdbcTemplate.update(sql, parameters);
	}
	/**
	 * 删除指定Person
	 */
	public int deletePerson(Integer id) {
		String sql = "delete from person where id = ?";
		Object[] parameters = new Object[] {id};
		return jdbcTemplate.update(sql, parameters);
	}
	/**
	 * Edit指定的Person
	 */
	public int editPerson(Integer id, String firstName, String lastName, Double money) {
		
		String sql = "update person set first_name = :firstName, " +
				"last_name = :lastName, money = :money where id = :id";
		// Assign values to parameters
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		parameters.put("firstName", firstName);
		parameters.put("lastName", lastName);
		parameters.put("money", money);
		
		return jdbcTemplate.update(sql, parameters);
		
	}
}
