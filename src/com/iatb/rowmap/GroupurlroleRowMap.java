package com.iatb.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.iatb.pojo.Groupurlrole;

@SuppressWarnings("unchecked")
public class GroupurlroleRowMap implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Groupurlrole groupurlrole=new Groupurlrole();
		
		groupurlrole.setId(rs.getInt("id"));
		groupurlrole.setName(rs.getString("name"));
		groupurlrole.setUrl(rs.getString("url"));
		groupurlrole.setTitleLike(rs.getString("titleLike"));
		groupurlrole.setTitleUnLike(rs.getString("titleUnLike"));
		groupurlrole.setUrlWebs(rs.getString("urlWebs"));
		groupurlrole.setUrlLike(rs.getString("urlLike"));
		groupurlrole.setUrlUnLike(rs.getString("urlUnLike"));
		groupurlrole.setContentPre(rs.getString("contentPre"));
		groupurlrole.setContentEnd(rs.getString("contentEnd"));
		groupurlrole.setCreateTime();
		groupurlrole.setStatus(rs.getInt("status"));
		groupurlrole.setIsNew(rs.getInt("isNew"));
		groupurlrole.setContentUnLike(rs.getString("contentUnLike"));
		groupurlrole.setSiteid(rs.getInt("siteid"));
		groupurlrole.setEncode(rs.getString("encode"));
		groupurlrole.setWebs(rs.getString("webs"));
		groupurlrole.setContentDel(rs.getString("contentDel"));
		groupurlrole.setIsInner(rs.getInt("isInner"));
		groupurlrole.setInnerWebs(rs.getString("innerWebs"));
		groupurlrole.setInnerEnd(rs.getString("innerEnd"));
		groupurlrole.setHasCode(rs.getInt("hasCode"));
		groupurlrole.setIsPic(rs.getInt("isPic"));
		return groupurlrole;
	}

}
