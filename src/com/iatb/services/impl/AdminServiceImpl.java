package com.iatb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iatb.dao.AdminDao;
import com.iatb.pojo.Admin;
import com.iatb.services.AdminService;

/** ϵͳ�û�����ҵ���߼��ӿ�ʵ�� */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDao adminDao;

	/** ϵͳ����Ա��¼ */
	public Admin adminLogin(String loginName, String loginPwd){
		return adminDao.getAdmin(loginName,loginPwd);
	}
	public boolean saveAdmin(Admin admin) {
		return adminDao.saveAdmin(admin);
	}
	public boolean updateAdmin(Admin admin) {
		return adminDao.updateAdmin(admin);
	}

	/** �������Ա */
	public List<Admin> browseAdmin(){
		return adminDao.listAll();
	}

	/** ɾ��ָ���Ĺ���Ա */
	public boolean delAdmin(Integer id){
		return adminDao.delAdmin(id);
	}

	/** װ��ָ���Ĺ���Ա */
	public Admin loadAdmin(Integer id){
		return adminDao.loadAdmin(id);
	}

}
