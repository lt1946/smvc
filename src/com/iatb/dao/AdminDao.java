package com.iatb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.iatb.pojo.Admin;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2011-12-19 22:01:53
 */
@SuppressWarnings("deprecation")
@Repository("adminDao")
public class AdminDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(AdminDao.class);
	/**
	 * 添加
	 * @param admin
	 */
	public String add(Admin admin){
		String sql="insert into admin (`LoginName`,`LoginPwd`,`Privileges`) values(?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),admin.getLoginName(),admin.getLoginPwd(),admin.getPrivileges());	
		log.info("添加admin:"+(c>0?"成功":"失败"));
		return ("添加admin:"+(c>0?"成功":"失败"));
	}
	public Admin getAdmin(String loginName, String loginPwd){
		return get("select * from admin where LoginName=? and LoginPwd=?", Admin.class, new Object[]{loginName,loginPwd});
	}
	
	public boolean saveAdmin(Admin admin){
		String sql="insert into admin (`LoginName`,`LoginPwd`,`Privileges`) values(?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),admin.getLoginName(),admin.getLoginPwd(),admin.getPrivileges());	
		return c>0?true:false;
	}
	public boolean updateAdmin(Admin admin){
		String sql="update admin set `LoginName`=?,`LoginPwd`=?,`Privileges`=? values(?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),admin.getLoginName(),admin.getLoginPwd(),admin.getPrivileges(),admin.getId());	
		return c>0?true:false;
	}
	public List<Admin> listAll(){
		return listAll("admin", Admin.class);
	}
	public boolean delAdmin(Integer id){
		return deleteById("admin", id)>0?true:false;
	}
	public Admin loadAdmin(Integer id){
		return getById("admin", id, Admin.class);
	}
}
