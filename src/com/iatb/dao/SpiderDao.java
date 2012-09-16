package com.iatb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.iatb.pojo.Groupurlrole;
import com.iatb.pojo.Person;

@Repository("spiderDao")
public class SpiderDao {
	private SimpleJdbcTemplate jdbcTemplate;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	/**
	 * 获取分页规则
	 */
	@SuppressWarnings("unchecked")
	public void getAllGroupurlRole(){
		String sql="select * from groupurlrole";
//		return jdbcTemplate.query(sql, new RowMapperResultSetExtractor(), args)
	}
	public List<Person> getAllPerson() {
		String sql = "select id, first_name, last_name, money from person";
		// Maps a SQL result to a Java object
		RowMapper<Person> mapper = new RowMapper<Person>() {  
	        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Person person = new Person();
	        	person.setId(rs.getInt("id"));
	        	person.setFirstName(rs.getString("first_name"));
	        	person.setLastName(rs.getString("last_name"));
	        	person.setMoney(rs.getDouble("money"));
	            return person;
	        }
	    };
//	    jdbcTemplate.q
		return jdbcTemplate.query(sql, mapper);
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
