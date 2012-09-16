package com.iatb.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Webbean;

/**
 * @author Administrator
 * @since  2011-12-15 14:40:30
 */
@Repository("webbeanDao")
public class WebbeanDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(WebbeanDao.class);
	
	/**
	 * ���
	 * @param webbean
	 */
	public String add(Webbean webbean){
		String sql="insert into webbean (`gurid`,`title`,`url`,`createTime`,`status`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),webbean.getGurid(),webbean.getTitle(),webbean.getUrl(),webbean.getCreateTime(),webbean.getStatus());	
		log.info("���webbean:"+(c>0?"�ɹ�":"ʧ��"));
		return ("���webbean:"+(c>0?"�ɹ�":"ʧ��"));
	}
	
	/**
	 * ���
	 * @param webbean
	 */
	public int addReturnId(Webbean wb){
		return insert("insert into webbean (gurid,pid,title,url,createTime,status) values(:gurid,:pid,:title,:url,:createTime,:status)", wb);
	}
	
	/**
	 * ���������б����أ�isend=1ʱ���Ƿ��ظ���stopnew
	 * @param isend
	 * @param list
	 * @return
	 */
	public boolean batchSave(boolean isend, List<Object[]> list) {
		boolean b=false;
		String sql="insert into webbean (`gurid`,`title`,`url`,`createTime`,`status`) values(?,?,?,?,?)";
		try {
			jdbcTemplate.batchUpdate(sql, list);
		} catch (DuplicateKeyException e) {
			for (Object[] objects : list) {
				try {
					jdbcTemplate.update(sql, objects);
				} catch (DuplicateKeyException e1) {
					if(isend)b=true;
					continue;
				}catch(Exception e2){
					e2.printStackTrace();
					continue;
				}
			}
		}
		return b;
	}
}
