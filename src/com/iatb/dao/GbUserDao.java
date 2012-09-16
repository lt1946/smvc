package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbUser;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbUserDao")
@SuppressWarnings("deprecation")
public class GbUserDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbUserDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param gbUser
	 */
	public int addReturnId(GbUser gbUser){
		return insert("insert into gb_user (`name`,`password`,`tel`,`city`,`createTime`,`status`,`gbsiteid`) values(?,?,?,?,?,?,?)", gbUser);
	}
	/**
	 * ���
	 * @param gb_user
	 */
	public String add(GbUser gbUser){
		String sql="insert into gb_user (`name`,`password`,`tel`,`city`,`createTime`,`status`,`gbsiteid`) values(?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbUser.getName(),gbUser.getPassword(),gbUser.getTel(),gbUser.getCity(),gbUser.getCreateTime(),gbUser.getStatus(),gbUser.getGbsiteid());	
		log.info("���gbUser:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���gbUser:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param gb_user
	 */
	public boolean save(GbUser gbUser){
		String sql="insert into gb_user (`name`,`password`,`tel`,`city`,`createTime`,`status`,`gbsiteid`) values(?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbUser.getName(),gbUser.getPassword(),gbUser.getTel(),gbUser.getCity(),gbUser.getCreateTime(),gbUser.getStatus(),gbUser.getGbsiteid());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param gb_user
	 */
	public boolean update(GbUser gbUser){
		String sql="update gb_user set (`name`=?,`password`=?,`tel`=?,`city`=?,`createTime`=?,`status`=?,`gbsiteid`=?) values(?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbUser.getName(),gbUser.getPassword(),gbUser.getTel(),gbUser.getCity(),gbUser.getCreateTime(),gbUser.getStatus(),gbUser.getGbsiteid(),gbUser.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<GbUser> listAll(){
		return listAll("gb_user", GbUser.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_user", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public GbUser load(Integer id){
		return getById("gb_user", id, GbUser.class);
	}
	
	
}
