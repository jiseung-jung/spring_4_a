package com.choa.s4.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choa.s4.member.MemberDTO;

/**
 * Servlet Filter implementation class NoticeFilter
 */
public class NoticeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public NoticeFilter() {
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
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		//notice List, notice Select는 누구나 접근가능
		//write update delete 는 로그인한 사람 중 id가 admin인 사람만 가능
		
		HttpServletRequest req = (HttpServletRequest)request;

		String uri = req.getRequestURI();
		String result = uri.substring(uri.lastIndexOf("/")+1);
		System.out.println(uri);
		System.out.println(result);
		
		boolean check = uri.equals("noticeList");
		if(!check) {
			check = uri.equals("noticeSelect");
		}
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		boolean admincheck=false;
		if(memberDTO != null && memberDTO.getId().equals("admin")) {
			admincheck = true;
		}
		
		if(check || admincheck) {
			chain.doFilter(request, response);
		}else {
			((HttpServletResponse)response).sendRedirect("../member/memberLogin");
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
