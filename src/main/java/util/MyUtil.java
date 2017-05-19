package main.java.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
	
	/**
	 * 判断参数是否为空字符串
	 * @param string 需要判断的字符串
	 * @return 当字符串为null 或 字符串trim()之后的长度为0，则返回 true。其它情况返回 false。
	 */
	public static Boolean isEmptyString(String string) {
		if (string == null) {
			return true;
		}
		else if (string.trim().isEmpty()) {
			return true;
		}
		
		return false;
	}

	/**
	 * 本地默认时区。构造 LocalDateTime 对象时需要用到
	 */
	private static final ZoneId LOCAL_ZONE_ID = ZoneId.systemDefault();
	/**
	 * 接收一个 Date 日期时间对象，转换为一个 LocalDateTime 对象 并返回
	 * @param date 日期时间对象，如 从数据库中取得的 java.sql.Timestamp 类型
	 * @return 转换后的对象
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), LOCAL_ZONE_ID);
	}
	/**
	 * 接收一个 数值型的时间戳对象，转换为一个 LocalDateTime 对象 并返回
	 * @param timestamp 时间戳对象，如 从前端传来的 毫秒值
	 * @return 转换后的对象
	 */
	public static LocalDateTime toLocalDateTime(long timestamp) {
		return toLocalDateTime(new Date(timestamp));
	}
}
