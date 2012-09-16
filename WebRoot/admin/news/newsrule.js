$(function(){
	var lastIndex;
    $('#newsrule').datagrid({
        url:$("#ctx").val()+'/groupurlrole/view.do',
        title:'���Ųɼ��������',
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
        loadMsg:'���ݼ����У������ĵȴ�...',
        pageList:[19],
        columns:[[
            {field:'ck',checkbox:true},
            {field:'id',title:'���',width:50,align:'center', sortable:true},
            {field:'name',title:'�ɼ���������',editor:"text",align:'center', width:100, sortable:true,
                    formatter:function(value,rec){
                    		return "<span title='"+value+"'>"+value.substring(0,100)+"</span>";
                        }},
            {field:'url',title:'���URL',editor:"text",align:'center', width:250,
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
            {field:'status',title:'״̬',align:'center', width:50,
                    formatter:function(value,rec){
                            return "<span title='"+(value=='1'?"1����":value=='5'?"5����":value+"����")+"'>"+value+"</span>";
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
        				if(confirm("ȷ���Ƿ����")){
		        			//TODO update 	rows[rows.length-1] 	mvc
        					
        				}
		        		row=rows[rows.length-1];
        			}else  {
        				console.log(3+JSON.stringify(row))
        				var rows = $('#newsrule').datagrid('getSelections');
		        		for (var i = 0; i < rows.length; i++) {
							if(rows[i].id==rowdb.id&&JSON.stringify(row)!=JSON.stringify(rowdb)){
		        				if(confirm("ȷ���Ƿ����")){
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
            text:'�����ɼ�����',
            iconCls:'icon-add',
            handler:function(){
            	datagrid.option('add');
            }
        },{
            text:'ɾ��',
            iconCls:'icon-cancel',
            handler:function(){
            	datagrid.option('delete');
        	  }
        },{
            text:'����',
            iconCls:'icon-save',
            handler:function(){
                datagrid.option('copy');
            }
        },{
            text:'�鿴',
            iconCls:'icon-search',
            handler:function(){
                datagrid.option('view');
            }
        },{
            text:'�޸�',
            iconCls:'icon-edit',
            handler:function(){
                datagrid.option('edit');
            }
        },{
            text:'�ɼ�',
            iconCls:'icon-redo',
            handler:function(){
                datagrid.option('crawl');
            }
        },{
            text:'ȡ��ѡ��',
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
	        {alert("��ѡ�����!");return;}
	        if(rows.length>1&&(op=='copy'||op=='edit'))		//TODO   ѡ����в��� mvc
	        {alert("��ֻѡ��һ������"+op+"!");return;}
	        if(p){
	        	if(confirm("ȷ��"+op)){
	        		var ids=[];
	        		for (var i = 0; i < rows.length; i++) {
	        			ids.push(rows[i].id);
	        		}
	        		ids.join(",");
		            $.get( $("#ctx").val()+datagrid.baseUrl+datagrid.actionUrl[op]+'?id='+ids,null,function(d){
			            alert(d);
			            if(d.indexOf('ɾ���ɹ�')>=0){		
			            	for (var i =  rows.length-1; i >=0; i--) {
				            	var index = $('#newsrule').datagrid('getRowIndex', rows[i]);
				            	$('#newsrule').datagrid('deleteRow', index)
			        		}
			            }
			            if(d.indexOf('���Ƴɹ�')>=0){			//TODO   ѡ����и��Ʋ��� mvc
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
//	        			alert("���豣�棡");	
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
        