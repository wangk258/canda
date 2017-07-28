package com.canda.common.test;

import java.math.BigDecimal;

/**
 * BigDecimal测试
 * @author kun.wang
 * @createDate 2016年12月19日 上午11:27:15
 */
public class BigDecimalTest {
	
	public static void main(String[] args) {
		BigDecimal minutes = new BigDecimal(10);
		BigDecimal seconds = minutes.multiply(new BigDecimal(60));
		System.out.println(String.format("%dmin = %dsec", minutes.intValue(), seconds.intValue()));
	}

}
