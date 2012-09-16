package com.iatb.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.iatb.dao.GroupurlroleDao;
import com.iatb.dao.WbcontentDao;
import com.iatb.dao.WebbeanDao;
import com.iatb.pojo.Groupurlrole;
import com.iatb.pojo.Wbcontent;
import com.iatb.pojo.Webbean;
import com.iatb.services.SpiderConentService;
import com.iatb.util.SpiderUtil;
import com.iatb.util.http.MyHtmlCleaner;

@Service("spiderContentService")
public class SpiderConentServiceImpl implements SpiderConentService {

	protected static Logger logger = Logger.getLogger(SpiderConentServiceImpl.class);

	@Autowired
	private GroupurlroleDao groupurlroleDao;
	@Autowired
	private WebbeanDao webbeanDao;
	@Autowired
	private WbcontentDao wbcontentDao;
	
	public void spiderNormalContent() {
		logger.info("��ʼץȡ��ͨ���������");
		List<Groupurlrole> list=groupurlroleDao.getNormalGur();
		for (Groupurlrole gur : list) {
			List<Webbean> wblist=webbeanDao.getList("select * from webbean where gurid=? and status=0", Webbean.class, new Object[]{gur.getId()});
			for (Webbean wb : wblist) {
					if(gur.getIsPic()==1){
						boolean sb=saveContentPic(gur,wb);
						if(sb){
							int i=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wb.getId()});
							logger.info("��״̬1��wb����id��"+wb.getId()+(i>0?"�ɹ�":"ʧ��"));
						}
					}else{
						try {
							if(gur.getInnerWebs()!=null&&MyHtmlCleaner.isInnerPage(wb.getUrl().trim(),gur.getEncode(),gur.getInnerWebs())){
								//ץȡ��ҳ��ʼҳ
								String c =getContent(gur,wb.getTitle(),wb.getUrl());
								if(c!=null&&!c.trim().equals("")){
									if(c.equals("404")){
										int k=webbeanDao.execute("update webbean set status=-4 where id=?", new Object[]{wb.getId()});
										logger.info("����ʼ��ҳ״̬-4��"+wb.getId()+(k>0?"�ɹ�":"ʧ��"));
										continue;
									}
									boolean sb=saveContent(gur,wb,c);
									logger.info("������ҳ��ʼҳ��wb����id���ݣ�"+wb.getId()+(sb?"�ɹ�":"ʧ��"));
									if(!sb)continue;
								}else{
									logger.info("ץȡ��ҳ��ʼҳwb����id���ݣ�"+wb.getId()+"Ϊ�գ�");
									int i=webbeanDao.execute("update webbean set status=-1 where id=?", new Object[]{wb.getId()});
									logger.info("��״̬-1��ץ��wb����id����Ϊ�ջ� �쳣��"+wb.getId()+(i>0?"�ɹ�":"ʧ��"));
									continue;
								}
								//��ȡ������ҳ����
								List<String> lists=MyHtmlCleaner.getLinksByWebs(wb.getUrl().trim(),gur.getEncode(),gur.getInnerWebs() );
								saveInnerContent(gur,lists,wb);
							}else{
								String c =getContent(gur,wb.getTitle(),wb.getUrl());
								if(c!=null&&!c.trim().equals("")){
									if(c.equals("404")){
										int k=webbeanDao.execute("update webbean set status=-4 where id=?", new Object[]{wb.getId()});
										logger.info("����ʼ��ҳ״̬-4��"+wb.getId()+(k>0?"�ɹ�":"ʧ��"));
										continue;
									}
									boolean sb=saveContent(gur,wb,c);
									if(sb){
										int i=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wb.getId()});
										logger.info("��״̬1��wb����id��"+wb.getId()+(i>0?"�ɹ�":"ʧ��"));
									}
								}else{
									logger.info("ץȡwb����id���ݣ�"+wb.getId()+"Ϊ�գ�");
									int i=webbeanDao.execute("update webbean set status=-1 where id=?", new Object[]{wb.getId()});
									logger.info("��״̬-1��ץ��wb����id����Ϊ�ջ� �쳣��"+wb.getId()+(i>0?"�ɹ�":"ʧ��"));
								}
							}
						} catch (Exception e) {
							logger.info("ץȡwb����id���ݣ�"+wb.getId()+"�쳣��");
							int i=webbeanDao.execute("update webbean set status=-1 where id=?", new Object[]{wb.getId()});
							logger.info("��״̬-1��ץ��wb����id�����쳣��"+wb.getId()+(i>0?"�ɹ�":"ʧ��"));
							continue;
						}
					}
			}
		}
	}
	/**
	 * ������ҳ����
	 * @param list
	 * @param title
	 */
	private boolean saveInnerContent(Groupurlrole gur,List<String> list, Webbean wb) {
		for (int i = 0; i <list.size(); i++) {
			String title=wb.getTitle()+"("+(i+1)+")";
			String url=list.get(i);
			int wbid=0;
			Webbean wb1= webbeanDao.get("select * from webbean where title=? and url=? ", Webbean.class, new Object[]{title,url});
			if(wb1!=null){
				if(wb1.getStatus()==1)continue;	//���ڼ���
				wbid=wb1.getId();
			}else{
				wbid=webbeanDao.addReturnId(new Webbean(gur.getId(),wb.getId(), title, url));
				if(wbid<1)return false;		//������ҳʧ�ܣ��˳���
			}
			String c=getContent(gur,title,url); 	//��ȡ��ҳ����
			if(c==null){
				logger.info("����wb����id_��"+(i+1)+"ҳ_��ҳ����Ϊ�գ�");return false;	//��ȡ��ҳ����ʧ�ܣ��˳���
			}
			int j=c.indexOf(gur.getInnerEnd().replace("{0}",""+list.size()));
			if(j>0)c=c.substring(0,j);
			if(c.trim().equals(""))return false;	//��ȡ��ҳ����Ϊ�գ��˳���
			Webbean w=new Webbean(/*i==0?wb1.getTitle():*/title,url);
			w.setId(wbid);
			boolean b=saveContent(gur,w,c);// ����
			logger.info("����wb����id_��ҳ���ݣ�"+wb.getId()+(b?"�ɹ�":"ʧ��"));
			if(b){
				int iw=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wbid});
				logger.info("����ҳ״̬1��wb.getId()��"+wbid+(iw>0?"�ɹ�":"ʧ��"));
				if(i==list.size()-1){
					int k=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wb.getId()});
					logger.info("����ʼ��ҳ״̬1��"+wb.getId()+(k>0?"�ɹ�":"ʧ��"));
					return true;
				}
