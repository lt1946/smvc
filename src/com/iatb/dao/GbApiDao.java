package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbApi;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbApiDao")
@SuppressWarnings("deprecation")
public class GbApiDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbApiDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param gbApi
	 */
	public int addReturnId(GbApi gbApi){
		return insert("insert into gb_api (`url`,`gbsiteid`,`createTime`,`status`,`updateTime`) values(?,?,?,?,?)", gbApi);
	}
	/**
	 * ���
	 * @param gb_api
	 */
	public String add(GbApi gbApi){
		String sql="insert into gb_api (`url`,`gbsiteid`,`createTime`,`status`,`updateTime`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbApi.getUrl(),gbApi.getGbsiteid(),gbApi.getCreateTime(),gbApi.getStatus(),gbApi.getUpdateTime());	
		log.info("���gbApi:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���gbApi:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param gb_api
	 */
	public boolean save(GbApi gbApi){
		String sql="insert into gb_api (`url`,`gbsiteid`,`createTime`,`status`,`updateTime`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbApi.getUrl(),gbApi.getGbsiteid(),gbApi.getCreateTime(),gbApi.getStatus(),gbApi.getUpdateTime());	
		return c>0?true:false;
	}
	/**
	 * �޸�
	 * @param gb_api
	 */
	public boolean update(GbApi gbApi){
		String sql="update gb_api set (`url`=?,`gbsiteid`=?,`createTime`=?,`status`=?,`updateTime`=?) values(?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbApi.getUrl(),gbApi.getGbsiteid(),gbApi.getCreateTime(),gbApi.getStatus(),gbApi.getUpdateTime(),gbApi.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<GbApi> listAll(){
		return listAll("gb_api", GbApi.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_api", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public GbApi load(Integer id){
		return getById("gb_api", id, GbApi.class);
	}
	
	
}
