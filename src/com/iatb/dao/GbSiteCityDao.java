package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbSiteCity;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbSiteCityDao")
@SuppressWarnings("deprecation")
public class GbSiteCityDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbSiteCityDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param gbSiteCity
	 */
	public int addReturnId(GbSiteCity gbSiteCity){
		return insert("insert into gb_site_city (`cityid`,`status`) values(?,?)", gbSiteCity);
	}
	/**
	 * ���
	 * @param gb_site_city
	 */
	public String add(GbSiteCity gbSiteCity){
		String sql="insert into gb_site_city (`cityid`,`status`) values(?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbSiteCity.getCityid(),gbSiteCity.getStatus());	
		log.info("���gbSiteCity:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���gbSiteCity:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param gb_site_city
	 */
	public boolean save(GbSiteCity gbSiteCity){
		String sql="insert into gb_site_city (`cityid`,`status`) values(?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbSiteCity.getCityid(),gbSiteCity.getStatus());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param gb_site_city
	 */
	public boolean update(GbSiteCity gbSiteCity){
		String sql="update gb_site_city set (`cityid`=?,`status`=?) values(?,?) where gbsiteid=?";
		int c=jdbcTemplate.update(sql.toString(),gbSiteCity.getCityid(),gbSiteCity.getStatus(),gbSiteCity.getGbsiteid());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<GbSiteCity> listAll(){
		return listAll("gb_site_city", GbSiteCity.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_site_city", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public GbSiteCity load(Integer id){
		return getById("gb_site_city", id, GbSiteCity.class);
	}
	
	
}
