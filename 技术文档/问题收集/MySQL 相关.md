MySQL 相关问题
==============

Q: JDBC 的 MySQL驱动 下载地址？
> A: 官网下载地址： [mysql-connector-java-5.1.42.zip](https://cdn.mysql.com//Downloads/Connector-J/mysql-connector-java-5.1.42.zip)

Q: JDBC数据库连接时 控制台提示 `Establishing SSL connection without server's identity verification is not recommended.` 怎么解决？
> A: 原因是MySQL在高版本需要指明是否进行SSL连接。解决方法：在mysql连接字符串url中加入ssl=true或者false即可，[详情请点这里](http://www.th7.cn/db/mysql/201603/178838.shtml)

Q: 用 `PrepareStatement` 执行SQL语句时， 控制台提示 `Parameter index out of range (1 > number of parameters, which is 0).` 是怎么回事？
> A: 第一种情况： SQL语句中没有 `?` 符号，却调用了 `preparedStatement.setXXX` 语句；  
	第二种情况：`?` 号被单引号包围，如 `insert into myTable (name, age) values ('?', '?')`；  
	第三种情况：检查 `?` 是否为英文半角符号。  
	参考自 [Parameter index out of range (1 > number of parameters, which is 0).](http://www.cnblogs.com/1020182600HENG/p/6097475.html)
