MySQL 相关问题
==============

Q: JDBC 的 MySQL驱动 下载地址？
> A: 官网下载地址： [mysql-connector-java-5.1.42.zip](https://cdn.mysql.com//Downloads/Connector-J/mysql-connector-java-5.1.42.zip)

Q: JDBC数据库连接时 控制台提示 `Establishing SSL connection without server's identity verification is not recommended.` 怎么解决？
> A: 原因是MySQL在高版本需要指明是否进行SSL连接。解决方法：在mysql连接字符串url中加入ssl=true或者false即可，[详情请点这里](http://www.th7.cn/db/mysql/201603/178838.shtml)
