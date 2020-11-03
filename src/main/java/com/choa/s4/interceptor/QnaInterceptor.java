package com.choa.s4.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.choa.s4.member.MemberDTO;

public class QnaInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			System.out.println("Controller 진입 전");
			
			HttpSession session = request.getSession();
			MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
			
			return super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			System.out.println("Controller 진입 후");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
			System.out.println("JSP(View) 렌더링 후");
		super.afterCompletion(request, response, handler, ex);
	}

}
