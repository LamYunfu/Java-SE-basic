# JVM类加载机制

![2fb054008ca2898e0a17f7d79ce525a1](C:\Users\蓝云甫\Desktop\材料\语言学习笔记\Java学习笔记\尚学堂300集\图片\2fb054008ca2898e0a17f7d79ce525a1.png)JVM类加载机制分为五个部分：加载，验证，准备，解析，初始化

## 加载:查找并加载类的二进制数据

 加载时类加载过程的第一个阶段，在加载阶段，虚拟机需要完成以下三件事情：

​    1、通过一个类的全限定名来获取其定义的二进制字节流。

​    2、将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。

​    3、在Java堆中生成一个代表这个类的java.lang.Class对象，作为对方法区中这些数据的访问入口。

​    相对于类加载的其他阶段而言，加载阶段（准确地说，是加载阶段获取类的二进制字节流的动作）是可控性最强的阶段，因为开发人员既可以使用系统提供的类加载器来完成加载，也可以自定义自己的类加载器来完成加载。

​    加载阶段完成后，虚拟机外部的 二进制字节流就按照虚拟机所需的格式存储在方法区之中，而且在Java堆中也创建一个java.lang.Class类的对象，这样便可以通过该对象访问方法区中的这些数据。

```java
加载.class文件的方式
– 从本地系统中直接加载
– 通过网络下载.class文件
– 从zip，jar等归档文件中加载.class文件
– 从专有数据库中提取.class文件
– 将Java源文件动态编译为.class文件
```

## 验证:确保被加载的类的正确性

验证是连接阶段的第一步，这一阶段的目的是为了确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。验证阶段大致会完成4个阶段的检验动作：

**文件格式验证**：验证字节流是否符合Class文件格式的规范；例如：是否以0xCAFEBABE开头、主次版本号是否在当前虚拟机的处理范围之内、常量池中的常量是否有不被支持的类型。

**元数据验证**：对字节码描述的信息进行语义分析（注意：对比javac编译阶段的语义分析），以保证其描述的信息符合Java语言规范的要求；例如：这个类是否有父类，除了java.lang.Object之外。

**字节码验证**：通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的。

**符号引用验证**：确保解析动作能正确执行。

验证阶段是非常重要的，但不是必须的，它对程序运行期没有影响，如果所引用的类经过反复验证，那么可以考虑采用-Xverifynone参数来关闭大部分的类验证措施，以缩短虚拟机类加载的时间。

##准备：为类的**静态变量**分配内存，并将其初始化为默认值

   准备阶段是正式为类变量分配内存并设置类变量初始值的阶段，这些内存都将在方法区中分配。对于该阶段有以下几点需要注意：

​    1、这时候进行内存分配的仅包括类变量（static），而不包括实例变量，实例变量会在对象实例化时随着对象一块分配在Java堆中。

​    2、这里所设置的初始值通常情况下是数据类型默认的零值（如0、0L、null、false等），而不是被在Java代码中被显式地赋予的值。

   假设一个类变量的定义为：public static int value = 3；

   那么变量value在准备阶段过后的初始值为0，而不是3，因为这时候尚未开始执行任何Java方法，而把value赋值为3的putstatic指令是在程序编译后，存放于类构造器<clinit>（）方法之中的，所以把value赋值为3的动作将在初始化阶段才会执行。

>· 这里还需要注意如下几点：
>· 对基本数据类型来说，对于类变量（static）和全局变量，如果不显式地对其赋值而直接使用，则系统会为其赋予默认的零值，而对于局部变量来说，在使用前必须显式地为其赋值，否则编译时不通过。
>· 对于同时被static和final修饰的常量，必须在声明的时候就为其显式地赋值，否则编译时不通过；而只被final修饰的常量则既可以在声明时显式地为其赋值，也可以在类初始化时显式地为其赋值，总之，在使用前必须为其显式地赋值，系统不会为其赋予默认零值。
>· 对于引用数据类型reference来说，如数组引用、对象引用等，如果没有对其进行显式地赋值而直接使用，系统都会为其赋予默认的零值，即null。
>· 如果在数组初始化时没有对数组中的各元素赋值，那么其中的元素将根据对应的数据类型而被赋予默认的零值。

