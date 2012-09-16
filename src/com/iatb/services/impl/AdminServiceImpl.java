package com.iatb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iatb.dao.AdminDao;
import com.iatb.pojo.Admin;
import com.iatb.services.AdminService;

/** 系统用户管理业务逻辑接口实现 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDao adminDao;

	/** 系统管理员登录 */
	public Admin adminLogin(String loginName, String loginPwd){
		return adminDao.getAdmin(loginName,loginPwd);
	}
	public boolean saveAdmin(Admin admin) {
		return adminDao.saveAdmin(admin);
	}
	public boolean updateAdmin(Admin admin) {
		return adminDao.updateAdmin(admin);
	}

	/** 浏览管理员 */
	public List<Admin> browseAdmin(){
		return adminDao.listAll();
	}

	/** 删除指定的管理员 */
	public boolean delAdmin(Integer id){
		return adminDao.delAdmin(id);
	}

	/** 装载指定的管理员 */
	public Admin loadAdmin(Integer id){
		return adminDao.loadAdmin(id);
	}

}
