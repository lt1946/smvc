package com.iatb.services.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iatb.dao.PersonDao;
import com.iatb.pojo.Person;
import com.iatb.services.PersonService;


/**
 * Service for processing Persons. 
 * <p>
 * ����Spring JDBC �� JdbcTemplate
 * see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html
 * <p>
 * ����transactions, see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html
 */

@Service("personService")
@RequestMapping("/person.do")
public class PersonServiceImpl implements PersonService {
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private PersonDao personDao;
	
	/**
	 *�������е�Person
	 */
	
	public List<Person> getAll() {
		logger.debug("Retrieving all persons");
		System.out.println(personDao+"==========");
		return personDao.getAllPerson();
	}
	
	/**
	 * ����person
	 */
	public String add(String firstName, String lastName, Double money) {
		logger.debug("Adding new person");
		int rows=personDao.addPerson(firstName, lastName, money);
		if(rows==1){
			return "��ӳɹ���";
		}else{
			return "���ʧ�ܣ�";
		}
	}
	
	/**
	 * ɾ��ָ��Person
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing person");
		
		personDao.deletePerson(id);
	}
	
	/**
	 * Editָ����Person
	 */
	public void edit(Integer id, String firstName, String lastName, Double money) {
		logger.debug("Editing existing person");
		
		personDao.editPerson(id, firstName, lastName, money);
		
	}

}
