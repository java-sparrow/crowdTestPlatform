package main.java.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyUtil {
	/**
	 * 通过 指定的路径 获取 Properties 对象
	 * @param filePath 属性文件路径
	 * @return 属性对象，在该对象上可直接使用getProperty获取对应的属性值。
	 * 			<br>当发生异常时返回 null
	 */
	public static Properties getPropertyByPath(String filePath) {
		Properties prop = new Properties();
		
		try {
			InputStream inStream = new FileInputStream(filePath);
			
			prop.load(inStream);
			
			return prop;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
