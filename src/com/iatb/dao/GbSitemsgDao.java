package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbSitemsg;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbSitemsgDao")
@SuppressWarnings("deprecation")
public class GbSitemsgDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbSitemsgDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param gbSitemsg
	 */
	public int addReturnId(GbSitemsg gbSitemsg){
		return insert("insert into gb_sitemsg (`siteid`,`uptime`,`type`,`desc`,`email`,`tel`,`address`,`siteqq`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?)", gbSitemsg);
	}
	/**
	 * ���
	 * @param gb_sitemsg
	 */
	public String add(GbSitemsg gbSitemsg){
		String sql="insert into gb_sitemsg (`siteid`,`uptime`,`type`,`desc`,`email`,`tel`,`address`,`siteqq`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbSitemsg.getSiteid(),gbSitemsg.getUptime(),gbSitemsg.getType(),gbSitemsg.getDesc(),gbSitemsg.getEmail(),gbSitemsg.getTel(),gbSitemsg.getAddress(),gbSitemsg.getSiteqq(),gbSitemsg.getCreateTime(),gbSitemsg.getStatus());	
		log.info("���gbSitemsg:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���gbSitemsg:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param gb_sitemsg
	 */
	public boolean save(GbSitemsg gbSitemsg){
		String sql="insert into gb_sitemsg (`siteid`,`uptime`,`type`,`desc`,`email`,`tel`,`address`,`siteqq`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbSitemsg.getSiteid(),gbSitemsg.getUptime(),gbSitemsg.getType(),gbSitemsg.getDesc(),gbSitemsg.getEmail(),gbSitemsg.getTel(),gbSitemsg.getAddress(),gbSitemsg.getSiteqq(),gbSitemsg.getCreateTime(),gbSitemsg.getStatus());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param gb_sitemsg
	 */
	public boolean update(GbSitemsg gbSitemsg){
		String sql="update gb_sitemsg set (`siteid`=?,`uptime`=?,`type`=?,`desc`=?,`email`=?,`tel`=?,`address`=?,`siteqq`=?,`createTime`=?,`status`=?) values(?,?,?,?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbSitemsg.getSiteid(),gbSitemsg.getUptime(),gbSitemsg.getType(),gbSitemsg.getDesc(),gbSitemsg.getEmail(),gbSitemsg.getTel(),gbSitemsg.getAddress(),gbSitemsg.getSiteqq(),gbSitemsg.getCreateTime(),gbSitemsg.getStatus(),gbSitemsg.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<GbSitemsg> listAll(){
		return listAll("gb_sitemsg", GbSitemsg.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_sitemsg", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public GbSitemsg load(Integer id){
		return getById("gb_sitemsg", id, GbSitemsg.class);
	}
	
	
}
