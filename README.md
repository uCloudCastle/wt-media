# wt-media
多媒体微服务器

主要能力包括

1、基本实体增删查改操作

​		/ServerType   流媒体类型

​	   /Server             流媒体服务器

​	   /Camera			摄像头

2、流媒体API接口

​	1、萤石云API接口

​		GET:  /Ezviz/AccessToken?serverId=?  

​    2、海康开放平台接口

​		GET:  /Hikvision/PreviewURL?serverId=?&code=?

​				/Hikvision/CaremaOnline?serverId=?&code=?
