package com.iatb.push;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

@SuppressWarnings({  "unchecked" })
public class PushInfo {

	public void setUserId(String userid) {
		WebContextFactory.get().getScriptSession().setAttribute("userid", userid);
	}
	public Util getUtil(String recpath,HttpServletRequest request){
		ServletContext sc = request.getSession().getServletContext();
		ServerContext sctx = ServerContextFactory.get(sc);
		Collection sessions = sctx.getScriptSessionsByPage(recpath);
		return  new Util(sessions);
	}
	public Collection<ScriptSession> getScriptSession(String recpath,String userid, HttpServletRequest request) {
		Collection<ScriptSession> sessions = new HashSet<ScriptSession>();
		Collection<ScriptSession> sessions2 = new HashSet<ScriptSession>();
		ServerContext sc=ServerContextFactory.get(request.getSession().getServletContext());
		if(sc==null)return null;
		sessions.addAll(sc.getScriptSessionsByPage(recpath));
		for (ScriptSession session : sessions) {
			String xuserid = (String) session.getAttribute("userid");
			if (xuserid != null){
				if(xuserid.equals(userid)) {
					sessions2.add(session);
				}
			}else{
				session.invalidate();
			}
		}
		return sessions2;
	}
	public void push(String content,String recpath,String jsname,HttpServletRequest request){
		Util util =  getUtil(recpath, request);
		util.addFunctionCall(jsname,content);
	}
	
}
