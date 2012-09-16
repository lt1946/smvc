package com.iatb.services;

import java.util.List;

import com.iatb.pojo.Person;

public interface PersonService {
	/**
	 *�������е�Person
	 */
	public List<Person> getAll();
	
	/**
	 * ����person
	 */
	public String add(String firstName, String lastName, Double money) ;
	
	/**
	 * ɾ��ָ��Person
	 */
	public void delete(Integer id);
	
	/**
	 * Editָ����Person
	 */
	public void edit(Integer id, String firstName, String lastName, Double money);
}
