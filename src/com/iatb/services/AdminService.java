package com.iatb.services;

import java.util.List;

import com.iatb.pojo.Admin;

/** ϵͳ�û�����ҵ���߼��ӿ� */
public interface AdminService {
	/** ϵͳ����Ա��¼ */
	public Admin adminLogin(String loginName,String loginPwd);	
	/** �������Ա */
	public List<Admin> browseAdmin();	
	/** װ��ָ���Ĺ���Ա */	
	public Admin loadAdmin(Integer id);	
	/** ɾ��ָ���Ĺ���Ա */	
	public boolean delAdmin(Integer id);	
	/** ��������Ա */
	public boolean saveAdmin(Admin admin);	
	/** �޸Ĺ���Ա */
	public boolean updateAdmin(Admin admin);	
	
}
