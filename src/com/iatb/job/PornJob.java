package com.iatb.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.iatb.util.porn.PornUtil;
import com.iatb.util.porn.XDaoImpl;

@Service
public class PornJob {

//	@Scheduled(cron="0 6 * * * ?")
	public void getrealgfporn(){
		PornUtil.main(null);
	}

//	@Scheduled(cron="0 50 * * * ?")
	public void gethomesexdaily(){
		XDaoImpl.main(null);
	}
	
}
