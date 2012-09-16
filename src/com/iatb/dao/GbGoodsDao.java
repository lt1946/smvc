package com.iatb.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbGoods;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbGoodsDao")
@SuppressWarnings("deprecation")
public class GbGoodsDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbGoodsDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param gbGoods
	 */
	public int addReturnId(GbGoods gbGoods){
		return insert("insert into gb_goods (`siteid`,`url`,`title`,`simageurl`,`mediumimageur`,`largeimageurl`,`cityid`,`status`,`startdate`,`enddate`,`price`,`value`,`amount`,`percent`,`website`,`expiration`,`detail`,`createTime`,`updateTime`,`isExpired`,`cate`,`city`,`siteurl`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", gbGoods);
	}
	/**
	 * ���
	 * @param gb_goods
	 */
	public String add(GbGoods gbGoods){
		String sql="insert into gb_goods (`siteid`,`url`,`title`,`simageurl`,`mediumimageur`,`largeimageurl`,`cityid`,`status`,`startdate`,`enddate`,`price`,`value`,`amount`,`percent`,`website`,`expiration`,`detail`,`createTime`,`updateTime`,`isExpired`,`cate`,`city`,`siteurl`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbGoods.getSiteid(),gbGoods.getUrl(),gbGoods.getTitle(),gbGoods.getSimageurl(),gbGoods.getMediumimageur(),gbGoods.getLargeimageurl(),gbGoods.getCityid(),gbGoods.getStatus(),gbGoods.getStartdate(),gbGoods.getEnddate(),gbGoods.getPrice(),gbGoods.getValue(),gbGoods.getAmount(),gbGoods.getPercent(),gbGoods.getWebsite(),gbGoods.getExpiration(),gbGoods.getDetail(),gbGoods.getCreateTime(),gbGoods.getUpdateTime(),gbGoods.getIsExpired(),gbGoods.getCate(),gbGoods.getCity(),gbGoods.getSiteurl());	
		log.info("���gbGoods:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���gbGoods:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ����
	 * @param gb_goods
	 */
	public boolean save(GbGoods gbGoods){
		try {
			String sql="insert into gb_goods (`siteid`,`url`,`title`,`simageurl`,`mediumimageur`,`largeimageurl`,`cityid`,`status`,`startdate`,`enddate`,`price`,`value`,`amount`,`percent`,`website`,`expiration`,`detail`,`createTime`,`updateTime`,`isExpired`,`cate`,`city`,`siteurl`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int c=jdbcTemplate.update(sql.toString(),gbGoods.getSiteid(),gbGoods.getUrl(),gbGoods.getTitle(),gbGoods.getSimageurl(),gbGoods.getMediumimageur(),gbGoods.getLargeimageurl(),gbGoods.getCityid(),gbGoods.getStatus(),gbGoods.getStartdate(),gbGoods.getEnddate(),gbGoods.getPrice(),gbGoods.getValue(),gbGoods.getAmount(),gbGoods.getPercent(),gbGoods.getWebsite(),gbGoods.getExpiration(),gbGoods.getDetail(),gbGoods.getCreateTime(),gbGoods.getUpdateTime(),gbGoods.getIsExpired(),gbGoods.getCate(),gbGoods.getCity(),gbGoods.getSiteurl());	
			return c>0?true:false;
		} catch (DataAccessException e) {
			return false;
		}
	}
	/**
	 * �޸�
	 * @param gb_goods
	 */
	public boolean update(GbGoods gbGoods){
		String sql="update gb_goods set (`siteid`=?,`url`=?,`title`=?,`simageurl`=?,`mediumimageur`=?,`largeimageurl`=?,`cityid`=?,`status`=?,`startdate`=?,`enddate`=?,`price`=?,`value`=?,`amount`=?,`percent`=?,`website`=?,`expiration`=?,`detail`=?,`createTime`=?,`updateTime`=?,`isExpired`=?,`cate`=?,`city`=?,`siteurl`=?) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbGoods.getSiteid(),gbGoods.getUrl(),gbGoods.getTitle(),gbGoods.getSimageurl(),gbGoods.getMediumimageur(),gbGoods.getLargeimageurl(),gbGoods.getCityid(),gbGoods.getStatus(),gbGoods.getStartdate(),gbGoods.getEnddate(),gbGoods.getPrice(),gbGoods.getValue(),gbGoods.getAmount(),gbGoods.getPercent(),gbGoods.getWebsite(),gbGoods.getExpiration(),gbGoods.getDetail(),gbGoods.getCreateTime(),gbGoods.getUpdateTime(),gbGoods.getIsExpired(),gbGoods.getCate(),gbGoods.getCity(),gbGoods.getSiteurl(),gbGoods.getId());	
		return c>0?true:false;
	}
	/**
	 * ��ѯ����
	 */
	public List<GbGoods> listAll(){
		return listAll("gb_goods", GbGoods.class);
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_goods", id)>0?true:false;
	}
	/**
	 * ��ȡΨһ
	 * @param id
	 */
	public GbGoods load(Integer id){
		return getById("gb_goods", id, GbGoods.class);
	}
	
	
}
