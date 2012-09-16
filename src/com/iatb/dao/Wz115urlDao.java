package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Wz115url;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-01-23 23:14:59
 */
@Repository("wz115urlDao")
@SuppressWarnings("deprecation")
public class Wz115urlDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(Wz115urlDao.class);
	/**
	 * ���
	 * @param wz115url
	 */
	public String add(Wz115url wz115url){
		String sql="insert into wz115url (`title`,`url`,`createTime`,`status`) values(?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),wz115url.getTitle(),wz115url.getUrl(),wz115url.getCreateTime(),wz115url.getStatus());	
		log.info("���wz115url:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���wz115url:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param wz115url
	 */
	public boolean save(Wz115url wz115url){
		String sql="insert into wz115url (`title`,`url`,`createTime`,`status`) values(?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),wz115url.getTitle(),wz115url.getUrl(),wz115url.getCreateTime(),wz115url.getStatus());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param wz115url
	 */
	public boolean update(Wz115url wz115url){
		String sql="update wz115url set `title`=?,`url`=?,`createTime`=?,`status`=? where id=?";
		int c=jdbcTemplate.update(sql.toString(),wz115url.getTitle(),wz115url.getUrl(),wz115url.getCreateTime(),wz115url.getStatus(),wz115url.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<Wz115url> listAll(){
		return listAll("wz115url", Wz115url.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("wz115url", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public Wz115url load(Integer id){
		return getById("wz115url", id, Wz115url.class);
	}
	
	
}
