package com.iatb.dao;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.iatb.bean.Webbean;
import com.iatb.pojo.Groupurlnow;

/**
 * @author Administrator
 * @since  2011-12-16 00:01:09
 */
@Repository("groupurlnowDao")
public class GroupurlnowDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GroupurlnowDao.class);
	
	/**
	 * ��Ӳ�����id
	 * @param webbean
	 */
	public int addReturnId(Groupurlnow groupurlnow){
		return insert("insert into groupurlnow (`daily`,`pagesize`,`gurid`,`createTime`,`status`,`isEnd`,`endurlnum`,`iswb`,`title`,`url`) values(?,?,?,?,?,?,?,?,?,?)", groupurlnow);
	}
	/**
	 * ���
	 * @param groupurlnow
	 */
	public String add(Groupurlnow groupurlnow){
		String sql="insert into groupurlnow (`daily`,`pagesize`,`gurid`,`createTime`,`status`,`isEnd`,`endurlnum`,`iswb`,`title`,`url`) values(?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),groupurlnow.getDaily(),groupurlnow.getPagesize(),groupurlnow.getGurid(),groupurlnow.getCreateTime(),groupurlnow.getStatus(),groupurlnow.getIsEnd(),groupurlnow.getEndurlnum(),groupurlnow.getIswb(),groupurlnow.getTitle(),groupurlnow.getUrl());	
		log.info("���groupurlnow:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���groupurlnow:"+(c>0?"�ɹ�":"ʧ��"));
	}
	/**
	 * ͨ��gurid��ȡGroupurlnow
	 * @param id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Groupurlnow getfromgurid(int id) {
		return jdbcTemplate.query("select * from groupurlnow where gurid=?", ParameterizedBeanPropertyRowMapper.newInstance(Groupurlnow.class), id).get(0);
	}
	/**
	 * �����ϴ�ץȡurl����ҳ��
	 * @param gun 
	 * @param ei 
	 */
	public boolean updateGunEndNum(int id, int ei){
		String sql="update groupurlnow set endurlnum=? where id=?";
		int c=jdbcTemplate.update(sql, ei,id);
		log.info("�����ϴ�ץȡurl����ҳ��"+(c>0?"�ɹ�":"ʧ��"));
		return c>0?true:false;
	}
	public boolean saveWebBean(int id, Webbean wb) {
		String sql="insert into groupurlnow (title,url) values(?,?) where gurid=?";
		int c=jdbcTemplate.update(sql, wb.getTitle(),wb.getUrl(),id);
		log.info("�����ϴ�ץȡwebbean"+(c>0?"�ɹ�":"ʧ��"));
		return c>0?true:false;
	}
	/**
	 * ͨ��gurid��ȡwebbean���ϴ�ץȡ��gun
	 * @param id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Groupurlnow getwbgurid(int id) {
		try {
			return jdbcTemplate.query("select * from groupurlnow where gurid=? and iswb=1",ParameterizedBeanPropertyRowMapper.newInstance(Groupurlnow.class),id).get(0);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * ����webbean�ϴ�ץȡurl����ҳ��
	 * @param id
	 * @param ei
	 * @return
	 */
	public boolean updateWBGunEndNum(int gurid, int ei) {
		String sql="update groupurlnow set endurlnum=? where gurid=? and iswb=1";
		int c=jdbcTemplate.update(sql, ei,gurid);
		log.info("����webbean�ϴ�ץȡurl����ҳ��"+(c>0?"�ɹ�":"ʧ��"));
		return c>0?true:false;
	}
	/**
	 * ����webbean�ϴ�ץȡisEnd=1
	 * @param id
	 * @param ei
	 * @return
	 */
	public boolean updateWBGunisEnd(int gurid) {
		String sql="update groupurlnow set isEnd=1 where gurid=? and isEnd=0 and iswb=1";
		int c=jdbcTemplate.update(sql, gurid);
		log.info("����webbean�ϴ�ץȡisEnd=1"+(c>0?"�ɹ�":"ʧ��"));
		return c>0?true:false;
	}
	/**
	 * �������ץȡisEnd=1��WB
	 * @param id
	 * @param t
	 * @param u
	 * @param isend 
	 * @return
	 */
	public boolean updateGunwb(int gurid, String t, String u, boolean isend) {
		String sql="update groupurlnow set title=?,url=? where gurid=? and iswb=1 and isEnd=?";
		int c=jdbcTemplate.update(sql,t,u, gurid,isend?1:0);
		log.info("�������ץȡisEnd=1��WB��"+(c>0?"�ɹ�":"ʧ��"));
		return c>0?true:false;
	}
	@SuppressWarnings("deprecation")
	public Groupurlnow getWB(int id,int guid) {
		return jdbcTemplate.query("select * from groupurlnow where gurid=?", ParameterizedBeanPropertyRowMapper.newInstance(Groupurlnow.class), id).get(0);
	}
	/**
	 * ����״̬��1.���� 0.����
	 * @param i
	 */
	public void updateGunStatus(int i,int gurid) {
		String sql="update groupurlnow set status=? where gurid=? and iswb=1";
		int c=jdbcTemplate.update(sql,i,gurid);
		log.info("����gurid��"+gurid+"����״̬Ϊ��"+(i==1?"����":"����")+(c>0?"�ɹ�":"ʧ��"));
	}
}
