$(function(){
	var lastIndex;
    $('#newsrule').datagrid({
        url:$("#ctx").val()+'/groupurlrole/view.do',
        title:'新闻采集规则管理',
        iconCls:'icon-save',
        width:970,
        height:560,
        striped: true,
        remoteSort: false,
        nowrap:true,
        pagination:true,
        rownumbers:true,
        singleSelect:false,
        idField:'id',
        loadMsg:'数据加载中，请耐心等待...',
        pageList:[19],
        columns:[[
            {field:'ck',checkbox:true},
            {field:'id',title:'序号',width:50,align:'center', sortable:true},
            {field:'name',title:'采集规则名称',editor:"text",align:'center', width:100, sortable:true,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,100)+"</span>";
                        }},
            {field:'url',title:'入口URL',editor:"text",align:'center', width:250,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,100)+"</span>";
                        }},
            {field:'titleLike',title:'titleLike',editor:"text",align:'center',width:100},
            {field:'titleUnLike',title:'titleUnLike',editor:"text",align:'center',width:100},
            {field:'urlWebs',title:'urlWebs',editor:"text",align:'center',width:100},
            {field:'urlLike',title:'urlLike',editor:"text",align:'center',width:150,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,150)+"</span>";
                        }},
            {field:'urlUnLike',title:'urlUnLike',editor:"text",align:'center',width:100},
            {field:'contentPre',title:'contentPre',editor:"text",align:'center',width:150,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,111)+"</span>";
                        }},
            {field:'contentEnd',title:'contentEnd',editor:"text",align:'center',width:100,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,11)+"</span>";
                        }},
            {field:'createTime',title:'createTime',editor:"text",align:'center',width:130},
            {field:'isNew',title:'isNew',editor:"text",align:'center',width:50},
            {field:'contentUnLike',title:'contentUnLike',editor:"text",align:'center',width:100,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,11)+"</span>";
                        }},
            {field:'siteid',title:'siteid',editor:"text",align:'center',width:50},
            {field:'encode',title:'encode',editor:"text",align:'center',width:100},
            {field:'webs',title:'webs',editor:"text",align:'center',width:100,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,11)+"</span>";
                        }},
            {field:'contentDel',title:'contentDel',editor:"text",align:'center',width:100,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,11)+"</span>";
                        }},
            {field:'isInner',title:'isInner',editor:"text",align:'center',width:50},
            {field:'innerWebs',title:'innerWebs',editor:"text",align:'center',width:100,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,11)+"</span>";
                        }},
            {field:'innerEnd',title:'innerEnd',editor:"text",align:'center',width:100,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,11)+"</span>";
                        }},
            {field:'hasCode',title:'hasCode',editor:"text",align:'center',width:50},
            {field:'isPic',title:'isPic',editor:"text",align:'center',width:50},
            {field:'status',title:'状态',align:'center', width:50,
                    formatter:function(value,rec){
                            return "<span title='"+(value=='1'?"1正常":value=='5'?"5限制":value+"其他")+"'>"+value+"</span>";
                        }
             }
        ]],
		onDblClickRow:function(rowIndex,rowData){
			$('#newsrule').datagrid('selectRow',rowIndex);
			$('#newsrule').datagrid('endEdit', lastIndex);
			rowdb=rowData;
			$('#newsrule').datagrid('beginEdit', rowIndex);
			lastIndex = rowIndex;
			console.log(1+JSON.stringify(row))
		},
		onClickRow:function(rowIndex,rowData){
			if (lastIndex != rowIndex&&rowdb!=null){
				$('#newsrule').datagrid('endEdit', lastIndex);
				var rows = $('#newsrule').datagrid('getChanges');
        		if(rows.length>0){
 					console.log(2+JSON.stringify(row))		//TODO bug row mvc
 					console.log(row.id)
 					console.log(rows[rows.length-1].id)
        			if(row.id!=rows[rows.length-1].id){
        				if(confirm("确认是否更新")){
		        			//TODO update 	rows[rows.length-1] 	mvc
        					
        				}
		        		row=rows[rows.length-1];
        			}else  {
        				console.log(3+JSON.stringify(row))
        				var rows = $('#newsrule').datagrid('getSelections');
		        		for (var i = 0; i < rows.length; i++) {
							if(rows[i].id==rowdb.id&&JSON.stringify(row)!=JSON.stringify(rowdb)){
		        				if(confirm("确认是否更新")){
									//TODO update 	rows[i] 	mvc
		        					
		        				}
		        				break;
							}
		        		}
        			}
        		}
			}
			rowdb=null;
		},
        toolbar:[{
            text:'新增采集规则',
            iconCls:'icon-add',
            handler:function(){
            	datagrid.option('add');
            }
        },{
            text:'删除',
            iconCls:'icon-cancel',
            handler:function(){
            	datagrid.option('delete');
        	  }
        },{
            text:'复制',
            iconCls:'icon-save',
            handler:function(){
                datagrid.option('copy');
            }
        },{
            text:'查看',
            iconCls:'icon-search',
            handler:function(){
                datagrid.option('view');
            }
        },{
            text:'修改',
            iconCls:'icon-edit',
            handler:function(){
                datagrid.option('edit');
            }
        },{
            text:'采集',
            iconCls:'icon-redo',
            handler:function(){
                datagrid.option('crawl');
            }
        },{
            text:'取消选择',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#newsrule').datagrid('clearSelections');
            }
        },{
            text:'w',
            iconCls:'icon-cancel',
            handler:function(){
            	location.href="w.jsp";
            }
        }
        ]
    });
    var p = $('#newsrule').datagrid('getPager');
    if (p){
        $(p).pagination({
            onBeforeRefresh:function(){
            	
            }
        });
    }
});
	var window={
		close:$('#w').window('close')
	};
	var datagrid={
		baseUrl: '/groupurlrole/',
		actionUrl:{"crawl":"rule_crawlNewsrule.action","edit":"rule_editNewsrule.action"
					,"view":"rule_viewNewsrule.action","copy":'copy.do'
					,"delete":"delete.do","add":"preAddNewsrule.action"},
		option:function(op){
			var p=false;
			if(op=='add'){
//				$('#w').window('open');
//				location.href="addNewsrule.jsp";
				return;
			}else if(op=='delete'||op=='copy'){
				p=true;
			}
			var ids ;
	        var rows = $('#newsrule').datagrid('getSelections');
	        if(rows.length==0)
	        {alert("请选择规则!");return;}
	        if(rows.length>1&&(op=='copy'||op=='edit'))		//TODO   选择多行操作 mvc
	        {alert("请只选择一个规则"+op+"!");return;}
	        if(p){
	        	if(confirm("确认"+op)){
	        		var ids=[];
	        		for (var i = 0; i < rows.length; i++) {
	        			ids.push(rows[i].id);
	        		}
	        		ids.join(",");
		            $.get( $("#ctx").val()+datagrid.baseUrl+datagrid.actionUrl[op]+'?id='+ids,null,function(d){
			            alert(d);
			            if(d.indexOf('删除成功')>=0){		
			            	for (var i =  rows.length-1; i >=0; i--) {
				            	var index = $('#newsrule').datagrid('getRowIndex', rows[i]);
				            	$('#newsrule').datagrid('deleteRow', index)
			        		}
			            }
			            if(d.indexOf('复制成功')>=0){			//TODO   选择多行复制操作 mvc
			            	if($('#newsrule').datagrid("getRows").length<19){
			            		$('#newsrule').datagrid();
			            	}
			            }
			        });
	        	}
	        }else{
	        	if(op=='edit'){
//	        		var rows = $('#tt').datagrid('getChanges');
//	        		if(rows.length==0){
//	        			alert("无需保存！");	
//	        		}else{
//	        			
//	        		}
//	        	 rows[i]	
//	        		$("#datagrid").wBox({
//					    requestType: "ajax",
//						target:"1.html"
//					   });
	        	}
//	            location.href='admin/'+datagrid.actionUrl[op]+'?id='+rows[0].id;
	        }
		}
	}
	var row={},rowdb={},index=false;
        function resize(){
            $('#newsrule').datagrid({
                title: 'New Title',
                striped: true,
                width: 900,
                queryParams:{
                    p:'param test',
                    name:'My Name'
                }
            });
        }
        function clearSelections(){
            $('#newsrule').datagrid('clearSelections');
        }
        function selectRow(){
            $('#newsrule').datagrid('selectRow',2);
        }
        function selectRecord(){
            $('#newsrule').datagrid('selectRecord','002');
        }
        function unselectRow(){
            $('#newsrule').datagrid('unselectRow',2);
        }
        