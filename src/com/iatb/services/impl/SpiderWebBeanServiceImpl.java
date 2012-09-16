package com.iatb.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iatb.bean.Webbean;
import com.iatb.dao.GroupurlnowDao;
import com.iatb.dao.GroupurlroleDao;
import com.iatb.dao.WebbeanDao;
import com.iatb.pojo.Groupurlnow;
import com.iatb.pojo.Groupurlrole;
import com.iatb.services.SpiderWebBeanService;
import com.iatb.util.DateUtil;
import com.iatb.util.http.MyHtmlCleaner;

/**
 * ץȡWebbeanservice
 * ȡ������ץȡ��
 * ��������ץȡ�жϺ�����ץȡ�жϡ�
 * //TODO �鼮������ 
 * @author IATBFOREVER
 */
@Service("spiderwbService")
public class SpiderWebBeanServiceImpl implements SpiderWebBeanService {

	protected static Logger logger = Logger.getLogger(SpiderWebBeanServiceImpl.class);

	@Autowired
	private GroupurlroleDao groupurlroleDao;
	@Autowired
	private WebbeanDao webbeanDao;
	@Autowired
	private GroupurlnowDao groupurlnowDao;
	/**
	 * �Ƿ�����ץȡ
	 */
	private boolean isend=false;
	/**
	 * ץȡ�����Ѿ�����
	 */
	private boolean stopnew=false;
	/**
	 * �µ�wb
	 */
	private Webbean newwb;
	private Groupurlnow gun;
	public void spiderNormalWB() {
		logger.info("��ʼץȡ��ͨ�����Webbean");
		List<Groupurlrole> list=groupurlroleDao.getNormalGur();
		for (Groupurlrole gur : list) {
			isend=false;
			stopnew=false;
			newwb=null;
			String url[]=((String) gur.getUrl()).split(",");
			String u = "";
			int end=Integer.parseInt(url[2]);
			int next=Integer.parseInt(url[3]);
			//���ϴ���ͣ��ץȡ
			gun=groupurlnowDao.getwbgurid(gur.getId());
			isend=gun==null?false:gun.getIsEnd()==1?true:false;
			boolean bb=false;
			if(gun!=null&&gun.getEndurlnum()!=0&&gun.getIsEnd()==0){
				boolean bn=true;
				logger.info("��ʼ���ϴ���ͣ��ץȡ��ҳ��" + url[0].replace("(*)",gun.getEndurlnum()+"" ));
				for(int ei=gun.getEndurlnum();ei<end;ei+=next,bn=groupurlnowDao.updateWBGunEndNum(gur.getId(),ei)){
					if(!bn){
						logger.info("�����ϴ�ץȡҳ������ʧ�ܣ�ֹͣ��");
						return;
					}
					String uend = url[0].replace("(*)",ei+"" );
					bb=getUrl(gur,uend); 		// ��ȡurl
					if(!bb){
						logger.info("���ϴ���ͣ��ץȡ����GUR_ID:"+gur.getId()+"ץȡwb:"+u+"Ϊ�ջ���������������¸�wb");
						break;
					}
					if(ei==end-1)
						isend=true;
				}
				if(!bb)continue;
				end=gun.getEndurlnum();
				groupurlnowDao.updateWBGunisEnd(gun.getId());
			}else if(gun==null){
				gun=new Groupurlnow(0,0,gur.getId(),0,0,1,"","");
				groupurlnowDao.add(gun);
				gun=groupurlnowDao.getwbgurid(gur.getId());
			}
			boolean bn=true;
			int first=Integer.parseInt(url[1]);
			//������ץȡ���һ��ץȡ
			for (int i = first; i <= end; i += next) {
				if(gun.getIsEnd()==0){
					if(i>first){
						bn=groupurlnowDao.updateWBGunEndNum(gur.getId(),i);
						if(!bn){
							logger.info("������ץȡҳ������ʧ�ܣ�ֹͣ��");
							return;
						}
						if(i==end){
							isend=true;
							bn=groupurlnowDao.updateWBGunisEnd(gur.getId());
						}
					}
				}
				u = url[0].replace("(*)", String.valueOf(i));
				logger.info("��ʼץȡ��ҳ��" + u);
				bb=getUrl(gur,u); 		// ��ȡurl
				if(!bb){
					logger.info("������ץȡ���һ��ץȡ����GUR_ID:"+gur.getId()+"ץȡwb:"+u+"Ϊ�ջ���������������¸�wb");
					break;
				}
				if(stopnew)break;
			}
		}
		logger.info("������ȡץȡ����");
	}
	/**
	 * ��ȡ��ҳ�������
	 * @param url
	 */
	public  boolean getUrl(Groupurlrole gur,String url) {
		try {
			Map<String, String> m=new ConcurrentHashMap<String, String>();
			boolean b=false;
			String urlWebs[]=new String[]{};
			if(gur.getUrlWebs()!=null&&gur.getUrlWebs().trim().length()!=0){
				urlWebs=gur.getUrlWebs().split("[|]");
				if(urlWebs.length==2){
					b=true;
				}
				m = MyHtmlCleaner.getAllLink(url, gur.getEncode(),urlWebs[0]);
			}else{
				m = MyHtmlCleaner.getAllLink(url, gur.getEncode());
			}
			if(m==null||m.size()==0){
				groupurlnowDao.updateGunStatus(1,gur.getId());
				return false;
			}
			if(gur.getId()==30){
				logger.info("");
			}
			if(b){	//����urlwebs
				for (Map.Entry<String, String> s : m.entrySet()) {
					Map<String, String> m2 = MyHtmlCleaner.getAllLink(s.getValue(), gur.getEncode(),urlWebs[1]);
					checkSaveWB(gur, m2);
					if(stopnew)return true;
				}
			}else checkSaveWB(gur, m);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * ��gur�������ֱ���Webbean
	 * @param gur
	 * @param m
	 */
	private void checkSaveWB(Groupurlrole gur,Map<String,String> m ){
		List<Object[]> list=new ArrayList<Object[]>();
		String time=DateUtil.getPlusTimeDate();
		for (Map.Entry<String, String> s : m.entrySet()) {
			Webbean wb=groupurlroleDao.checkUrl(gur,s.getKey(), s.getValue());  	//����webbean
			if(wb!=null){
				if(newwb==null){
					newwb=wb;
					//����wb(ÿ�ζ�û������ץȡʱ����
					if(!isend/*||isend&&(gun.getTitle()==null||gun.getTitle().trim().equals(""))*/){
//						gun.setTitle(newwb.getTitle());gun.setUrl(newwb.getUrl());
						groupurlnowDao.updateGunwb(gur.getId(), newwb.getTitle(),newwb.getUrl(),isend);	
					}
				}
				if(isend){
					if(gun.getUrl().equals(wb.getUrl())){
//						if(!newwb.getUrl().equals(gun.getUrl()))
						groupurlnowDao.updateGunwb(gur.getId(), newwb.getTitle(),newwb.getUrl(),isend);	
						stopnew=true;break;	//����������ץȡ��
					}
				}
				list.add(new Object[]{gur.getId(),wb.getTitle(),wb.getUrl(),time,0});
			}
		}
		if(list.size()>0){
			stopnew=webbeanDao.batchSave(isend, list);list=null;
		}
		m=null;
	}
	
	
}
