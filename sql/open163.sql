drop table open163_course,open163_lecture,open163_openclass,open163_openclasstype,open163_type,open163_university;
CREATE TABLE `open163_university` (                                                                      
                      `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '����',                                                                
                      `name` varchar(20) NOT NULL  COMMENT '����',                                                      
                      `url` varchar(255) DEFAULT NULL COMMENT '��ַ',                                                      
                      `des` text COMMENT '����',                                                                           
                      `createTime` char(19) NOT NULL  COMMENT '����ʱ��',                                              
                      `status` tinyint(1) default 0  COMMENT '״̬',                                                     
                      PRIMARY KEY (`id`) COMMENT '��ѧ��'                                                                 
                    ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='��ѧ��'; 

 CREATE TABLE `open163_type` (                                                                            
                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',                                                                
                `name` varchar(10) NOT NULL COMMENT '����',                                                      
                `url` varchar(255) NOT NULL COMMENT '��ַ',                                                      
                `createTime` char(19) DEFAULT NULL COMMENT '����ʱ��',                                              
                `status` tinyint(1) DEFAULT 0 COMMENT '״̬',                                                     
                PRIMARY KEY (`id`) COMMENT '���ͱ�'                                                                 
              ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='���ͱ�'  ;

CREATE TABLE `open163_openclasstype` (                                                                                     
                         `openClassId` int(11) NOT NULL COMMENT '����������',                                                            
                         `typeId` int(11) NOT NULL COMMENT '��������',                                                                    
                         `createTime` char(19) DEFAULT NULL COMMENT '����ʱ��',                                                                
                         `status` tinyint(1) DEFAULT 0 COMMENT '״̬'                                                                        
                       ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='�����������͹�����' ; 

CREATE TABLE `open163_openclass` (                                                                          
                     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',                                                                   
                     `name` varchar(255) NOT NULL COMMENT '����',                                                        
                     `des` text COMMENT '�γ̽���',                                                                        
                     `collageId` int(11) NOT NULL COMMENT '��ѧ���',                                                  
                     `lecturerId` int(11) NOT NULL COMMENT '��ʦ���',                                                 
                     `courseNum` int(11) NOT NULL COMMENT '�γ̼���',                                                  
                     `openClassurl` varchar(255)NOT NULL COMMENT '�γ���ַ',                                          
                     `downUrl` varchar(255) NOT NULL COMMENT '������ַ',                                               
                     `photoUrl` varchar(255) NOT NULL COMMENT 'ͼƬ��ַ',                                              
                     `isOver` tinyint(4) DEFAULT 0 COMMENT '�Ƿ���',                                                  
                     `createTime` char(19) DEFAULT NULL COMMENT '��������',                                                 
                     `status` tinyint(1) DEFAULT 0 COMMENT '״̬',                                                        
                     PRIMARY KEY (`id`) COMMENT '���׹�����'                                                              
                   ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='�����α�' ; 

 CREATE TABLE `open163_lecture` (                                                                         
                   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',                                                                
                   `name` varchar(10) NOT NULL COMMENT '����',                                                      
                   `universityID` int(11) NOT NULL COMMENT '��ѧ���',                                            
                   `des` text COMMENT '����',                                                                           
                   `photoUrl` varchar(255) NOT NULL COMMENT 'ͼƬ��ַ',                                           
                   `createTime` char(19) DEFAULT NULL COMMENT '����ʱ��',                                              
                   `status` tinyint(1) DEFAULT 0 COMMENT '״̬',                                                     
                   PRIMARY KEY (`id`) COMMENT '��ʦ��'                                                                 
                 ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='��ʦ��'  ;
CREATE TABLE `open163_course` (                                                                          
                  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',                                                                
                  `name` varchar(255) NOT NULL COMMENT '�γ�����',                                               
                  `openClassId` int(11) NOT NULL COMMENT '���������',                                          
                  `courseNo` int(1) NOT NULL COMMENT '�γ̼���',                                                 
                  `standardSize` varchar(10) NOT NULL COMMENT '��׼���С',                                     
                  `standardUrl` varchar(255) NOT NULL COMMENT '��׼�����ص�ַ',                               
                  `standarExt` varchar(10) NOT NULL COMMENT '��׼���ļ���׺',                                 
                  `mobileSize` varchar(10) NOT NULL COMMENT '�ֻ����С',                                       
                  `mobileUrl` varchar(255) NOT NULL COMMENT '�ֻ������ص�ַ',                                 
                  `mobileExt` varchar(10) NOT NULL COMMENT '�ֻ����ļ���׺',                                  
                  `createTime` char(19) DEFAULT NULL COMMENT '��������',                                              
                  `status` tinyint(1) DEFAULT 0 COMMENT '״̬',                                                     
                  PRIMARY KEY (`id`) COMMENT '״̬'                                                                    
                ) ENGINE=InnoDB DEFAULT CHARSET=gbk CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='�γ̱�'  ;
