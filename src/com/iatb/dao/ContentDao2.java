package com.iatb.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iatb.bean.Webbean;
import com.iatb.pojo.Content;

/**
 * @author Administrator
 * @since  2011-12-14 20:57:25
 */
@Repository("contentDao")
public class ContentDao2  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(ContentDao2.class);
	/**
	 * ���
	 * @param content
	 */
	public String add(Content content){
		String sql="insert into content (`title`,`url`,`content`,`siteid`,`groupurlid`,`status`,`createTime`) values(?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),content.getTitle(),content.getUrl(),content.getContent(),content.getSiteid(),content.getGroupurlid(),content.getStatus(),content.getCreateTime());	
		log.info("���content:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���content:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ���webben�Ƿ��ظ�
	 * @param wb
	 * @return
	 */
	public  boolean checkWebbean(Webbean wb) {
		Long i=(Long) jdbcTemplate.queryForObject( "select count(1) b from content where title='"+wb.getTitle().replaceAll("'", "��").replaceAll("\"","��")+"'",Long.class);
		return i>0?true:false;
	}
}
