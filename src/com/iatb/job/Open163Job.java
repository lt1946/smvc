package com.iatb.job;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.iatb.pojo.Open163Type;
import com.iatb.services.Open163CourseService;
import com.iatb.services.Open163LectureService;
import com.iatb.services.Open163OpenclassService;
import com.iatb.services.Open163TypeService;
import com.iatb.services.Open163UniversityService;
import com.iatb.util.http.MyHtmlCleaner;

@Service
public class Open163Job {
	private static final Logger log=Logger.getLogger(Open163Job.class);
	@Autowired	Open163CourseService	open163CourseService;
	@Autowired	Open163LectureService	open163LectureService;
	@Autowired	Open163TypeService		open163TypeService;
	@Autowired	Open163OpenclassService		open163OpenclassService;
	@Autowired	Open163UniversityService	open163UniversityService;
//	@Scheduled(cron="0 20 1/2 * * ?")
	public void spider(){
		log.info("==========开始获取Open163Job============");
		
		log.info("==========结束获取Open163Job============");
	}
//	@Test
//	@Scheduled(cron="0 55 * * * ?")
	public void spiderType(){
		Map<String, String> m=new HashMap<String, String>();
		String url="http://so.v.163.com/movie/listpage/listprogram2/pl2/%D5%DC%D1%A7/default/fc/ot/default/1.html";
			m = MyHtmlCleaner.getAllLink(url, "gbk","ul,class,contentSliderArea-list cDBlue");
			for(Map.Entry<String, String> me:m.entrySet()){
//				System.out.println(me.getKey());
//				System.out.println(me.getValue());
				open163TypeService.saveOpen163Type(new Open163Type(me.getKey(),me.getValue()));
			}
	}
}
