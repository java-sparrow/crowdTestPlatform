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

Q: 创建外键时，出现 `Cannot add foreign key constraint` 提示怎么解决？
> A: 可能由于 **主键勾选了 unsigned** 所致。来源：[百度贴吧 帖子8楼](http://tieba.baidu.com/p/2370913430)

Q: 创建外键时，出现 `Can't write; duplicate key in table` 提示怎么解决？
> A: 因为 `Foreign Key Name` 是不能重复的，所以检查 SQL语句中 `CONSTRAINT` 后面的名称是否已经在别的地方用过了。

Q: 如何获取 刚插入数据库的新记录的 自增id？
> A: 再执行一次 `preparedStatement.getGeneratedKeys()` ，然后从返回的 `ResultSet` 对象中获取。
	详细例子参见 [mysql java使用PreparedStatement插入数据并返回id的代码](http://blog.csdn.net/lishamao/article/details/7189751)

Q: 用 `PrepareStatement` 执行SQL语句时， 控制台提示 `Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate(), Statement.executeLargeUpdate() or Connection.prepareStatement().` 是怎么回事？
> A: `connection.prepareStatement(sql)` 需要加第二个参数，改为 `connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)` 就好了。  
	详情参见 [MYSQL驱动包升级到5.1.17版本之后会出现的问题：Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.execu...](http://blog.csdn.net/shootyou/article/details/6023428)。  
	另外，官方回复可以见 [这里](https://bugs.mysql.com/bug.php?id=41448)

