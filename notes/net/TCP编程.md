# TCP编程

UDP是使用的数据包来进行数据传输，但是TCP使用的是IO流来进行传输。

![20180808120145558](C:\Users\蓝云甫\Desktop\材料\语言学习笔记\Java学习笔记\尚学堂300集\图片\20180808120145558.jpg)

TCP分为客户端和服务端，其中：

* 客户端 
  * 创建Socket连接服务端(指定ip地址,端口号)通过ip地址找对应的服务器
  * 调用Socket的getInputStream()和getOutputStream()方法获取和服务端相连的IO流
  * 输入流可以读取服务端输出流写出的数据
  * 输出流可以写出数据到服务端的输入流
*  服务端 
  * 创建ServerSocket(需要指定端口号)
  * 调用ServerSocket的accept()方法接收一个客户端请求，得到一个Socket
  * 调用Socket的getInputStream()和getOutputStream()方法获取和客户端相连的IO流
  * 输入流可以读取客户端输出流写出的数据

### TCP服务器

* 1. 指定端口 使用ServerSocket创建服务器
 * 2. 阻塞式等待连接 accept
 * 3. 操作：输入输出流
 * 4. 释放资源

```java
	public static void main(String[] args) throws IOException {
		System.out.println("---Server---");
		// 1. 指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(9999);
		// 2. 阻塞式等待连接 accept,客户端不连会的话会一直阻塞下去
		Socket client = server.accept();
		System.out.println("一个客户端建立了连接");
		// 3. 操作：输入输出流
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String data = dis.readUTF();
		System.out.println(data);
		// 4. 释放资源
		dis.close();
		client.close();
	}
```



### TCP客户端

* 1. 使用Socket创建客户端，指定服务器的地址和端口
 * 2.操作：输入输出流操作
 * 3. 释放资源

```java
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---Client---");
		 //1. 使用Socket创建客户端，指定服务器的地址和端口
		Socket client = new Socket("localhost", 9999);
		 //2.操作：输入输出流操作
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		String data = "hello";
		dos.writeUTF(data);
		dos.flush();
		 //3. 释放资源
		dos.close();
	}
```



## TCP应用---文件上传