3、如果类字段的字段属性表中存在ConstantValue属性，即同时被final和static修饰，那么在准备阶段变量value就会被初始化为ConstValue属性所指定的值。

   假设上面的类变量value被定义为： public static final int value = 3；

   编译时Javac将会为value生成ConstantValue属性，在准备阶段虚拟机就会根据ConstantValue的设置将value赋值为3。回忆上一篇博文中对象被动引用的第2个例子，便是这种情况。我们可以理解为static final常量在编译期就将其结果放入了调用它的类的常量池中



## 解析:把类中的符号引用转换为直接引用

解析阶段是指虚拟机将常量池中的符号引用替换为直接引用的过程。符号引用就是class文件中的：

- CONSTANT_Class_info
- CONSTANT_Field_info
- CONSTANT_Method_info

等类型的常量。

下面我们解释一下符号引用和直接引用的概念：

- 符号引用与虚拟机实现的布局无关，引用的目标并不一定要已经加载到内存中。各种虚拟机实现的内存布局可以各不相同，但是它们能接受的符号引用必须是一致的，因为符号引用的字面量形式明确定义在Java虚拟机规范的Class文件格式中。
- 直接引用可以是指向目标的指针，相对偏移量或是一个能间接定位到目标的句柄。如果有了直接引用，那引用的目标必定已经在内存中存在。

## 初始化

初始化阶段是类加载最后一个阶段，前面的类加载阶段之后，除了在加载阶段可以自定义类加载器以外，其它操作都由JVM主导。到了初始阶段，才开始真正执行类中定义的Java程序代码。

- 初始化，为类的静态变量赋予正确的初始值，JVM负责对类进行初始化，主要对类变量进行初始化。在Java中对类变量进行初始值设定有两种方式：

    ①**声明类变量是指定初始值**  public static int a = 10;

    ②**使用静态代码块为类变量指定初始值**    static{ ... }

   JVM初始化步骤

   1、假如这个类还没有被加载和连接，则程序先加载并连接该类

   2、假如该类的直接父类还没有被初始化，则先初始化其直接父类

   3、假如类中有初始化语句，则系统依次执行这些初始化语句

  类初始化时机：只有当对类的主动使用的时候才会导致类的初始化，类的主动使用包括以下六种：

  – 创建类的实例，也就是new的方式

  – 访问某个类或接口的静态变量，或者对该静态变量赋值

  – 调用类的静态方法

  – 反射（如Class.forName(“com.shengsiyuan.Test”)）

  – 初始化某个类的子类，则其父类也会被初始化

  – Java虚拟机启动时被标明为启动类的类（Java Test），直接使用java.exe命令来运行某个主类

注意以下几种情况不会执行类初始化：

- 通过子类引用父类的静态字段，只会触发父类的初始化，而不会触发子类的初始化。也就是只会初始化真正定义了这个字段的类。

```java
calss A extends B{
    static{
        System.out.println("类A被初始化")；
    }
}
calss B{
    public static int a = 10;
    static{
        System.out.println("类B被初始化")；
    }
}
```

当调用A.a的时候，只会初始化B，而不会初始化A。

- 定义对象数组，不会触发该类的初始化。Object[] object = new Object[10];
- **常量(final)**在编译期间会存入调用类的常量池中，本质上并没有直接引用定义常量的类，不会触发定义常量所在的类。final static int a = 10;
- 通过类名获取Class对象，不会触发类的初始化。  Class clazz = Text.class;
- 通过Class.forName加载指定类时，如果指定参数initialize为false时，也不会触发类初始化，其实这个参数是告诉虚拟机，是否要对类进行初始化。
- 通过ClassLoader默认的loadClass方法，也不会触发初始化动作。



