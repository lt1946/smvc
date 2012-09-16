package com.iatb.controller;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iatb.pojo.Person;
import com.iatb.services.PersonService;

@Controller
@RequestMapping("/main")
public class MainController {
	
protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="personService")
	private PersonService personService;
	
	/**
	 *������е�Person�����ص�ָ��JSPҳ��
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/persons.do", method = RequestMethod.GET)
    public String getPersons(Model model) {
    	logger.debug("Received request to show all persons");
    	List<Person> persons = personService.getAll();
    	model.addAttribute("persons", persons);
    	return "user/personspage";
	}
 
    /**
     *����ҳ�洫�ݹ�����ֵ����һPerson����ת��ָ��ҳ��.
     */
    @RequestMapping(value = "/persons/add.do", method = RequestMethod.GET)
    public String add(
    		@RequestParam(value="firstname", required=true) String firstName,
    		@RequestParam(value="lastname", required=true) String lastName,
    		@RequestParam(value="money", required=true) Double money) {
   
		logger.debug("Received request to add new person");
		
		personService.add(firstName, lastName, money);

		return "user/addedpage";
	}
    
    /**
     * ���ݽ��յ�IDɾ��Person
     */
    @RequestMapping(value = "/persons/delete.do", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Integer id, 
    										Model model) {
   
		logger.debug("Received request to delete existing person");
		
		personService.delete(id);
		
		model.addAttribute("id", id);
    	
		return "user/deletedpage";
	}
    
    /**
     * editָ����Person
     */
    @RequestMapping(value = "/persons/edit.do", method = RequestMethod.GET)
    public String edit(
    		@RequestParam(value="id", required=true) Integer id,
    		@RequestParam(value="firstname", required=true) String firstName,
    		@RequestParam(value="lastname", required=true) String lastName,
    		@RequestParam(value="money", required=true) Double money,
    		Model model){
   
		logger.debug("Received request to edit existing person");

		personService.edit(id, firstName, lastName, money);

		model.addAttribute("id", id);
		
		return "user/editedpage";
	}
    @RequestMapping(value="index.do")
	public String showIndexPage(){
		return "user/index";
	}
}
