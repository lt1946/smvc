package com.iatb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("column")
public class ColumnController {

	/** �������������Ŀ���� */
	public String browseColumns(){
//				actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"gbk");
//		columnsList = service.browseColumns();//����ҵ���߼����ȡ��������Ŀ�б�

		return "admin/column/browser";
	}
}
