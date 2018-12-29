# UDP编程

### 基本概念

* **DatagramPacket**:数据包

>该类表示数据报包。
>
>
>
>数据报包用于实现无连接分组传送服务。 仅基于该数据包中包含的信息，每个消息从一台机器路由到另一台机器。 从一台机器发送到另一台机器的多个分组可能会有不同的路由，并且可能以任何顺序到达。 包传送不能保证。

```java
DatagramPacket(byte[] buf, int length)
```

构造一个 `DatagramPacket`用于接收长度的数据包 `length` 。

* **DatagramSocket**:用于发送或者接收数据包的套接字

>此类表示用于发送和接收数据报数据包的套接字。
>
>w
>
>数据报套接字是分组传送服务的发送或接收点。 在数据报套接字上发送或接收的每个数据包都被单独寻址和路由。 从一个机器发送到另一个机器的多个分组可以不同地路由，并且可以以任何顺序到达。

```java
DatagramSocket(int port)
```

构造数据报套接字并将其绑定到本地主机上的指定端口。

`send(DatagramPacket p)`从此套接字发送数据报包。

`receive(DatagramPacket p)`从此套接字接收数据报包。

### 发送端

* 1. 使用DatagramSocket 指定端口 创建发送端
 * 2. 准备数据 注意必须转换成字节数组
 * 3. j将数据封装成DatagramPacket包裹，指定目的地和端口
 * 4. 发送包裹 set(DatagramPacket p)
 * 5. 释放资源

```java
public static void main(String[] args) throws Exception {
		System.out.println("发送方启动中");
		DatagramSocket client = new DatagramSocket(8888);
		String data = "蓝云甫";
		byte[] datas = data.getBytes();
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
				new InetSocketAddress("localhost",9999));
		client.send(packet);
		client.close();
}
```

### 接收端

 * 1. 使用DatagramSocket 指定端口 创建接收端
 * 2. 准备容器 封装成DatagramSocket 包裹
 * 3. 阻塞式接收包裹recieve(DatagramSocket p) 阻塞等待接收
 * 4. 分析数据  byte[] getData();
 * 5. 释放资源

```java
public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("接收方启动中");
		//使用DatagramSocket 指定端口 创建接收端
		DatagramSocket server = new DatagramSocket(9999);
		//准备容器 封装成DatagramSocket 包裹
		byte[] container = new byte[1024 * 60];
		DatagramPacket packet = new DatagramPacket(container,0,container.length);
		//阻塞式接收包裹recieve(DatagramSocket p)
		server.receive(packet);
		//分析数据  byte[] getData();
		byte[] datas = packet.getData();
		System.out.println(new String(datas));
		//释放资源
		server.close();
}
```

### 传输数据使用Data流

**将其它类型的数据转化成字节输出流**

```java
ByteArrayOutputStream baos = new ByteArrayOutputStream();
DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
//操作类型 + 数据
dos.writeUTF("编码");
dos.writeInt(18);
dos.writeBoolean(false);
dos.writeChar('a');
dos.flush();
byte[] datas = baos.toByteArray();
```

**获取流中数据**

```java
byte[] datas = packet.getData();
int len = packet.getLength();
DataInputStream dis = new DataInputStream(new BufferedInputStream(new 		   		                   ByteArrayInputStream(datas, len)));
//顺序与写出一致
String msg = dis.readUTF();
int age = dis.readInt();
boolean flag = dis.readBoolean();
char ch = dis.readChar();
```



### 传输对象使用Object流（传输对象类首先需要序列化）

`ObjectInputStream`

`ObjectOutputStream`



### 传输图片（文件）

将文件转化成字节数组



## 利用多线程实现双向聊天

### 发送端类

```java
package com.ouc.lucas.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
/**
 * 发送端类
 * @author 蓝云甫
 *
 */
public class TalkSend implements Runnable{
	private DatagramSocket client;
	private BufferedReader reader;
	private String toIP;
	private int toPort;
	public TalkSend(int port, String toIP, int toPort){
		try {
			this.client = new DatagramSocket(port);
			//从控制台获取数据
			this.reader = new BufferedReader(new InputStreamReader(System.in));
			this.toIP = toIP;
			this.toPort = toPort;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			String data;
			try {
				data = reader.readLine();
				byte[] datas = data.getBytes();
				DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
						new InetSocketAddress(this.toIP,this.toPort));
				client.send(packet);
				if("bye".equals(data)) {
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		client.close();
	}

}

```



### 接收端类

```java
package com.ouc.lucas.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkRecive implements Runnable{
	private DatagramSocket server;
	private String from;
	public TalkRecive(int localPort, String from) {
		this.from = from;
		try {
			this.server = new DatagramSocket(localPort);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true) {
			//准备容器 封装成DatagramSocket 包裹
			byte[] container = new byte[1024 * 60];
			DatagramPacket packet = new DatagramPacket(container,0,container.length);
			//阻塞式接收包裹recieve(DatagramSocket p)
			try {
				server.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//分析数据  byte[] getData();
			byte[] datas = packet.getData();
			System.out.println(this.from + "说：" + new String(datas));
			//释放资源
			if(new String(datas).equals("bye")) {
				break;
			}
		}
		server.close();
	}

}

```



### 老师实例

```java
package com.ouc.lucas.udp;
/**
 * 加入多线程，实现双向交流，模拟在线咨询
 * @author 蓝云甫
 *
 */
public class TalkStudent {

	public static void main(String[] args) {
		//创建学生发送线程
		new Thread(new TalkSend(7777, "localhost", 9999)).start();
		
		//创建学生接收线程
		new Thread(new TalkRecive(8888, "老师")).start();
	}

}

```



### 学生实例

```java
package com.ouc.lucas.udp;

public class TalkTeacher {

	public static void main(String[] args) {
		//创建老师接收端线程
		new Thread(new TalkRecive(9999, "学生")).start();
		
		//创建老师发送线程
		new Thread(new TalkSend(6666, "localhost", 8888)).start();
	}

}
```







