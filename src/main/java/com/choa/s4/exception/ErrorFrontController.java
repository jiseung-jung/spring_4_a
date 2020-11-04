package com.choa.s4.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error/**")
public class ErrorFrontController {
	
	public ModelAndView error404() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "존재하지 않는 페이지 입니다.");
		mv.setViewName("error/error_front");
		
		return mv;
	}

}
