package com.canda.common.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

/**
 * 类操作工具类
 * @author kun.wang
 * @createDate 2016年12月14日 下午8:35:59
 */
public class ClassUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);
	
	/**
	 * 加载类
	 * @createDate 2016年12月14日 下午8:35:59
	 * @author kun.wang
	 * @param className
	 * @param initialize 是否初始化该类，比如其静态方法、变量等
	 * @param loader
	 * @return
	 * @return Class<?>
	 */
	public static Class<?> loadClass(String className, Boolean initialize, ClassLoader loader) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className, initialize, loader);
		} catch (ClassNotFoundException e) {
			log.error("class load error", new RuntimeException(e));
		}
		return clazz;
	}
	
	/**
	 * 获取类加载器
	 * @createDate 2016年12月14日 下午8:35:59
	 * @author kun.wang
	 * @return
	 * @return ClassLoader
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * 获取包下的所有类
	 * @createDate 2016年12月14日 下午8:35:59
	 * @author kun.wang
	 * @param packageName
	 * @param initialize 是否初始化该类，比如其静态方法、变量等
	 * @return
	 * @return Set<Class<?>>
	 */
	public static Set<Class<?>> getClasses(String packageName, Boolean initialize) {
		Set<Class<?>> classSet = Sets.newHashSet();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replaceAll(".", File.separator));
			while (urls.hasMoreElements()) {
				URL url = (URL) urls.nextElement();
				if (url == null) {
					continue;
				}
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String packagePath = url.getPath().replaceAll("%20", "");
					addClass(classSet, packagePath, packageName, initialize);
				} else if ("jar".equals(protocol)) {
					JarURLConnection connection = (JarURLConnection)url.openConnection();
					if (connection == null) {
						continue;
					}
					JarFile jarFile = connection.getJarFile();
					if (jarFile == null) {
						continue;
					}
					Enumeration<JarEntry> entrys = jarFile.entries();
					while (entrys.hasMoreElements()) {
						JarEntry jarEntry = (JarEntry) entrys.nextElement();
						String jarEntryName = jarEntry.getName();
						if (StringUtils.isNotEmpty(jarEntryName) && jarEntryName.endsWith(".class")) {
							String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll(File.separator, ".");
							doAddClass(classSet, className, initialize);
						}
					}
				}
			}
		} catch (IOException e) {
			log.error("get class set error", e);
			throw new RuntimeException(e);
		}
		return classSet;
	}
	
	/**
	 * 添加class到Set
	 * @createDate 2016年12月19日 下午4:30:20
	 * @author kun.wang
	 * @param classSet
	 * @param packagePath
	 * @param packageName
	 * @param initialize 是否初始化该类，比如其静态方法、变量等
	 * @return void
	 */
	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName, Boolean initialize) {
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
			}
		});
		if (files == null || files.length <= 0) {
			return;
		}
		for (File file: files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if (StringUtils.isNotBlank(packageName)) {
					className = packageName + "." + className;
				}
				doAddClass(classSet, className, initialize);
			} else {
				String subPackagePath = fileName;
				if (StringUtils.isNotBlank(packagePath)) {
					subPackagePath = packagePath + File.pathSeparator + subPackagePath;
				}
				String subPackageName = fileName;
				if (StringUtils.isNotBlank(packageName)) {
					subPackageName = packageName + "." + subPackageName; 
				}
				addClass(classSet, subPackagePath, subPackageName, initialize);
			}
		}
	}
	
	/**
	 * 将class对象添加至Set
	 * @createDate 2016年12月19日 下午4:44:28
	 * @author kun.wang
	 * @param classSet
	 * @param className
	 * @param initialize 是否初始化该类，比如其静态方法、变量等
	 * @return void
	 */
	private static void doAddClass(Set<Class<?>> classSet, String className, Boolean initialize) {
		Class<?> clazz = loadClass(className, initialize, getClassLoader());
		if (classSet == null) {
			classSet = new HashSet<Class<?>>();
		}
		classSet.add(clazz);
	}

}