# 深入类加载器（JVM类加载第一部分）

### 类加载器的作用

将Class文件字节码内容加载到内存当中，并将这些静态数据转换成方法区中运行时的数据结构，在堆中生成一个代表这个类的java.lang.Class对象，作为方法区类数据的访问入口。![d330251551f6de988239494ce2773095](C:\Users\蓝云甫\Desktop\材料\语言学习笔记\Java学习笔记\尚学堂300集\图片\d330251551f6de988239494ce2773095.png)

**注意：这里父类加载器并不是通过继承关系来实现的，而是采用组合实现的。**

站在Java虚拟机的角度来讲，只存在两种不同的类加载器：**启动类加载器**：它使用C++实现（这里仅限于Hotspot，也就是JDK1.5之后默认的虚拟机，有很多其他的虚拟机是用Java语言实现的），是虚拟机自身的一部分；所有其他的类加载器：这些类加载器都由Java语言实现，独立于虚拟机之外，并且全部继承自抽象类java.lang.ClassLoader，这些类加载器需要由启动类加载器加载到内存中之后才能去加载其他的类。

**ClassLoader:**

```java
作用：
* java.lang.classLoader类的基本职责就是根据一个指定类的名称，找到或者生成其对应的字节代码，然后从这些字节代码中定义出一个java类，即java.lang.Class的一个实例。
* 除此之外，ClassLoader还负责加载java应用所需的资源，如图像文件和配置文件等。
```



**（C++）启动类加载器**：Bootstrap ClassLoader，用于加载Java核心类库，负责加载存放在JDK\jre\lib(JDK代表JDK的安装目录，下同)下，或被-Xbootclasspath参数指定的路径中的，并且能被虚拟机识别的类库（如rt.jar，所有的java.*开头的类均被Bootstrap ClassLoader加载）。启动类加载器是无法被Java程序直接引用的。

**扩展类加载器**：Extension ClassLoader，该加载器由sun.misc.Launcher$ExtClassLoader实现，它负责加载DK\jre\lib\ext目录中，或者由java.ext.dirs系统变量指定的路径中的所有类库（如javax.*开头的类），开发者可以直接使用扩展类加载器。

**应用程序类加载器**：Application ClassLoader，该类加载器由sun.misc.Launcher$AppClassLoader来实现，它负责加载用户类路径（ClassPath）所指定的类，开发者可以直接使用该类加载器，如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。

应用程序都是由这三种类加载器互相配合进行加载的，如果有必要，我们还可以加入自定义的类加载器。因为JVM自带的ClassLoader只是懂得从本地文件系统加载标准的java class文件，因此如果编写了自己的ClassLoader，便可以做到如下几点：

1）在执行非置信代码之前，自动验证数字签名。

2）动态地创建符合用户特定需要的定制化构建类。

3）从特定的场所取得java class，例如数据库中和网络中。

**加载器结构测试：**

```java
public static void main(String[] main){
    System.out.println(ClassLoader.getSystemClassLoader());
    System.out.println(ClassLoader.getSystemClassLoader().getParent());
    System.out.println(ClassLoader.getSystemClassLoader().getParent());
}
```

运行后，输出结果：

```
sun.misc.Launcher$AppClassLoader@64fef26a
sun.misc.Launcher$ExtClassLoader@1ddd40f3
null     //Bootstrap ClassLoader，由于不是用Java写的，所以获取不到
```

## 类加载器的代理模式

* 代理模式

  交给其它加载器来加载指定的类

