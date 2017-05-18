package main.java.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(
		description = "编码过滤器，因为需要提高执行优先级，所以在类名前加了 “A_” ", 
		urlPatterns = { "/*" })
public class A_EncodingFilter implements Filter {
	private static final String DEFAULT_CHARACTER_ENCODING = "utf-8";

    /**
     * Default constructor. 
     */
    public A_EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("经过 编码过滤器，正在设置编码为：" + DEFAULT_CHARACTER_ENCODING);
		
		response.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
		request.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
