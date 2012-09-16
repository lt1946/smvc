package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Groupurlrole;

/**
 * Groupurlrole管理业务逻辑接口
 * @author Administrator
 * @since  2012-01-23 00:45:11
 */
public interface GroupurlroleService {
	/** 浏览 Groupurlrole*/
	public List<Groupurlrole> view();
	public List<Groupurlrole> browseGroupurlrole();
	/** 装载指定的 Groupurlrole*/
	public Groupurlrole loadGroupurlrole(Integer id);
	/** 删除指定的 Groupurlrole*/	
	public boolean deleteGroupurlrole(Integer id);
	/** 新增 Groupurlrole*/
	public boolean saveGroupurlrole(Groupurlrole groupurlrole);
	/** 修改 Groupurlrole*/	
	public boolean updateGroupurlrole(Groupurlrole groupurlrole);
	/** 分页浏览Groupurlrole */
	public List<Groupurlrole> view(int page,int row);
	/** 查询总行数 */
	public Long count();
	/** 复制id的记录	 */
	public boolean copyById(String id);
	/** 删除id的记录	 */
	public boolean deleteById(String id);
}