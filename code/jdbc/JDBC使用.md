# JDBC使用

**一、相关概念**

1.什么是JDBC

　　JDBC（Java Data Base Connectivity,java数据库连接）是一种用于执行SQL语句的Java API，可以为多种关系数据库提供统一访问，它由一组用Java语言编写的类和接口组成。JDBC提供了一种基准，据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序。

2.数据库驱动

　　我们安装好数据库之后，我们的应用程序也是不能直接使用数据库的，必须要通过相应的数据库驱动程序，通过驱动程序去和数据库打交道。其实也就是数据库厂商的JDBC接口实现，即对Connection等接口的实现类的jar文件。

![img](https://images2015.cnblogs.com/blog/928953/201608/928953-20160825093535448-70487578.png)

 

**二、常用接口**

1.Driver接口

　　Driver接口由数据库厂家提供，作为java开发人员，只需要使用Driver接口就可以了。在编程中要连接数据库，必须先装载特定厂商的数据库驱动程序，不同的数据库有不同的装载方法。如：

　　装载MySql驱动：Class.forName("com.mysql.jdbc.Driver");

　　装载Oracle驱动：Class.forName("oracle.jdbc.driver.OracleDriver");

2.Connection接口

　　Connection与特定数据库的连接（会话），在连接上下文中执行sql语句并返回结果。DriverManager.getConnection(url, user, password)方法建立在JDBC URL中定义的数据库Connection连接上。

　　连接MySql数据库：Connection conn = DriverManager.getConnection("jdbc:mysql://host:port/database", "user", "password");

![1551711232441](C:\Users\蓝云甫\AppData\Roaming\Typora\typora-user-images\1551711232441.png)

　　连接Oracle数据库：Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@host:port:database", "user", "password");

　　连接SqlServer数据库：Connection conn = DriverManager.getConnection("jdbc:microsoft:sqlserver://host:port; DatabaseName=database", "user", "password");

　　常用方法：

- - createStatement()：创建向数据库发送sql的statement对象。
  - prepareStatement(sql) ：创建向数据库发送预编译sql的PrepareSatement对象。
  - prepareCall(sql)：创建执行存储过程的callableStatement对象。
  - setAutoCommit(boolean autoCommit)：设置事务是否自动提交。
  - commit() ：在链接上提交事务。
  - rollback() ：在此链接上回滚事务。

3.Statement接口

　　用于执行静态SQL语句并返回它所生成结果的对象。

　　三种Statement类：

- - Statement：由createStatement创建，用于发送简单的SQL语句（不带参数）。
  - PreparedStatement ：继承自Statement接口，由preparedStatement创建，用于发送含有一个或多个参数的SQL语句。PreparedStatement对象比Statement对象的效率更高，并且可以防止SQL注入，所以我们一般都使用PreparedStatement。
  - CallableStatement：继承自PreparedStatement接口，由方法prepareCall创建，用于调用**存储过程**。

  ![1551712586418](C:\Users\蓝云甫\AppData\Roaming\Typora\typora-user-images\1551712586418.png)

```java
/**
statement利用connnection对象的createStatement方法创建Statement对象
然后利用statement对象的execute方法执行相应的sql语句
*/
Statement stmt = con.createStatement();
stmt.execute(sql);

/**
preparedStatement
先写好有占位符的sql语句，然后调用connnection对象的prepareStatement方法传入sql语句，对sql语句来进行预编译。随后调用preparedStatement对象的setXXX()方法将占位符的位置填上具体的数值，最后调用preparedStatement的execute方法来执行。
*/
sql = "insert into t_user (username, pwd)values(?, ?);";   //？占位符
PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, "John");			//参数索引从1开始计算
```



常用Statement方法：

- - execute(String sql):运行语句，返回是否有结果集 true/false
  - executeQuery(String sql)：运行select语句，返回ResultSet结果集。
  - executeUpdate(String sql)：运行insert/update/delete操作，返回更新的行数。
  - addBatch(String sql) ：把多条sql语句放到一个批处理中。
  - executeBatch()：向数据库发送一批sql语句执行。

4.ResultSet接口

　　ResultSet提供检索不同类型字段的方法，常用的有：

**获取ResultSet对象的内容一般是利用它的next方法逐步定位到数据表记录的每一行，然后通过该对象的getXXX(index)方法获取到该行的第index列的内容。**

![1551869900641](C:\Users\蓝云甫\AppData\Roaming\Typora\typora-user-images\1551869900641.png)

- - getString(int index)、getString(String columnName)：获得在数据库里是varchar、char等类型的数据对象。
  - getFloat(int index)、getFloat(String columnName)：获得在数据库里是Float类型的数据对象。
  - getDate(int index)、getDate(String columnName)：获得在数据库里是Date类型的数据。
  - getBoolean(int index)、getBoolean(String columnName)：获得在数据库里是Boolean类型的数据。
  - getObject(int index)、getObject(String columnName)：获取在数据库里任意类型的数据。

　　ResultSet还提供了对结果集进行滚动的方法：

- - next()：移动到下一行
  - Previous()：移动到前一行
  - absolute(int row)：移动到指定行
  - beforeFirst()：移动resultSet的最前面。
  - afterLast() ：移动到resultSet的最后面。

使用后依次关闭对象及连接：ResultSet → Statement → Connection

**在关闭的时候这三个对象的关闭一定要在三个不同的try catch块里面，以免一个关闭出现异常导致后面的对象不能关闭**

1.批处理Batch

![img](https://images2015.cnblogs.com/blog/928953/201609/928953-20160929090123000-1381809692.png)

```java
conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root", "root");

conn.setAutoCommit(false);	//设置为手动提交
stmt = conn.createStatement();
for(int i=0;i < 20000;i++){
    stmt.addBatch("insert into t_user(username, pwd, regTime)values("dsf","ss",now())")
;}
stmt.executeBatch();
conn.commit();       //提交数据
```

**四、事务（ACID特点、隔离级别、提交commit、回滚rollback）**

 ![img](https://images2015.cnblogs.com/blog/928953/201608/928953-20160826154351116-1941364953.png)

![img](https://images2015.cnblogs.com/blog/928953/201608/928953-20160826154554226-758567375.png)

 ![img](https://images2015.cnblogs.com/blog/928953/201609/928953-20160929090000797-712025301.png)

```java
 	conn.setAutoCommit(false); //JDBC中默认是true，自动提交事务
	ps1 = conn.prepareStatement("insert into t_user(userName,pwd)values(?,?)"); //事务开始
	ps1.setObject(1, "小高");
	ps1.setObject(2, "123");
	ps1.execute();
	System.out.println("第一次插入");
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	ps2 = conn.prepareStatement("insert into t_user(userName,pwd)values(?,?,?)");   //模拟执行失败(values的参数写成三个了)
	//insert时出现异常,执行conn.rollback
	ps2.setObject(1, "小张");
	ps2.setObject(2, "678");
	ps2.execute();
	System.out.println("第二次插入");    
	conn.commit();
```

在本次模拟过程中，由于第二次插入数据库操作出现了异常，此时在异常的捕获区域做事务的回滚操作，由于此时事务还没有提交，因此第一次插入的数据也会重新清除掉。

### JDBC时间处理

java.util.Date

* 子类：java.sql.Date     			表示年月日
* 子类：java.sql.Time                      表示时分秒
* 子类：java.sql.Timestamp           表示年月日时分秒

取出指定时间段的数据

```java
//将字符串表示的时间转换成long类型数值，格式是年月日时分秒
public long strToDate(String timeStr){
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    return format.parse(timeStr).getTime();
}
ps = conn.prepateStatement("select * from t_user where regtime>? and regtime<?");
java.sql.Date start = new java.sql.Date(strToDate("2019-03-08 12:00:00"));
java.sql.Date end = new java.sql.Date(strToDate("2019-04-20 12:00:00"));
ps.setObject(1, start);
ps.setObject(2, end);

rs = ps.executeQuery();
while(rs.next){
    System.out.println(rs.getString(1) + "...........");
}
```

**六、CLOB文本大对象操作**

 ![img](https://images2015.cnblogs.com/blog/928953/201609/928953-20160929091858735-1798271205.png)

```java
ps = conn.prepareedStatement("insert into t_user(username, myInfo)values(?, ?)");
ps.setString(1, "蓝云甫");
//clob类型的数据传入值可以以Reader流的形式传入
ps.setClob(2, new FileReader(new File("C://a.txt")));
ps.executeUpdate();

//取出数据库中的CLOB数据
ps = conn.preparedStatement("select * from t_user where id = ?");
ps.setString(1, 1001);
ResultSet rs = ps.executeQuery();
while(rs.next()){
    Clob c = rs.getClob("myInfo");
    Reader r = c.getCharacterStream();
    int temp = 0;
    while（(temp = r.read())！= -1）{
        System.out.println((char)temp);
    }
}
```

**七、BLOB二进制大对象的使用**

 ![img](https://images2015.cnblogs.com/blog/928953/201609/928953-20160929092306281-306062939.png)

### 八、java的properties资源配置文件

```
java中的properties文件是一种配置文件，主要用于表达配置信息。
文件类型为*.properties，格式为文本文件，文件内容是"键=值"的格式。
在properties文件中，可以用"#"来作注释
```

比如下面的mysql的连接资源配置文件

```
#MySQL连接配置
mysqlDriver=com.mysql.jdbc.Driver
mysqlURL=jdbc:mysql://localhost:3306/testjdbc
mysqlUser=root
mysqlPwd=mysql
```

配置文件的读取：

```java
static Properties pros = null;
static{
    //静态代码块，只有在这个类加载第一次的时候才会加载到  
    pros.load(Thread.currentThread.getContextClassLoader().getResourceAsStream("db.propert				ies"));
    String url = pros.getProperty("mysqlURL");
}
```



