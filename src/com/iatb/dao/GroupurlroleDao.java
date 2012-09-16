package com.iatb.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.iatb.bean.Webbean;
import com.iatb.pojo.Groupurlrole;
import com.iatb.util.MyString;

/**
 * @author Administrator
 * @since  2012-01-23 00:48:44
 */
@Repository("groupurlroleDao")
@SuppressWarnings("deprecation")
public class GroupurlroleDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GroupurlroleDao.class);
	
	/**
	 * 获取限制gur
	 * @return
	 */
	public List<Groupurlrole> getLimitGur(){
		return jdbcTemplate.query("select * from groupurlrole where status=5 and isNew=1",ParameterizedBeanPropertyRowMapper.newInstance(Groupurlrole.class));
	}
	/**
	 * 获取普通gur
	 * @return
	 */
	public List<Groupurlrole> getNormalGur(){
		return jdbcTemplate.query("select * from groupurlrole where status=1 and isNew=1",ParameterizedBeanPropertyRowMapper.newInstance(Groupurlrole.class));
	}
	
	/**
	 * 过滤webbean
	 * @param title
	 * @param link
	 * @return
	 */
	public Webbean checkUrl(Groupurlrole gur,String title,String link){
		if(title==null||link==null||title.equals(""))return null;
		String titleLike=gur.getTitleLike();
		String titleUnLike=gur.getTitleUnLike();
		String urlLike=gur.getUrlLike();
		String urlUnLike=gur.getUrlUnLike();
		if(titleLike!=null&&!titleLike.trim().equals("")){
			String tl[]=titleLike.split("[|]");
			boolean btl=true;
			for (int i = 0; i < tl.length; i++) {
				if(title.indexOf(tl[i])>=0){btl=false;break;}
			}
			if(btl)return null;
		}
		if(urlLike!=null&&!urlLike.trim().equals("")){
			if(gur.getUrlWebs()!=null&&gur.getUrlWebs().trim().length()!=0){
				if(gur.getUrlWebs().split("[|]").length==2){
					if(!link.startsWith(urlLike.split("[|]")[1])){return null;}
				}else{
					String tl[]=urlLike.split("[|]");
					boolean btl=true;
					for (int i = 0; i < tl.length; i++) {
						if(link.indexOf(tl[i])>=0){btl=false;break;}
					}
					if(btl)return null;
				}
			}else{
				String tl[]=urlLike.split("[|]");
				boolean btl=true;
				for (int i = 0; i < tl.length; i++) {
					if(link.indexOf(tl[i])>=0){btl=false;break;}
				}
				if(btl)return null;
			}
		}
		if(urlUnLike!=null&&!urlUnLike.trim().equals("")){
			String tl[]=urlUnLike.split("[|]");
			
			boolean btl=false;
			for (int i = 0; i < tl.length; i++) {
				if(link.indexOf(tl[i])>=0){btl=true;break;}
			}
			if(btl)return null;
		}
		if(titleUnLike!=null&&!titleUnLike.trim().equals("")){
			String tl[]=titleUnLike.split("[|]");
			boolean btl=false;
			for (int i = 0; i < tl.length; i++) {
				if(title.indexOf(tl[i])>=0){btl=true;break;}
			}
			if(btl)return null;
		} 
		title=MyString.decode(title);
		return new Webbean(title,link);
	}
	/**
	 * 添加
	 * @param groupurlrole
	 */
	public String add(Groupurlrole groupurlrole){
		String sql="insert into groupurlrole (`name`,`url`,`titleLike`,`titleUnLike`,`urlWebs`,`urlLike`,`urlUnLike`,`contentPre`,`contentEnd`,`createTime`,`status`,`isNew`,`contentUnLike`,`siteid`,`encode`,`webs`,`contentDel`,`isInner`,`innerWebs`,`innerEnd`,`hasCode`,`isPic`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),groupurlrole.getName(),groupurlrole.getUrl(),groupurlrole.getTitleLike(),groupurlrole.getTitleUnLike(),groupurlrole.getUrlWebs(),groupurlrole.getUrlLike(),groupurlrole.getUrlUnLike(),groupurlrole.getContentPre(),groupurlrole.getContentEnd(),groupurlrole.getCreateTime(),groupurlrole.getStatus(),groupurlrole.getIsNew(),groupurlrole.getContentUnLike(),groupurlrole.getSiteid(),groupurlrole.getEncode(),groupurlrole.getWebs(),groupurlrole.getContentDel(),groupurlrole.getIsInner(),groupurlrole.getInnerWebs(),groupurlrole.getInnerEnd(),groupurlrole.getHasCode(),groupurlrole.getIsPic());	
		log.info("添加groupurlrole:"+(c>0?"成功":"失败"));
		return ("添加groupurlrole:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param groupurlrole
	 */
	public boolean save(Groupurlrole groupurlrole){
		String sql="insert into groupurlrole (`name`,`url`,`titleLike`,`titleUnLike`,`urlWebs`,`urlLike`,`urlUnLike`,`contentPre`,`contentEnd`,`createTime`,`status`,`isNew`,`contentUnLike`,`siteid`,`encode`,`webs`,`contentDel`,`isInner`,`innerWebs`,`innerEnd`,`hasCode`,`isPic`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),groupurlrole.getName(),groupurlrole.getUrl(),groupurlrole.getTitleLike(),groupurlrole.getTitleUnLike(),groupurlrole.getUrlWebs(),groupurlrole.getUrlLike(),groupurlrole.getUrlUnLike(),groupurlrole.getContentPre(),groupurlrole.getContentEnd(),groupurlrole.getCreateTime(),groupurlrole.getStatus(),groupurlrole.getIsNew(),groupurlrole.getContentUnLike(),groupurlrole.getSiteid(),groupurlrole.getEncode(),groupurlrole.getWebs(),groupurlrole.getContentDel(),groupurlrole.getIsInner(),groupurlrole.getInnerWebs(),groupurlrole.getInnerEnd(),groupurlrole.getHasCode(),groupurlrole.getIsPic());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param groupurlrole
	 */
	public boolean update(Groupurlrole groupurlrole){
		String sql="update groupurlrole set (`name`=?,`url`=?,`titleLike`=?,`titleUnLike`=?,`urlWebs`=?,`urlLike`=?,`urlUnLike`=?,`contentPre`=?,`contentEnd`=?,`createTime`=?,`status`=?,`isNew`=?,`contentUnLike`=?,`siteid`=?,`encode`=?,`webs`=?,`contentDel`=?,`isInner`=?,`innerWebs`=?,`innerEnd`=?,`hasCode`=?,`isPic`=?) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),groupurlrole.getName(),groupurlrole.getUrl(),groupurlrole.getTitleLike(),groupurlrole.getTitleUnLike(),groupurlrole.getUrlWebs(),groupurlrole.getUrlLike(),groupurlrole.getUrlUnLike(),groupurlrole.getContentPre(),groupurlrole.getContentEnd(),groupurlrole.getCreateTime(),groupurlrole.getStatus(),groupurlrole.getIsNew(),groupurlrole.getContentUnLike(),groupurlrole.getSiteid(),groupurlrole.getEncode(),groupurlrole.getWebs(),groupurlrole.getContentDel(),groupurlrole.getIsInner(),groupurlrole.getInnerWebs(),groupurlrole.getInnerEnd(),groupurlrole.getHasCode(),groupurlrole.getIsPic(),groupurlrole.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<Groupurlrole> listAll(){
		return listAll("groupurlrole", Groupurlrole.class);
	}
	/**
	 * 查询所有
	 */
	public List<Groupurlrole> view(){
		return getList("select * from groupurlrole",Groupurlrole.class,null);
	}
	/**
	 * 分页查询所有
	 */
	public List<Groupurlrole> view(int page,int row){
		return getList("select * from groupurlrole where status<>-1 order by id asc limit ?,?",Groupurlrole.class,new Object[]{row*(page-1),row});
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("groupurlrole", id)>0?true:false;
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean deletes(String id){
		boolean b=true;
		String ids[]=id.split(",");
		for (int i = 0; i < ids.length; i++) {
			b=b&&deleteById("groupurlrole", Integer.parseInt(ids[i]))>0?true:false;
		}
		return b;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public Groupurlrole load(Integer id){
		return getById("groupurlrole", id, Groupurlrole.class);
	}
	/** 复制id的记录	 */
	public boolean copyById(String id) {
		return jdbcTemplate.update("insert into groupurlrole " +
				"(name,url,titleLike,titleUnLike,urlWebs,urlLike,urlUnLike," +
				"contentPre,contentEnd,createTime,status,isNew,contentUnLike," +
				"siteid,encode,webs,contentDel,isInner ,innerWebs,innerEnd,hasCode,isPic)" +
				" select name,url,titleLike,titleUnLike,urlWebs,urlLike,urlUnLike," +
				"contentPre,contentEnd,now(),status,isNew,contentUnLike,siteid,encode, " +
				"webs,contentDel,isInner ,innerWebs,innerEnd,hasCode,isPic " +
				"from groupurlrole where id=?", new Object[]{id})>0;
	}
	
}
