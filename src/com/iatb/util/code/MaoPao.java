package com.iatb.util.code;

/**
 * √∞≈›À„∑®
 * @author Administrator
 */
public class MaoPao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int []a={1,43,2,65,3,6,9,29,43,36};
		a=getr(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+"\t");
		}
	}
	public static int[] getr(int []a){
		int k;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length-i-1; j++) {
				if(a[j]>a[j+1]){
					k=a[j];
					a[j]=a[j+1];
					a[j+1]=k;
				}
			}
		}
		return a;
	}

}
