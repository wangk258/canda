package com.canda.test.code;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class Decode {
	
	public static void main(String[] args) throws Exception {
		System.out.println("中文to Unicode="+chineseToUnicode("疲劳驾驶停车休息"));
		System.out.println("中文to Hex="+toChineseHex("疲劳驾驶停车休息"));
		System.out.println("中文to GBK=");
		printStandardMsg(chinese2GBKHex("0:成功,上传文件-文件上传成功"));
		
		System.out.println("GBK Hex to 中文="+GBK2Chinese(chinese2GBKHex("苏")));
		String msg = "30 3A B3 C9 B9 A6 2C C9 CF B4 AB CE C4 BC FE 2D CE C4 BC FE C9 CF B4 AB B3 C9 B9 A6 ";
		byte[] aa=blanktransfer2Bytes(msg.replaceAll(" ", ""));
		String chinese = new String(aa, "GBK");
		 
		System.out.println("中文：" + chinese);
	}

	public static void printStandardMsg(String msg) {
		char[] chars = msg.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
			if ((i + 1) % 2 == 0) {
				System.out.print(" ");
			}
		}
		System.out.println();
	}
	
	public static byte[] blanktransfer2Bytes(String msg2){
		msg2=msg2.toUpperCase();
		String msg_0500="";
		for(int j=0;j<msg2.length();j++){
			if(j%2==0){
				String tmp=msg2.substring(j, j+2);
				msg_0500=msg_0500+" "+tmp;
			}
		}
		msg_0500=msg_0500.substring(1);
		return transfer2Bytes(msg_0500);
	}
	
	public static byte[] transfer2Bytes(String input){
		System.out.println(input);
		String[] arrs = input.split(" ");
		byte[] bytes = new byte[arrs.length];
		for (int i = 0; i < arrs.length; i++) {
			bytes[i] = (byte)Integer.parseInt(arrs[i],16);
		}
		return bytes;
	}
	 
	public static String chinese2GBKHex(String chineseStr) throws Exception{
		StringBuffer GBKStr = new StringBuffer();
		byte[] GBKDecode = chineseStr.getBytes("gbk");
		for (byte b : GBKDecode) 
			GBKStr.append(Integer.toHexString(b&0xFF));
		return GBKStr.toString().toUpperCase();
	}
	
	public static String chineseToUnicode(String s){ 
		String as[] = new String[s.length()]; 
		String s1 = ""; 
		for (int i = 0; i < s.length(); i++){ 
			as[i] = Integer.toHexString(s.charAt(i) & 0xffff); 
			s1 = s1 + as[i]+"\t"; 
		}
		return s1; 
	}

	 
	public static String toChineseHex(String s){
		String ss = s;
		byte[] bt = ss.getBytes();
		String s1 = "";
		for (int i = 0; i < bt.length; i++){
			String tempStr = Integer.toHexString(bt[i]);
			if (tempStr.length() > 2)
			tempStr = tempStr.substring(tempStr.length() - 2);
			s1 = s1 + tempStr + " ";
		}
		return s1.toUpperCase();
	}
	
	//16进制GBK字符串转换成中文
	  public static String GBK2Chinese(String GBKStr)throws Exception{
		byte[] b = HexString2Bytes(GBKStr);
		String chineseStr = new String(b, "gbk");//输入参数为字节数组
		return chineseStr;
	   }
	  
	  //把16进制字符串转换成字节数组
	  public static byte[] HexString2Bytes(String hexStr) {
		 byte[] b = new byte[hexStr.length() / 2];
		 for (int i = 0; i < b.length; i++) 
	       b[i]=(byte) Integer.parseInt(hexStr.substring(2*i,2*i+2),16);
		return b;
	  }
}
