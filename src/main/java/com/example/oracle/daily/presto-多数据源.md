## presto 摸鱼日记

### presto是什么东西OLAP

-（数据仓库的核心，联机分析处理），能够支持多个数据源的查询。数据源通过catalog 来新增。

### 安装。docker的形式。

- 镜像。docker pull prestosql/presto:latest  只有这个镜像我能够使用。

- docker run -it --name presto -p 8080:8080 prestosql/presto -d

### SQL server安装。docker形式。

- SQL server的镜像需要到docker hub上去搜索，用docker search出来的镜像有一点点问题。
- docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=MyPassWord123"  -p 1433:1433 --name sql1  -d mcr.microsoft.com/mssql/server:2017-latest
- 数据库的密码一定要复杂一点，不然启动不了。

###  