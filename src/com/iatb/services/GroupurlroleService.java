package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Groupurlrole;

/**
 * Groupurlrole����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-01-23 00:45:11
 */
public interface GroupurlroleService {
	/** ��� Groupurlrole*/
	public List<Groupurlrole> view();
	public List<Groupurlrole> browseGroupurlrole();
	/** װ��ָ���� Groupurlrole*/
	public Groupurlrole loadGroupurlrole(Integer id);
	/** ɾ��ָ���� Groupurlrole*/	
	public boolean deleteGroupurlrole(Integer id);
	/** ���� Groupurlrole*/
	public boolean saveGroupurlrole(Groupurlrole groupurlrole);
	/** �޸� Groupurlrole*/	
	public boolean updateGroupurlrole(Groupurlrole groupurlrole);
	/** ��ҳ���Groupurlrole */
	public List<Groupurlrole> view(int page,int row);
	/** ��ѯ������ */
	public Long count();
	/** ����id�ļ�¼	 */
	public boolean copyById(String id);
	/** ɾ��id�ļ�¼	 */
	public boolean deleteById(String id);
}