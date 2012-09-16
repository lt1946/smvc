package com.iatb.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eportal.ORM.Newsrule;

/** ͳһ���ݷ��ʽӿ�ʵ�� */
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {
	
	/** ͳ��ָ��������г־û����� */
	public int countAll(String clazz) {
		final String hql = "select count(*) from "+clazz+ " as a";
		Long count = (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql);
				query.setMaxResults(1);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});	
		return count.intValue();
	}

	/** ͳ��ָ����Ĳ�ѯ��� */
	public int countQuery(String hql) {
		final String counthql = hql;
		Long count = (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(counthql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	/** ɾ��ָ��ID�ĳ־û����� */
	public void delById(Class clazz,Serializable id) {
		getHibernateTemplate().delete(getHibernateTemplate().load(clazz, id));			
	}

	/** װ��ָ��������г־û����� */
	public List listAll(String clazz) {
		return getHibernateTemplate().find("from "+clazz+" as a order by a.id desc");
	}
	
	/** ��ҳװ��ָ��������г־û����� */
	public List listAll(String clazz, int pageNo, int pageSize) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String hql = "from "+clazz+ " as a order by a.id desc";
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo-1)*pSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))Hibernate.initialize(result);
				return result;
			}
		});	
		return list;
	}
	
	/** ��ҳװ��ָ��������г־û����� */
	public List listAll(String clazz, int pageNo, int pageSize,String order ,String sortName) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String hql = "from "+clazz+ " order by "+sortName+" "+order;
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo-1)*pSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))Hibernate.initialize(result);
				return result;
			}
		});	
		return list;
	}	
	/** ��ҳװ��ָ��������г־û����� */
	public List listAllBySql(final String hqlNoOrder, final int pageNo, final int pageSize,String order ,String sortName) {
		final String hql = hqlNoOrder+"  order by "+sortName+" "+order ;
//		hqlNoOrder=hqlNoOrder.indexOf("where")>0?hqlNoOrder+" and ":hqlNoOrder+" where"; 
//		if(order.equals("desc")) {
//			hql = hqlNoOrder+" id>"+(count-pSize*(pageNo))+" and id <="+(count-pSize*(pageNo-1))+" order by "+sortName+" desc" ;
//		}else {
//			hql = hqlNoOrder+" id>="+(pNo-1)*pSize+" and id <"+(pNo)*pSize+" order by "+sortName ;
//		}
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql);
				query.setFirstResult((pageNo-1)*pageSize);
				query.setMaxResults(pageSize);
				query.setCacheable(true);
				List result = query.list();
				if (!Hibernate.isInitialized(result))Hibernate.initialize(result);
				return result;
			}
		});
		return list;
	}	
	/**
	 * ��ȡ��ҳ����
	 * @param first
	 * @param rows
	 * @return
	 */
	public int getMaxid(String tablename, int first,int rows,String order ,String sortName) {
		final String hql ="select a.id from (select id from newsrule  where id>"+first+" order by "+sortName+" "+order+" limit "+rows+")a order by a.id "+(order.equals("desc")?"asc":"desc")+" limit 1";
		return  (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				return  session.createSQLQuery(hql).uniqueResult();
			}
		});
	}
	/** ����ָ��ID�ĳ־û����� */
	public Object loadById(Class clazz,Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}
	
	/** ����ָ��ID�ĳ־û����� */
	public void copyById(final Class clazz,Serializable id) {
		Object o= getHibernateTemplate().get(clazz, id);
		if(o instanceof Newsrule) {
			int count = (Integer)getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session) throws HibernateException{
					Query query = session.createSQLQuery("select id from newsrule order by id desc limit 1");
					return query.uniqueResult();
				}
			});
			getHibernateTemplate().evict(o);
			((Newsrule)o).setId(count+1);
			getHibernateTemplate().save(o);
		}
	}
	
	/**
	 * ͨ��sql����ͳ��
	 * @return
	 */
	public int countBySql(final String sql) {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	/**�������������ĳ־û�����*/
	public Object loadObject(String hql) {
		final String hql1 = hql;
		Object obj = null;
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql1);
				return query.list();
			}
		});			
		if(list.size()>0)obj=list.get(0);	
		return obj;
	}

	/** ��ѯָ��������������ĳ־û����� */
	public List query(String hql) {
		final String hql1 = hql;
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql1);
				return query.list();
			}
		});	
	}

	/** ��ҳ��ѯָ��������������ĳ־û����� */
	public List query(String hql, int pageNo, int pageSize) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String hql1 = hql;
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql1);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo-1)*pSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))Hibernate.initialize(result);
				return result;
			}
		});	
	}

	/** ��������ָ���ĳ־û����� */
	public void saveOrUpdate(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	/** ��������ָ���ĳ־û����� */
	public void batch(final List<Object> obj) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				for (int i = 0; i < obj.size(); i++) {
					arg0.update(arg0);
					if((1+i)%40==0||i==(obj.size()-1)) {
						arg0.flush();
					}
				}
				return null;
			}
		});
	}

	/** ������������ */
	public int update(String hql) {
		final String hql1 = hql; 
		return ((Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql1);
				return query.executeUpdate();
			}
		})).intValue();	
	}
	
	/** �����ӳ���ȡ��һ��JDBC���� */
	public Connection getConnection() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession().connection();
	}

	public int addSql(final String sql) {
		return ((Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				return session.createSQLQuery(sql).executeUpdate();
			}
		})).intValue();	
	}
}