//				if(iw<1)return false;				//������ҳwebbeanʧ�ܣ��˳���
			}else{
				return false;	//������ҳ����ʧ�ܣ��˳���
			}
		}
		return true;
	}
	/**
	 * ��ȡ����
	 * @param title
	 * @param url
	 * @return
	 */
	private String getContent(Groupurlrole gur,String title, String url) {
		try {
			String encode=gur.getEncode();
			String webs=gur.getWebs();
//			String contentUnLike=gur.getContentUnLike();
			String content=null;
			if(webs!=null){
				try {
					content=SpiderUtil.getContentParser(url,encode,webs,gur.getHasCode(),gur.getIsPic()==1).toString();
				} catch (Exception e) {
					System.out.println(e.getMessage()+":"+url);
					return null;
				}
			}else{
				content=SpiderUtil.getContentParser(url,encode,gur.getHasCode(),gur.getIsPic()==1).toString();
//				content=MyHtmlCleaner.getCode(url, encode);
			}
			if(content==null)return null;
			if(content.equals("404")){
				return content;
			}
			String contentPre=gur.getContentPre();
			String contentEnd=gur.getContentEnd();
			int first=0;
			int end=content.length();
			//�޸�Ĭ��ֵΪnull
			if(contentPre!=null&&contentEnd!=null&&!contentPre.trim().equals("")&&!contentEnd.trim().equals("")){
				String c[] = contentPre.split("[|]");
				String c2[] = contentEnd.split("[|]");
				int s=-1;
				for (int i = 0; i < c.length; i++) {
					int j=-1;
					if(s<(j=content.indexOf(c[i],s>-1?s:0))){
						s=j+c[i].length();
					}
				}
				if (s != -1) first = s ;
				s=-1;
				for (int i = 0; i < c2.length; i++) {
					int j=content.indexOf(c2[i]);
					s=(j>s)?(s!=-1?s:j):(j!=-1?j:s);
				}
				if(s!=-1)end=s;
				content= content.substring(first,end);
			}else if((contentPre==null||contentPre.trim().equals(""))&&contentEnd!=null&&!contentEnd.trim().equals("")){
				String c2[] = contentEnd.split("[|]");
				int s=-1;
				for (int i = 0; i < c2.length; i++) {
					int j=content.indexOf(c2[i]);
					s=(j>s)?(s!=-1?s:j):(j==-1?s:j);
				}
				if(s!=-1)end=s;
				content= content.substring(first,end);
			}else if(contentPre!=null&&!contentPre.trim().equals("")&&(contentEnd==null||contentEnd.trim().equals(""))){
				String c[] = contentPre.split("[|]");
				int s=-1;
				int len=0;
				for (int i = 0; i < c.length; i++) {
					int j=-1;
					if(s<(j=content.indexOf(c[i],s>-1?s:0))){
						s=j;
						len=c[i].length();
					}
				}
				if (s != -1) first = s + len;
				content= content.substring(first,end);
			}
//			System.out.println(MyString.utf8ToGbk(content.trim()));
//			System.out.println(MyString.UTF8ToGB(content.trim()).replace("?", ""));
			if(gur.getContentDel()!=null){
				String del[]=gur.getContentDel().split("[|]");
				for (int i = 0; i < del.length; i++) {
					String dd[]=del[i].split("~");
					if(dd.length==1){
							content=content.replace(del[i], "");
					}else{
						int j=-1;
						while((j=content.indexOf(dd[0]))>=0){
							int jj=-1;
							if((jj=content.indexOf(dd[1], j))<0)break;
							content=content.substring(0,j)+content.substring(jj+dd[1].length(),content.length());
						}
					}
				}
			}
			return content.trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**	
	 * ������ͼƬ������
	 * @param wb
	 */
	private boolean saveContentPic(Groupurlrole gur, Webbean wb){
		try {
			if(gur.getSiteid()==11){	//��չ
				List<String> list=new ArrayList<String>();
				HtmlCleaner cleaner = new HtmlCleaner();
				TagNode node=null;
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(wb.getUrl().trim()); 
				HttpResponse response;
				String  r = null;
				response = client.execute(httpget);
				if(null != response){
					HttpEntity entity = response.getEntity();
					r  = EntityUtils.toString(entity ,gur.getEncode());
					httpget.abort();
					client.getConnectionManager().shutdown(); 
				}
				node=cleaner.clean(r);
				Object[] ns = node.evaluateXPath("//div[@class=\"title fz18 white line24\"]");
				if(ns.length==0)return  false;
				String title=((TagNode)ns[0]).getText().toString();
				int i=title.indexOf("(1/");
				if(i!=-1){
					String num=title.substring(i+3,title.length()-1);
					int end=Integer.parseInt( num);
					if(end>1){
						String c=wb.getUrl().substring(wb.getUrl().lastIndexOf("."));
						for (int j = 0; j < end; j++) {
							list.add(wb.getUrl().substring(0,wb.getUrl().lastIndexOf("_")+1)+""+j+c);
						}
					}else{
						list.add(wb.getUrl());
					}
				}
				for (int j = 0; j <list.size(); j++) {
					String c =getContent(gur,wb.getTitle()+"("+j+1+")",list.get(j)); //��ȡ����
					if(c==null||c.trim().equals("")){
						logger.info("saveContentPic():		content is :null");return false;
					}
					saveContent(gur,new Webbean(j==0?wb.getTitle():wb.getTitle()+"("+(j+1)+")",list.get(j)),c);// ����
				}
			}else{
				String c =getContent(gur,wb.getTitle().trim(),wb.getUrl().trim());
				if(c==null)return false;
				saveContent(gur,wb,c);// ����
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * ��������
	 * @param wb
	 * @param c
	 */
	private boolean saveContent(Groupurlrole gur,Webbean wb, String c) {
		try {
			if(c==null||c.trim().equals(""))return false;
			Wbcontent cc=new Wbcontent();
			cc.setContent(c);
			cc.setCreateTime();
			cc.setStatus(0);
			cc.setWbid(wb.getId());
			String s=wbcontentDao.add(cc);
			logger.info("���content:��"+wb.getTitle()+(s.indexOf("�ɹ�")>=0?"��\t�ɹ�":"��\tʧ��"));
		} catch (DuplicateKeyException e) {
			logger.info("saveContent():\ttitle:<"+wb.getTitle()+">_�Ѿ�����!");
			int i=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wb.getId()});
			logger.info("�����Ѿ����ڵ�wb����id���ݣ�"+wb.getId()+(i>0?"�ɹ�":"ʧ��"));
			return false;
		}catch (Exception e) {
			logger.info("saveContent():\t"+e.getMessage());
			return false;

		}
		return true;
	}
}
