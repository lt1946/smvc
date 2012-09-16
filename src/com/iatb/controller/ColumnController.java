package com.iatb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("column")
public class ColumnController {

	/** 处理浏览新闻栏目请求 */
	public String browseColumns(){
//				actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"gbk");
//		columnsList = service.browseColumns();//调用业务逻辑组件取得新闻栏目列表

		return "admin/column/browser";
	}
}
