package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbSite;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbSiteDao")
@SuppressWarnings("deprecation")
public class GbSiteDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbSiteDao.class);
	
	/**
	 * 添加并返回id
	 * @param gbSite
	 */
	public int addReturnId(GbSite gbSite){
		return insert("insert into gb_site (`siteName`,`siteUrl`,`loginUrl`,`signupUrl`,`inviteURl`,`isReg`,`isDownLine`,`isInvite`,`isCheck`,`createTime`,`status`,`encode`,`updateTime`) values(?,?,?,?,?,?,?,?,?,?,?,?,?)", gbSite);
	}
	/**
	 * 添加
	 * @param gb_site
	 */
	public String add(GbSite gbSite){
		String sql="insert into gb_site (`siteName`,`siteUrl`,`loginUrl`,`signupUrl`,`inviteURl`,`isReg`,`isDownLine`,`isInvite`,`isCheck`,`createTime`,`status`,`encode`,`updateTime`) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbSite.getSiteName(),gbSite.getSiteUrl(),gbSite.getLoginUrl(),gbSite.getSignupUrl(),gbSite.getInviteURl(),gbSite.getIsReg(),gbSite.getIsDownLine(),gbSite.getIsInvite(),gbSite.getIsCheck(),gbSite.getCreateTime(),gbSite.getStatus(),gbSite.getEncode(),gbSite.getUpdateTime());	
		log.info("添加gbSite:"+(c>0?"成功":"失败"));
		return ("添加gbSite:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param gb_site
	 */
	public boolean save(GbSite gbSite){
		String sql="insert into gb_site (`siteName`,`siteUrl`,`loginUrl`,`signupUrl`,`inviteURl`,`isReg`,`isDownLine`,`isInvite`,`isCheck`,`createTime`,`status`,`encode`,`updateTime`) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbSite.getSiteName(),gbSite.getSiteUrl(),gbSite.getLoginUrl(),gbSite.getSignupUrl(),gbSite.getInviteURl(),gbSite.getIsReg(),gbSite.getIsDownLine(),gbSite.getIsInvite(),gbSite.getIsCheck(),gbSite.getCreateTime(),gbSite.getStatus(),gbSite.getEncode(),gbSite.getUpdateTime());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param gb_site
	 */
	public boolean update(GbSite gbSite){
		String sql="update gb_site set (`siteName`=?,`siteUrl`=?,`loginUrl`=?,`signupUrl`=?,`inviteURl`=?,`isReg`=?,`isDownLine`=?,`isInvite`=?,`isCheck`=?,`createTime`=?,`status`=?,`encode`=?,`updateTime`=?) values(?,?,?,?,?,?,?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbSite.getSiteName(),gbSite.getSiteUrl(),gbSite.getLoginUrl(),gbSite.getSignupUrl(),gbSite.getInviteURl(),gbSite.getIsReg(),gbSite.getIsDownLine(),gbSite.getIsInvite(),gbSite.getIsCheck(),gbSite.getCreateTime(),gbSite.getStatus(),gbSite.getEncode(),gbSite.getUpdateTime(),gbSite.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<GbSite> listAll(){
		return listAll("gb_site", GbSite.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_site", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public GbSite load(Integer id){
		return getById("gb_site", id, GbSite.class);
	}
	
	
}
