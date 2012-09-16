package com.iatb.services;

import java.util.List;

import com.iatb.pojo.Person;

public interface PersonService {
	/**
	 *检索所有的Person
	 */
	public List<Person> getAll();
	
	/**
	 * 新增person
	 */
	public String add(String firstName, String lastName, Double money) ;
	
	/**
	 * 删除指定Person
	 */
	public void delete(Integer id);
	
	/**
	 * Edit指定的Person
	 */
	public void edit(Integer id, String firstName, String lastName, Double money);
}
