package main.java.bo;

import java.time.LocalDateTime;
import java.util.Date;

import main.java.util.MyUtil;

public class BaseBo {
	/**
	 * 将 日期对象（比如java.sql.Timestamp类型）转换为一个 LocalDateTime 对象 并返回
	 * @param date 日期时间对象
	 * @return 如果参数为null，则返回null。不为null则返回转换后的对象
	 */
	protected static LocalDateTime toLocalDateTime(Date date) {
		return MyUtil.toLocalDateTime(date);
	}
	/**
	 * 将 数值型的时间戳对象 转换为一个 LocalDateTime 对象 并返回
	 * @param timestamp 时间戳对象，如 从前端传来的 毫秒值
	 * @return 转换后的对象
	 */
	protected static LocalDateTime toLocalDateTime(long timestamp) {
		return MyUtil.toLocalDateTime(timestamp);
	}
}
