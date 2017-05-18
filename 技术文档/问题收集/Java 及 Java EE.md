Java 及 Java EE 相关的问题
=========================

Java EE
-------

Q: 基于 XML 配置的 `Filter`（过滤器）很容易调整执行顺序，那基于注解方式的 `Filter` 应该怎么配置执行顺序呢？
> A: 由于 `Filter` 的执行顺序 跟 类名称字母顺序 有关，所以改变 `Filter` 的名字就可以达到目的了。
	参考 [基于注解方式的Filter执行顺序](http://blog.csdn.net/rnzuozuo/article/details/23108865)

Q: `Filter`（过滤器）中如何获取 `Session` 对象？
> A: `Filter` 的 `doFilter` 方法中，参数 `request` 对象是 `ServletRequest` 类型的，所以无法直接拿到 `Session` 对象。但可以先将 `ServletRequest` 类型的 `request` 对象 强制转换成 `HttpServletRequest` 类型，就能调用 `getSession()` 方法了。

