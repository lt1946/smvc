package com.iatb.util.code;

import java.util.Arrays;

/**
 * �����㷨����۰��㷨
 * @author Administrator
 */
public class ZheBan {

	public static void main(String[] args) {
		int []a={3,42,5,6,4,75,34,7,4,2,6,8,4,6,3,2,6,454,7};
		long s1=System.currentTimeMillis();
		a=MaoPao.getr(a);
		System.out.println("ð�ݺ�ʱ��"+(System.currentTimeMillis()-s1));
		System.out.println("ð�ݺ�����飺"+Arrays.toString(a));
		System.out.println("==========================");
		long s2=System.currentTimeMillis();
		int r=getr(a,75,0,a.length-1);
		System.out.println("�۰��ʱ��"+(System.currentTimeMillis()-s2));
		System.out.println("�۰���ҵ�75λ��:"+r);
		System.out.println("==========================");
		int []b={3,42,5,6,4,75,34,7,4,2,6,8,4,6,3,2,6,454,7};
		long s3=System.currentTimeMillis();
		Arrays.sort(b);
		System.out.println("Arrays�����ʱ��"+(System.currentTimeMillis()-s3));
		System.out.println("Arrays����"+Arrays.toString(b));
		System.out.println("==========================");
	}

	private static int getr(int[] a, int i, int j, int k) {
		if(j>k)return -1;
		int middle=(j+k)/2;
		if(a[middle]==i)return middle;
		if(a[middle]>i){
			return getr(a, i, j, middle-1);
		}else{
			return getr(a, i, middle+1, k);
		}
	}
}
