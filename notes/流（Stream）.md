# 流（Stream）

* 分类
  * 按流的方向划分：输入流和输出流
  * 按处理数据单位分：字节流和字符流
  * 按功能分：结点流和处理流
* 所有流的类型都继承与*java.io*的四种抽象流类型
  * **InpuStream**(输入字节流)
  * **OutputStream**(输出字节流)
  * **Reader**(输入字符流)
  * **Writer**(输出字符流)
* **所谓的输入输出是站在程序的角度上来看的**



### 节点流和处理流

**结点流**：从 一个特定的数据源（节点）读写数据（如：文件，内存）

**处理流**：“连接”在已经存在的流（节点流或处理流）之上，通过对数据处理从而为程序提供更加强大的读写功能。



### InputStream

* 继承自InputStream的流都是用于向**程序**中输入数据的，且数据的单位为字节（8bit）

##### 基本方法

`int read() throws IOException `

* 读取一个字节并且以整数的形式返回（0~255）
* 如果返回-1表示已经读到输入流的末尾

`int read(byte[] buffer)`

* 读取一系列字节并且存储到一个数组buffer当中
* 返回**实际读取的字节数**，如果读取前已经到达输入流末尾则返回-1

`int read(byte[] buffer, int offset, int length)`

* 读取length个字节，并存储到一个字节数组buffer,读取位置从offset位置开始
* 返回**实际读取的字节数**，如果读取前已经到达输入流末尾则返回-1

`void close()`

* 关闭流释放内存资源

`long skip(long n)`

* 跳过n个字节不读，返回**实际跳过的字节数**



### OutputStream

* 继承自InputStream的流都是用于从**程序**中输出数据的，且数据的单位为字节（8bit）

**基本方法**

`void write(int b) throws IOException `

- 向输出流中写入一个字节数据，该字节数据为参数b的低8位

`void write(byte[] buffer)`

* 将一个字节类型的数组中的数据写入输出流

`void write(byte[] buffer, int offset, int length)`

- 将一个字节类型的数组中的从指定位置（offset）开始的length个字节写入到输出流
- 返回**实际读取的字节数**，如果读取前已经到达输入流末尾则返回-1

`void close()`

- 关闭流释放内存资源

`void flush()`

* 将输出流中**缓冲**的数据全部写出到目的地。
* 良好的编程方式应该是先写flush()后close



### Reader

* 继承自Reader的流都是用于向程序中输入数据，且数据的单位为字符（2个字节16bit）
* 用于像中文这样一个字符占两个字节的文字书写
* 相关方法与InputStream类似，只是操作的单位由字节变成了字符（byte --> char）



### Writer

* 将程序中的字符类型的数据写出到目的地，与OutputStream方法类似，只是操作的单位变成了字符
* 添加的两个方法

`void write(String string)`

`void write(String string, int offset, int length)`

实际上在后台调用了String类的*toCharArray*将字符串转换成一个字符数组，然后再写入。

