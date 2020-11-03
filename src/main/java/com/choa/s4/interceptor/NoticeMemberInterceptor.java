package com.choa.s4.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.choa.s4.member.MemberDTO;

@Component
public class NoticeMemberInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
			HttpSession session = request.getSession();
			
			MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
			
			boolean check = false;
			if(memberDTO != null && memberDTO.getId().equals("admin")) {
				check=true;
			}
			
			request.setAttribute("mas", "권한이 필요합니다.");
			request.setAttribute("path", "../member/memberLigin");
			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/views/common/result");
			view.forward(request, response);
			
		return check;
	}

		//NoticeList, NoticeSelect 는 누구나 통과
	
	
	
}
