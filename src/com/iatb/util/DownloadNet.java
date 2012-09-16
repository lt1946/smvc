package com.iatb.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadNet {
	private File fileOut;
	private URL url;
	private long fileLength = 0;
	private boolean finish=false;
	// 初始化线程数
	private  int ThreadNum = 4; //5:41610  10:50594
	public DownloadNet(String urls,String savePath,String filename,int ThreadNum,boolean print) {
		this.ThreadNum=ThreadNum;
		Long s=System.currentTimeMillis();
		try {
			if(print) {
				System.out.println("正在链接URL");
			}
			url = new URL(urls);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			fileLength = urlcon.getContentLength();
			if (urlcon.getResponseCode() >= 400) {
				System.out.println("服务器响应错误");
				return;
			}
			if (fileLength <= 0)
				{
					System.out.println("无法获知文件大小");setFinish(true);return;
				}
			if(print) {
				printMIME(urlcon);
				System.out.println("文件大小为" + fileLength / 1024 + "K");
			}
			// 获取文件名
			String trueurl = urlcon.getURL().toString();
//			String filename = trueurl.substring(trueurl.lastIndexOf('/') + 1);
			String fn = filename==null?trueurl.substring(trueurl.lastIndexOf('/') + 1):filename;
			fileOut = new File(savePath,fn);
			if(!fileOut.exists())fileOut.createNewFile();
		} catch (MalformedURLException e) {
			System.err.println(e);return;
		} catch (IOException e) {
			System.err.println(e);return;
		}
		init(s,print);
	}
	public DownloadNet(String urls,String savePath,int ThreadNum,boolean print) {
		this.ThreadNum=ThreadNum;
		Long s=System.currentTimeMillis();
		try {
			if(print) {
				System.out.println("正在链接URL");
			}
			url = new URL(urls);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			fileLength = urlcon.getContentLength();
			if (urlcon.getResponseCode() >= 400) {
				System.out.println("服务器响应错误");
				System.exit(-1);
			}
			if (fileLength <= 0)
				System.out.println("无法获知文件大小");
			if(print) {
				printMIME(urlcon);
				System.out.println("文件大小为" + fileLength / 1024 + "K");
			}
			// 获取文件名
			String trueurl = urlcon.getURL().toString();
			String filename = trueurl.substring(trueurl.lastIndexOf('/') + 1);
			fileOut = new File(savePath,filename);
			if(!fileOut.exists())fileOut.createNewFile();
		} catch (MalformedURLException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
		init(s,print);
	}

	private void init(Long s,boolean print) {
		DownloadNetThread[] down = new DownloadNetThread[ThreadNum];
		try {
			long block = fileLength / ThreadNum + 1;
			for (int i = 0; i < ThreadNum; i++) {
				RandomAccessFile randOut = new RandomAccessFile(fileOut, "rw");
				randOut.setLength(fileLength);
				randOut.seek(block * i);
				down[i] = new DownloadNetThread(url, randOut, block, i + 1);
				down[i].setPriority(7);
				down[i].start();
			}
			// 循环判断是否下载完毕
			boolean flag = true;
			while (flag) {
				Thread.sleep(100);
				flag = false;
				for (int i = 0; i < ThreadNum; i++)
					if (!down[i].isFinished()) {
						flag = true;
						break;
					}
			}// end while
			if(print) {
				System.out.println("文件下载完毕，保存在" + fileOut.getPath());
				System.out.println(System.currentTimeMillis()-s);
			}
			setFinish(true);
		} catch (FileNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	private void printMIME(HttpURLConnection http) {
		for (int i = 0;; i++) {
			String mine = http.getHeaderField(i);
			if (mine == null)
				return;
			System.out.println(http.getHeaderFieldKey(i) + ":" + mine);
		}
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}

//	public static void main(String[] args) {
//		Long s=System.currentTimeMillis();
//		DownloadNet app = new DownloadNet();
//		System.out.println(System.currentTimeMillis()-s);
//	}
}
