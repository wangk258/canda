package com.canda.common.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流工具类，用户流的读写
 * @author kun.wang
 * @createDate 2017年1月24日 上午10:20:03
 */
public class StreamUtil {
	
	private static final Logger log = LoggerFactory.getLogger(StreamUtil.class);
	
	/**
	 * 从输入流中获取字符串
	 * @createDate 2017年1月24日 上午10:31:03
	 * @author kun.wang
	 * @param is
	 * @return
	 * @return String
	 */
	public static String getString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			log.error("get string error", new RuntimeException(e));
		} finally {
			close(br);
		}
		return sb.toString();
	}
	
	/**
	 * 流关闭
	 * @createDate 2017年1月24日 上午10:30:10
	 * @author kun.wang
	 * @param closeable
	 * @return void
	 */
	private static void close(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			log.error("close stream error", new RuntimeException(e));
		}
	}

}
