# Java程序中使用正则表达式

* 相关类位于：java.util.regex包下面

   java.util.regex 包主要包括以下三个类：

- Pattern 类：

  pattern 对象是一个正则表达式的编译表示。Pattern 类没有公共构造方法。要创建一个 Pattern 对象，你必须首先调用其公共静态编译方法，它返回一个 Pattern 对象。该方法接受一个正则表达式作为它的第一个参数。

- Matcher 类：

  Matcher 对象是对输入字符串进行解释和匹配操作的引擎。与Pattern 类一样，Matcher 也没有公共构造方法。你需要调用 Pattern 对象的 matcher 方法来获得一个 Matcher 对象。

- PatternSyntaxException：

  PatternSyntaxException 是一个非强制异常类，它表示一个正则表达式模式中的语法错误。

```java
	public static void main(String[] args) {
		//在这个字符串fafsfl32flasdfkj23rfwe,是否符合指定的正则表达式： \w+
		//创建正则表达式对象
		Pattern p = Pattern.compile("\\w+");
		//创建Matcher对象
		Matcher m = p.matcher("fasdf2323safjlaks");
		
		//matches方法尝试将整个字符串和正则表达式进行匹配
		boolean result = m.matches();
		
		//find()方法扫描整个序列查找与该模式匹配的下一个子序列,可以重复多次调用
		boolean result2 = m.find();
		
		//group()方法返回找到的子序列
		String result3 = m.group();   //group(n) 返回正则表达式中的第n个分组;也就是第n个()包裹的部分
		
		/*
		 * 利用正则表达式进行复杂的字符串切割
		 */
		String str = "fasd23wew32sds32s";
		String[] strs = str.split("\\d+");
	}

```

# 利用正则表达式来进行网络爬虫

```java
public class WebSpider {
	/**
	 * 获得URL对应的网页源码的内容
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getURLContent(URL url) throws Exception{
		StringBuilder sb = new StringBuilder();
		//获得网络资源的一个输入流
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("gbk")));
		String temp = "";
		while((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		return sb.toString();
	}
	
	public static List<String> getMatherSubStrs(String destStr, String regexStr){
		List<String> result = new ArrayList<String>();
		Pattern p = Pattern.compile(regexStr);
		Matcher m  = p.matcher(destStr);
		while(m.find()) {
			result.add(m.group(1));
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		String destStr = getURLContent(new URL("http://www.163.com"));
		String regexStr = "href=\"([\\w\\s./:]+?)\"";
		List<String> result = getMatherSubStrs(destStr, regexStr);
				
	}
}
```

