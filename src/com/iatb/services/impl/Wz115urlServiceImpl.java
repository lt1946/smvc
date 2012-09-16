package com.iatb.services.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iatb.dao.GroupurlnowDao;
import com.iatb.dao.GroupurlroleDao;
import com.iatb.dao.Wz115urlDao;
import com.iatb.pojo.Groupurlnow;
import com.iatb.pojo.Groupurlrole;
import com.iatb.pojo.Wz115url;
import com.iatb.services.Wz115urlService;
import com.iatb.util.DateUtil;
import com.iatb.util.DownloadNet;
import com.iatb.util.StringUtil;
import com.iatb.util.SystemUtil;
import com.iatb.util.db.ConnTools;
import com.iatb.util.http.MyHtmlCleaner;
import com.iatb.util.spider.GoogleSpider;
import com.iatb.util.spider.UDSpider;

/**
 * Wz115url管理业务逻辑接口实现
 * @author Administrator
 * @since  2012-01-23 23:14:59
 */
@Service("wz115urlService")
public class Wz115urlServiceImpl implements Wz115urlService {
	/** 通过依赖注入DAO组件实例 */
	@Autowired
	Wz115urlDao wz115urlDao;
	@Autowired
	GroupurlnowDao groupurlnowDao;
	@Autowired
	GroupurlroleDao groupurlroleDao;
	/** 新增Wz115url */	
	public boolean saveWz115url(Wz115url columns){
			return wz115urlDao.save(columns);
	}
	/** 修改Wz115url */	
	public boolean updateWz115url(Wz115url columns){
		return wz115urlDao.update(columns);
	}

	/** 浏览Wz115url */
	public List<Wz115url> browseWz115url(){
		return wz115urlDao.listAll();
	}
	
	/** 删除指定的Wz115url */
	public boolean deleteWz115url(Integer id){
		return wz115urlDao.delete(id);
	}

	/** 装载指定的Wz115url */
	public Wz115url loadWz115url(Integer id){
		return (Wz115url)wz115urlDao.load(id);
	}
	public void spider115Wz() {
		boolean  isWin=SystemUtil.isWindows();
		String path = isWin?"D://SEO//Earn//80lou//"+ DateUtil.getCurrentDate() + "//":"/home/media/"+ DateUtil.getCurrentDate() + "/";
		if (!new File(path).exists())
			new File(path).mkdirs();
		List<Groupurlrole> gurlist=groupurlroleDao.getList("select * from groupurlrole where siteid=14", Groupurlrole.class, null);
		for (Groupurlrole gur : gurlist) {
			Groupurlnow gun=groupurlnowDao.get("select * from groupurlnow where gurid=?", Groupurlnow.class,new Object[]{gur.getId()});
			if(gun==null){
				gun=new Groupurlnow(0,0,gur.getId(),0,0,0,"","");
				int id=groupurlnowDao.addReturnId(gun);
				gun.setId(id);
			}
			if(gun.getStatus()==1)return;
			String endurl=gun.getUrl()==null?"":gun.getUrl();
			boolean isEnd=gun.getIsEnd()==1,isSave=false;
			String url="",title="";
			int i=0;
			while(true){
				Map<String, String> m=GoogleSpider.getUrl(gur.getName(),i*10,10);
				if(m==null||m.size()==0)break;
				for (Map.Entry<String, String> mm: m.entrySet()) {
					boolean b=StringUtil.like(mm.getKey(),gur.getTitleLike());
					if(b)
					{
						String mmu=mm.getValue(),t=MyHtmlCleaner.getTitle(mmu, gur.getEncode());
						if(url.equals(""))url=mmu;
						if(wz115urlDao.count("select count(1) from wz115url where url=?", new Object[]{mmu})>0)continue;
						t=t.substring(0,t.indexOf("网盘下载|")==-1?t.length():t.indexOf("网盘下载|"));
						if(title.equals(""))title=t;
						if(isEnd){
							if(url.equals(endurl)){
								if(isSave){
									groupurlnowDao.execute("update groupurlnow set url=?,title=? where id=?", new Object[]{url,title,gun.getId()});
								}
								return;
							}
						}
						String wzurl=UDSpider.get115dlink(mm.getValue());
						if(wzurl==null)continue;
						saveWz115url(new Wz115url(t,mmu));
						download115Wz(new Wz115url(t,mmu),wzurl, path);
						isSave=true;
					}
				}
				i++;
			}
			if(!isEnd){
				groupurlnowDao.execute("update groupurlnow set url=?,title=?, isEnd=1  where id=?", new Object[]{url,title,gun.getId()});
			}
		}
	}
	public void download115Wz(Wz115url wz,String url,String path) {
			Long s = System.currentTimeMillis();
			DownloadNet d = new DownloadNet(url, path,wz.getTitle(), 10,true);
			System.out.println(wz.getTitle()+ (System.currentTimeMillis() - s));
			if (d.isFinish()){
				wz115urlDao.execute("update wz115url set status=1 where url=?",new Object[]{wz.getUrl()});
			}
	}

}