* 双亲委托机制

  * 就是某个特定的类加载器在接到加载类的亲求时，首先将加载任务委托给父类加载器，依次追溯，直到最高爷爷辈的，如果父类加载器可以完成类的加载任务，就返回成功；只有当父类加载器无法完成此加载任务时，才去自己加载。

  * 双亲委托机制就是为了保证Java核心库的类型安全。这种机制就保证不会出现用户自己能定义java.lang.Object类的情况,因为在这种情况下，这个类只会被上层的启动类加载器加载。
  * 类加载器除了用于加载类，也是安全的最基本保障。

* 并不是所有的类加载器使用的都是双亲委托机制，比如，tomcat加载器，它就是自己先加载这个类，如果自己加载不了的话，再交给它上层的类加载，这个与双亲委托机制是恰恰相反的。

>1、当AppClassLoader加载一个class时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。
>
>2、当ExtClassLoader加载一个class时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给BootStrapClassLoader去完成。
>
>3、如果BootStrapClassLoader加载失败（例如在$JAVA_HOME/jre/lib里未查找到该class），会使用ExtClassLoader来尝试加载；
>
>4、若ExtClassLoader也加载失败，则会使用AppClassLoader来加载，如果AppClassLoader也加载失败，则会报出异常ClassNotFoundException。

为什么需要双亲委派模型呢？假设没有双亲委派模型，试想一个场景：

> 黑客自定义一个`java.lang.String`类，该`String`类具有系统的`String`类一样的功能，只是在某个函数稍作修改。比如`equals`函数，这个函数经常使用，如果在这这个函数中，黑客加入一些“病毒代码”。并且通过自定义类加载器加入到`JVM`中。此时，如果没有双亲委派模型，那么`JVM`就可能误以为黑客自定义的`java.lang.String`类是系统的`String`类，导致“病毒代码”被执行。

而有了双亲委派模型，黑客自定义的`java.lang.String`类永远都不会被加载进内存。因为首先是最顶端的类加载器加载系统的`java.lang.String`类，最终自定义的类加载器无法加载`java.lang.String`类。

或许你会想，我在自定义的类加载器里面强制加载自定义的`java.lang.String`类，不去通过调用父加载器不就好了吗?确实，这样是可行。但是，在`JVM`中，判断一个对象是否是某个类型时，如果该对象的实际类型与待比较的类型的类加载器不同，那么会返回false。

举个简单例子：

> `ClassLoader1`、`ClassLoader2`都加载`java.lang.String`类，对应Class1、Class2对象。那么`Class1`对象不属于`ClassLoad2`对象加载的`java.lang.String`类型。

### java.lang.ClassLoader类API详细介绍

相关方法：

* **getParent()**      返回该类加载器的父类加载器
* loadClass(String name)    加载名称为name的类，返回的结果是java.lang.Class类的实例。
  * 此方法负责加载指定名字的类，首先会从已经加载的类中寻找，如果没有找到；从parent ClassLoader(ExtClassLoader)中加载；如果没有加载到，则从Bootstrap ClassLoader中尝试加载，如果还是加载失败，则抛出异常ClassNotFoundExcpetion。





# 自定义实现类加载器

   通常情况下，我们都是直接使用系统类加载器。但是，有的时候，我们也需要自定义类加载器。比如应用是通过网络来传输 Java 类的字节码，为保证安全性，这些字节码经过了加密处理，这时系统类加载器就无法对其进行加载，这样则需要自定义类加载器来实现。自定义类加载器一般都是继承自 ClassLoader 类，从上面对 loadClass 方法来分析来看，我们只需要重写 findClass 方法即可。

## 自定义类加载器的流程

* 继承：java.lang.ClassLoader
* 首先检查请求的类型是否已经被这个类装载器装载到命名空间中去了，如果已经装载，则直接返回；
* 委派类加载器请求给父类加载器，如果父类加载器能够完成，则返回父类加载器加载的Class实例；
* 否则调用本类加载器的findClass(...)方法，试图获取对应的字节码，如果获取得到，则调用defineClass(...)导入类型到方法区；如果获取不到对应的字节码或者其它原因的导入失败，返回异常给loadClass(...), loadClass(...)转抛异常，终止加载过程。
* **注意：被两个类加载器加载的同一个类，JVM不认为是相同的类。**
* 自定义加载器，将普通的类文件转换成二进制流

