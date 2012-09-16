package com.iatb.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

/** ͳһ���ݷ��ʽӿ� */
public interface BaseDAO {
	/** ����ָ��ID�ĳ־û����� */
	public Object loadById(Class clazz,Serializable id);
	
	/** ����ָ��ID�ĳ־û����� */
	public void copyById(Class clazz,Serializable id);
	
	/** �������������ĳ־û����� */
	public Object loadObject(String hql);
	
	/** ɾ��ָ��ID�ĳ־û����� */
	public void delById(Class clazz,Serializable id);
	
	/** ��������ָ���ĳ־û����� */
	public void saveOrUpdate(Object obj);
	
	/** װ��ָ��������г־û����� */
	public List listAll(String clazz);
	
	/** ��ҳװ��ָ��������г־û����� */
	public List listAll(String clazz,int pageNo,int pageSize);
	/** ��ҳװ��ָ��������г־û����� */
	public List listAllBySql(String sqlNoOrder, int pageNo, int pageSize,String order ,String sortName);
	/** ͳ��ָ��������г־û����� */
	public int countAll(String clazz);
	
	/** ��ѯָ��������������ĳ־û����� */
	public List query(String hql);
	
	/** ��ҳ��ѯָ��������������ĳ־û����� */
	public List query(String hql,int pageNo,int pageSize);
	/**
	 * ��ȡ��ҳ����
	 * @param first
	 * @param rows
	 * @return
	 */
	public int getMaxid(String tablename, int first,int rows,String order ,String sortName);
	/** ͳ��ָ����Ĳ�ѯ��� */
	public int countQuery(String hql);
	
	/** ������������ */
	public int update(String hql);
	
	/** �����ӳ���ȡ��һ��JDBC���� */
	public Connection getConnection();
	/**
	 * ͨ��sql����ͳ��
	 * @return
	 */
	public int countBySql(final String sql) ;
	/**
	 * ����sql
	 * @param sql
	 * @return
	 */
	public int addSql(final String sql);
	/** ��ҳװ��ָ��������г־û����� */
	public List listAll(String clazz, int pageNo, int pageSize,String order ,String sortName) ;
}

