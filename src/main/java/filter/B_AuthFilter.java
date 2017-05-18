package main.java.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.bo.UserBo;
import main.java.constant.SessionAttributeConst;
import main.java.util.APIObject;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(description = "权限校验，拦截未登录用户的访问。考虑到执行优先级，所以在类名前加了 “B_”", urlPatterns = { "/api/*" })
public class B_AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public B_AuthFilter() {
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
		System.out.println("权限 Filter 校验：");
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		UserBo loginUser = (UserBo) session.getAttribute(SessionAttributeConst.LOGIN_USER_FIELD_NAME);
		
		if (loginUser == null) {
			System.out.println("权限校验【不通过】");
			
			APIObject apiObject = new APIObject("请先登录");
			
			response.getWriter().append(apiObject.toJSON());
			
			return;
		}

		System.out.println("权限校验【通过】，已登录用户名为：[" + loginUser.getUsername() + "]");
		
		// 保存快捷属性
		request.setAttribute(SessionAttributeConst.LOGIN_USER_FIELD_NAME, loginUser);
		request.setAttribute(SessionAttributeConst.LOGIN_USER_ID_FIELD_NAME, loginUser.getId());

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