```java
public class FileSystemClassLoader extends ClassLoader{
	private String rootDir;
	
	public FileSystemClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);	 //查找在当前加载器中是否已经加载过这个类
		if(c != null) {
			return c;		//如果已经加载，就直接返回被加载的类
		}else {
			//获取当前类的父类加载器
			ClassLoader parent = this.getParent();
			//委派给父类进行加载
			c = parent.loadClass(name);
			if(c != null) {
				return c;
			}else {
				byte[] classData = getClassData(name);
				if(classData == null) {
					throw new ClassNotFoundException();
				}else {
					c = this.defineClass(name, classData, 0, classData.length);
				}
			}
		}
		return c;
	}
	
	private byte[] getClassData(String className){
		String path = this.rootDir + "/" + className.replace('.', '/') + ".class";
		InputStream is = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		try {	
			is = new FileInputStream(path);		
			int temp = 0;
			while((temp = is.read(buffer)) != -1) {
				baos.write(buffer, 0, temp);
			}
			return baos.toByteArray();
		} catch (FileNotFoundException e) {
			System.out.println("File not find!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
```

# 线程上下文类加载器

　　线程上下文类加载器（context class loader）是从 JDK 1.2 开始引入的。类 java.lang.Thread中的方法 getContextClassLoader()和 setContextClassLoader(ClassLoader cl)用来获取和设置线程的上下文类加载器。如果没有通过 setContextClassLoader(ClassLoader cl)方法进行设置的话，线程将继承其父线程的上下文类加载器。Java 应用运行的初始线程的上下文类加载器是系统类加载器。在线程中运行的代码可以通过此类加载器来加载类和资源。

　　前面提到的类加载器的代理模式并不能解决 Java 应用开发中会遇到的类加载器的全部问题。Java 提供了很多服务提供者接口（Service Provider Interface，SPI），允许第三方为这些接口提供实现。常见的 SPI 有 JDBC、JCE、JNDI、JAXP 和 JBI 等。这些 SPI 的接口由 Java 核心库来提供，如 JAXP 的 SPI 接口定义包含在 javax.xml.parsers包中。这些 SPI 的实现代码很可能是作为 Java 应用所依赖的 jar 包被包含进来，可以通过类路径（CLASSPATH）来找到，如实现了 JAXP SPI 的 Apache Xerces所包含的 jar 包。SPI 接口中的代码经常需要加载具体的实现类。如 JAXP 中的 javax.xml.parsers.DocumentBuilderFactory类中的 newInstance()方法用来生成一个新的 DocumentBuilderFactory的实例。这里的实例的真正的类是继承自 javax.xml.parsers.DocumentBuilderFactory，由 SPI 的实现所提供的。如在 Apache Xerces 中，实现的类是 org.apache.xerces.jaxp.DocumentBuilderFactoryImpl。而问题在于，SPI 的接口是 Java 核心库的一部分，是由引导类加载器来加载的；

**SPI 实现的 Java 类一般是由系统类加载器来加载的。引导类加载器是无法找到 SPI 的实现类的，因为它只加载 Java 的核心库。它也不能代理给系统类加载器，因为它是系统类加载器的祖先类加载器。也就是说，类加载器的代理模式无法解决这个问题。**

　　线程上下文类加载器正好解决了这个问题。如果不做任何的设置，Java 应用的线程的上下文类加载器默认就是系统上下文类加载器。在 SPI 接口的代码中使用线程上下文类加载器，就可以成功的加载到 SPI 实现的类。线程上下文类加载器在很多 SPI 的实现中都会用到。

参考文献：

https://www.cnblogs.com/ityouknow/p/5603287.html

http://www.importnew.com/25295.html