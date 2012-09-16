package com.iatb.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.iatb.pojo.Person;

@SuppressWarnings("unchecked")
public class PersonRowMap implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Person person=new Person();
		
		person.setId(rs.getInt("id"));
		person.setFirstName(rs.getString("first_name"));
		person.setLastName(rs.getString("last_name"));
		person.setMoney(rs.getDouble("money"));
		return person;
	}

}
