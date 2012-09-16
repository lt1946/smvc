drop table open163_course,open163_lecture,open163_openclass,open163_openclasstype,open163_type,open163_university;
CREATE TABLE `open163_university` (                                                                      
                      `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '主键',                                                                
                      `name` varchar(20) NOT NULL  COMMENT '名称',                                                      
                      `url` varchar(255) DEFAULT NULL COMMENT '网址',                                                      
                      `des` text COMMENT '介绍',                                                                           
                      `createTime` char(19) NOT NULL  COMMENT '创建时间',                                              
                      `status` tinyint(1) default 0  COMMENT '状态',                                                     
                      PRIMARY KEY (`id`) COMMENT '大学表'                                                                 
                    ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='大学表'; 

 CREATE TABLE `open163_type` (                                                                            
                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',                                                                
                `name` varchar(10) NOT NULL COMMENT '名称',                                                      
                `url` varchar(255) NOT NULL COMMENT '网址',                                                      
                `createTime` char(19) DEFAULT NULL COMMENT '创建时间',                                              
                `status` tinyint(1) DEFAULT 0 COMMENT '状态',                                                     
                PRIMARY KEY (`id`) COMMENT '类型表'                                                                 
              ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='类型表'  ;

CREATE TABLE `open163_openclasstype` (                                                                                     
                         `openClassId` int(11) NOT NULL COMMENT '公开课主键',                                                            
                         `typeId` int(11) NOT NULL COMMENT '类型主键',                                                                    
                         `createTime` char(19) DEFAULT NULL COMMENT '创建时间',                                                                
                         `status` tinyint(1) DEFAULT 0 COMMENT '状态'                                                                        
                       ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='公开课与类型关联表' ; 

CREATE TABLE `open163_openclass` (                                                                          
                     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',                                                                   
                     `name` varchar(255) NOT NULL COMMENT '名称',                                                        
                     `des` text COMMENT '课程介绍',                                                                        
                     `collageId` int(11) NOT NULL COMMENT '大学外键',                                                  
                     `lecturerId` int(11) NOT NULL COMMENT '讲师外键',                                                 
                     `courseNum` int(11) NOT NULL COMMENT '课程集数',                                                  
                     `openClassurl` varchar(255)NOT NULL COMMENT '课程网址',                                          
                     `downUrl` varchar(255) NOT NULL COMMENT '下载网址',                                               
                     `photoUrl` varchar(255) NOT NULL COMMENT '图片网址',                                              
                     `isOver` tinyint(4) DEFAULT 0 COMMENT '是否结课',                                                  
                     `createTime` char(19) DEFAULT NULL COMMENT '创建日期',                                                 
                     `status` tinyint(1) DEFAULT 0 COMMENT '状态',                                                        
                     PRIMARY KEY (`id`) COMMENT '网易公开课'                                                              
                   ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='公开课表' ; 

 CREATE TABLE `open163_lecture` (                                                                         
                   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',                                                                
                   `name` varchar(10) NOT NULL COMMENT '名字',                                                      
                   `universityID` int(11) NOT NULL COMMENT '大学外键',                                            
                   `des` text COMMENT '介绍',                                                                           
                   `photoUrl` varchar(255) NOT NULL COMMENT '图片网址',                                           
                   `createTime` char(19) DEFAULT NULL COMMENT '创建时间',                                              
                   `status` tinyint(1) DEFAULT 0 COMMENT '状态',                                                     
                   PRIMARY KEY (`id`) COMMENT '讲师表'                                                                 
                 ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='讲师表'  ;
CREATE TABLE `open163_course` (                                                                          
                  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',                                                                
                  `name` varchar(255) NOT NULL COMMENT '课程名称',                                               
                  `openClassId` int(11) NOT NULL COMMENT '公开课外键',                                          
                  `courseNo` int(1) NOT NULL COMMENT '课程集数',                                                 
                  `standardSize` varchar(10) NOT NULL COMMENT '标准版大小',                                     
                  `standardUrl` varchar(255) NOT NULL COMMENT '标准版下载地址',                               
                  `standarExt` varchar(10) NOT NULL COMMENT '标准版文件后缀',                                 
                  `mobileSize` varchar(10) NOT NULL COMMENT '手机版大小',                                       
                  `mobileUrl` varchar(255) NOT NULL COMMENT '手机版下载地址',                                 
                  `mobileExt` varchar(10) NOT NULL COMMENT '手机版文件后缀',                                  
                  `createTime` char(19) DEFAULT NULL COMMENT '创建日期',                                              
                  `status` tinyint(1) DEFAULT 0 COMMENT '状态',                                                     
                  PRIMARY KEY (`id`) COMMENT '状态'                                                                    
                ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='课程表'  ;
