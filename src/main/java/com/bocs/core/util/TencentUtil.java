package com.bocs.core.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class TencentUtil {

	/**
	 * 自定义XStream, 用于处理返回微信的xml, 添加CDATA标签
	 */
	public static XStream xStreamForResponseData = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out, new XmlFriendlyNameCoder("-_",
					"_")) {
				// 是否增加CDATA标签
				boolean cdata = false;

				@Override
				public void setValue(String text) {
					if (null != text && !"".equals(text)) {
						// 浮点型判断
						Pattern patternInt = Pattern
								.compile("[0-9]*(\\.?)[0-9]*");
						// 整型判断
						Pattern patternFloat = Pattern.compile("[0-9]+");
						// 如果是整数或浮点数 就不要加[CDATA[]了
						if (patternInt.matcher(text).matches()
								|| patternFloat.matcher(text).matches()) {
							cdata = false;
						} else {
							cdata = true;
						}
					}
					super.setValue(text);
				}

				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 获取一定长度的随机字符串
	 * 
	 * @param length 指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 通过反射的方式遍历对象的属性和属性值，方便调试
	 * 
	 * @param o  要遍历的对象
	 * @throws Exception
	 */
	public static void reflect(Object o) throws Exception {
		Class<?> cls = o.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.setAccessible(true);
		}
	}

	public static byte[] readInput(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
		return out.toByteArray();
	}

	public static String inputStreamToString(InputStream is) throws IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}

	public static InputStream getStringStream(String sInputString) throws Exception {
		ByteArrayInputStream tInputStringStream = null;
		if (sInputString != null && !sInputString.trim().equals("")) {
			tInputStringStream = new ByteArrayInputStream(
					sInputString.getBytes("UTF-8"));
		}
		return tInputStringStream;
	}

	public static Object getObjectFromXML(String xml, Class<?> tClass) {
		// 将从API返回的XML数据映射到Java对象
		XStream xStreamForResponseData = new XStream();
		xStreamForResponseData.alias("xml", tClass);
		xStreamForResponseData.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
		return xStreamForResponseData.fromXML(xml);
	}

	public static String getStringFromMap(Map<String, Object> map, String key,
			String defaultValue) {
		if (key == "" || key == null) {
			return defaultValue;
		}
		String result = (String) map.get(key);
		if (result == null) {
			return defaultValue;
		} else {
			return result;
		}
	}

	public static int getIntFromMap(Map<String, Object> map, String key) {
		if (key == "" || key == null) {
			return 0;
		}
		if (map.get(key) == null) {
			return 0;
		}
		return Integer.parseInt((String) map.get(key));
	}

	/**
	 * 将Javabean转换为map
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> convertBeanToMap(Object bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (!key.equalsIgnoreCase("class")
						&& !key.equalsIgnoreCase("responseClass")) {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(bean);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}

	/**
	 * 读取本地的xml数据，一般用来自测用
	 * 
	 * @param localPath 本地xml文件路径
	 * @return 读到的xml字符串
	 */
	public static String getLocalXMLString(String localPath) throws IOException {
		return inputStreamToString(TencentUtil.class
				.getResourceAsStream(localPath));
	}


	/**
	 * 将xml数据转换为map
	 * 
	 * @param xmlString
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getMapFromXML(String xmlString) throws Exception{

		Map<String, Object> map = new HashMap<>();
		// 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
		DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = TencentUtil.getStringStream(xmlString);
		Document document = builder.parse(is);

		// 获取到document里面的全部结点
		NodeList allNodes = document.getFirstChild().getChildNodes();
		Node node;
		int i = 0;
		while (i < allNodes.getLength()) {
			node = allNodes.item(i);
			if (node instanceof Element) {
				map.put(node.getNodeName(), node.getNodeValue());
			}
			i++;
		}
		return map;
	}

	/**
	 * 转换Object为xml
	 * 
	 * @param xmlObj
	 * @return
	 */
	public static String convertObjToXml(Object xmlObj) {
		// 解决XStream对出现双下划线的bug
		XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8",
				new XmlFriendlyNameCoder("-_", "_")));
		xStreamForRequestPostData.alias("xml", xmlObj.getClass());
		return xStreamForRequestPostData.toXML(xmlObj);
	}

	/**
	 * 转换Object为带CDATA的xml
	 * 
	 * @param xmlObj
	 * @return
	 */
	public static String objToCDATAXml(Object xmlObj) {
		xStreamForResponseData.alias("xml", xmlObj.getClass());
		return xStreamForResponseData.toXML(xmlObj);
	}

	public static void main(String[] args) {

	/*	BaseResponseData responseData = new BaseResponseData();
		responseData.setReturn_code("SUCCESS");
		responseData.setReturn_msg("OK");
		System.out.println(objToCDATAXml(responseData));*/
	}
	
	public static String getLocalIP(){
		String localIP = "";
		try {
			InetAddress ip = InetAddress.getLocalHost();
			localIP = ip.getHostAddress();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return localIP;
	}
}
