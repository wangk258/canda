package com.canda.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性工具类
 * @author kun.wang
 * @createDate 2016年12月14日 下午8:35:59
 */
public class PropertyUtil {
	
	private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);
	
	/**
	 * 加载属性文件
	 * @createDate 2016年12月14日 下午8:35:59
	 * @author kun.wang
	 * @param fileName
	 * @return
	 * @return Properties
	 */
	public static Properties loadProperty(String fileName) {
		Properties properties = null;
		InputStream is = null;
		try {
			is = new FileInputStream(new File(fileName));
			properties = new Properties();
			properties.load(new InputStreamReader(is, "UTF-8"));;
		} catch (IOException e) {
			log.error("property load error", e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				log.error("InputStream close failure", e);
			}
		}
		return properties;
	}
	
	/**
	 * 获取属性值
	 * @createDate 2016年12月14日 下午8:35:59
	 * @author kun.wang
	 * @param properties
	 * @param key
	 * @param defaultValue
	 * @return
	 * @return String
	 */
	public static Object get(Properties properties, String key, Object defaultValue) {
		if (properties != null && properties.contains(key)) {
			return properties.getProperty(key);
		}
		return defaultValue;
	}
	
	/**
	 * 获取字符串属性值
	 * @createDate 2016年12月14日 下午8:35:59
	 * @author kun.wang
	 * @param properties
	 * @param key
	 * @return
	 * @return String
	 */
	public static String getString(Properties properties, String key) {
		if (properties != null && properties.contains(key)) {
			return properties.getProperty(key);
		}
		return "";
	}
	
	/**
	 * 获取整形属性值
	 * @createDate 2016年12月14日 下午8:35:59
	 * @author kun.wang
	 * @param properties
	 * @param key
	 * @return
	 * @return Integer
	 */
	public static Integer getInteger(Properties properties, String key) {
		if (properties != null && properties.contains(key)) {
			return Integer.valueOf(properties.getProperty(key));
		}
		return 0;
	}
	
	/**
	 * 获取布尔属性值
	 * @createDate 2016年12月14日 下午8:35:59
	 * @author kun.wang
	 * @param properties
	 * @param key
	 * @return
	 * @return Integer
	 */
	public static Boolean getBoolean(Properties properties, String key) {
		if (properties != null && properties.contains(key)) {
			return Boolean.valueOf(properties.getProperty(key));
		}
		return false;
	}
	
	/**
	 * 获取双精度属性值
	 * @createDate 2016年12月14日 下午8:35:59
	 * @author kun.wang
	 * @param properties
	 * @param key
	 * @return
	 * @return Integer
	 */
	public static Double getDouble(Properties properties, String key) {
		if (properties != null && properties.contains(key)) {
			return Double.valueOf(properties.getProperty(key));
		}
		return 0.0;
	}

}
