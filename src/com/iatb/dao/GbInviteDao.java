package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbInvite;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbInviteDao")
@SuppressWarnings("deprecation")
public class GbInviteDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbInviteDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param gbInvite
	 */
	public int addReturnId(GbInvite gbInvite){
		return insert("insert into gb_invite (`myuser`,`iuser`,`isiteid`,`createTime`,`status`) values(?,?,?,?,?)", gbInvite);
	}
	/**
	 * ���
	 * @param gb_invite
	 */
	public String add(GbInvite gbInvite){
		String sql="insert into gb_invite (`myuser`,`iuser`,`isiteid`,`createTime`,`status`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbInvite.getMyuser(),gbInvite.getIuser(),gbInvite.getIsiteid(),gbInvite.getCreateTime(),gbInvite.getStatus());	
		log.info("���gbInvite:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���gbInvite:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param gb_invite
	 */
	public boolean save(GbInvite gbInvite){
		String sql="insert into gb_invite (`myuser`,`iuser`,`isiteid`,`createTime`,`status`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbInvite.getMyuser(),gbInvite.getIuser(),gbInvite.getIsiteid(),gbInvite.getCreateTime(),gbInvite.getStatus());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param gb_invite
	 */
	public boolean update(GbInvite gbInvite){
		String sql="update gb_invite set (`myuser`=?,`iuser`=?,`isiteid`=?,`createTime`=?,`status`=?) values(?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbInvite.getMyuser(),gbInvite.getIuser(),gbInvite.getIsiteid(),gbInvite.getCreateTime(),gbInvite.getStatus(),gbInvite.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<GbInvite> listAll(){
		return listAll("gb_invite", GbInvite.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_invite", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public GbInvite load(Integer id){
		return getById("gb_invite", id, GbInvite.class);
	}
	
	
}
