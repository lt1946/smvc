package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.iatb.bean.Webbean;
import com.iatb.pojo.Content;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-01-14 21:31:05
 */
@Repository("contentDao")
@SuppressWarnings("deprecation")
public class ContentDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(ContentDao.class);
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
	 * ����
	 * @param content
	 */
	public boolean save(Content content){
		String sql="insert into content (`title`,`url`,`content`,`siteid`,`groupurlid`,`status`,`createTime`) values(?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),content.getTitle(),content.getUrl(),content.getContent(),content.getSiteid(),content.getGroupurlid(),content.getStatus(),content.getCreateTime());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param content
	 */
	public boolean update(Content content){
		String sql="update content set (`title`=?,`url`=?,`content`=?,`siteid`=?,`groupurlid`=?,`status`=?,`createTime`=?) values(?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),content.getTitle(),content.getUrl(),content.getContent(),content.getSiteid(),content.getGroupurlid(),content.getStatus(),content.getCreateTime(),content.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<Content> listAll(){
		return listAll("content", Content.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("content", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public Content load(Integer id){
		return getById("content", id, Content.class);
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
