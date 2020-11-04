package com.choa.s4.member.memberUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.choa.s4.member.MemberDTO;
import com.choa.s4.member.memberFile.MemberFileDAO;
import com.choa.s4.member.memberFile.MemberFileDTO;
import com.choa.s4.util.FileSaver;

@Controller
@RequestMapping("/member/**")
public class MemberUserController {
	@Autowired
	private MemberUserService memberUserService;
	

	
	//idcheck
	@GetMapping("memberIdCheck")
	public ModelAndView getMemberIdCheck(MemberDTO memberDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		memberDTO = memberUserService.getMemberIdCheck(memberDTO);
		
		int result = 1;//중복
		if(memberDTO == null) {
			result = 0;
		}
		mv.addObject("msg", result);
		mv.setViewName("common/ajaxResult");
		return mv;
		
	}
	
	//join
	@GetMapping("memberJoin")
	public ModelAndView setMemberJoin()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberJoin");
		return mv;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView setMemberJoin(MemberDTO memberDTO, MultipartFile photo,HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println(photo.getOriginalFilename());
		System.out.println(photo.getName());
		System.out.println(photo.getSize());
		System.out.println(photo.getContentType());
		int result = memberUserService.setMemberJoin(memberDTO, photo, session);

		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	//delete
	@GetMapping("memberDelete")
	public ModelAndView setMemberDelete(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		int result = memberUserService.setMemberDelete(memberDTO);
		session.invalidate();
		mv.setViewName("redirect:../");
		return mv;
	}
	
	//setMemberUpdate
	@GetMapping("memberUpdate")
	public ModelAndView setMemberUpdate()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberUpdate");
		return mv;
	}
	
	@PostMapping("memberUpdate")
	public ModelAndView setMemberUpdate(MemberDTO memberDTO, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberDTO s = (MemberDTO)session.getAttribute("member");
		memberDTO.setId(s.getId());
		
		int result = memberUserService.setMemberUpdate(memberDTO);
		
		if(result>0) {
			s.setName(memberDTO.getName());
			s.setEmail(memberDTO.getEmail());
			session.setAttribute("member", s);
		}
		
		mv.setViewName("redirect:./memberPage");
		
		return mv;
	}
	

	@GetMapping("memberPage")
	public ModelAndView getMemberPage(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		MemberFileDTO memberFileDTO = memberUserService.getOne(memberDTO);
		mv.addObject("file", memberFileDTO);

		mv.setViewName("member/memberPage");
		return mv;
	}

	@GetMapping("memberLogout")
	public ModelAndView getMemberLogout(HttpSession session)throws Exception{
		//웹브라우저 종료
		//일정시간 경과(로그인 후에 요청이 발생하면 시간이 연장)
		//memberDTO를 NULL로 대체
		//유지시간을 강제로 0으로 변경
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:../");
		return mv;
	}
	
	//getMemberLogin
	@GetMapping("memberLogin")
	public ModelAndView getMemberLogin()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberLogin");
		
		return mv;
	}
	
	@PostMapping("memberLogin")
	public ModelAndView getMemberLogin(MemberDTO memberDTO, String remember, HttpSession session,
			HttpServletResponse response)throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println("remember: "+remember);
		
		//remember가 null이 아니라면, id를 cookie 발행
		//cookie의 이름은 (remember) value는 로그인 할 때의 id
		
		if(remember != null) {
			Cookie cookie = new Cookie("remember", memberDTO.getId());
			cookie.setDomain("cookie/showCookie");
			response.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("remember", "");
			cookie.setDomain("member/memberLogin");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		memberDTO = memberUserService.getMemberLogin(memberDTO);
		
		if(memberDTO != null) {
			//index 페이지로 이동
			//redirect
			session.setAttribute("member", memberDTO);
			mv.setViewName("redirect:../");
			
		}else {
			//로그인 실패 메세지를 alert
			//로그인 입력 폼 으로 이동
			//foward
			mv.addObject("msg", "Login Fail");
			mv.addObject("path", "./memberLogin");
			mv.setViewName("common/result");
		}
		
		
		return mv;
	}
}
