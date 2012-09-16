package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Wbcontent;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-01-15 20:29:46
 */
@Repository("wbcontentDao")
@SuppressWarnings("deprecation")
public class WbcontentDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(WbcontentDao.class);
	/**
	 * ���
	 * @param wbcontent
	 */
	public String add(Wbcontent wbcontent){
		String sql="insert into wbcontent (`wbid`,`content`,`createTime`,`status`) values(?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),wbcontent.getWbid(),wbcontent.getContent(),wbcontent.getCreateTime(),wbcontent.getStatus());	
		log.info("���wbcontent:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���wbcontent:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param wbcontent
	 */
	public boolean save(Wbcontent wbcontent){
		String sql="insert into wbcontent (`wbid`,`content`,`createTime`,`status`) values(?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),wbcontent.getWbid(),wbcontent.getContent(),wbcontent.getCreateTime(),wbcontent.getStatus());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param wbcontent
	 */
	public boolean update(Wbcontent wbcontent){
		String sql="update wbcontent set (`wbid`=?,`content`=?,`createTime`=?,`status`=?) values(?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),wbcontent.getWbid(),wbcontent.getContent(),wbcontent.getCreateTime(),wbcontent.getStatus(),wbcontent.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<Wbcontent> listAll(){
		return listAll("wbcontent", Wbcontent.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("wbcontent", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public Wbcontent load(Integer id){
		return getById("wbcontent", id, Wbcontent.class);
	}
	
	
}
