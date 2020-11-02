package com.choa.s4.board.qna;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.choa.s4.board.BoardDTO;
import com.choa.s4.board.file.BoardFileDTO;
import com.choa.s4.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	
	@GetMapping("qnaUpdate")
	public ModelAndView setUpdate(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println("===qnaUpdate===");
		boardDTO = qnaService.getOne(boardDTO);
		
		mv.addObject("dto", boardDTO);
		mv.setViewName("board/boardUpdate");
		return mv;
	}
	
	//--------------------------------------------------------------------
	
	@PostMapping("summernoteDelete")
	public ModelAndView summernoteDelete(String file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		boolean result = qnaService.summernoteDelete(file, session);
		
		mv.addObject("mag", result);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	
	
	//--------------------------------------------------------------------
	
	@PostMapping("summernote")
	public ModelAndView summernote(MultipartFile file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		String fileName = qnaService.summernote(file, session);
		System.out.println(fileName);
		
		String name = session.getServletContext().getContextPath()+File.separator;
		name = name+"resources"+File.separator+"upload"+File.separator;
		name = name+"qna"+File.separator+fileName;
		
		mv.addObject("mag", fileName);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	
	//--------------------------------------------------------------------
	
	
	@GetMapping("fileDown")
	public ModelAndView fileDown(BoardFileDTO boardFileDTO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("board","qna");
		mv.addObject("fileDTO", boardFileDTO);
		mv.setViewName("fileDown");
		
		return mv;
	}
	
	//--------------------------------------------------------------------
	
	
	@PostMapping("qnaReply")
	public ModelAndView setReply(BoardDTO boardDTO)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setReply(boardDTO);
		
		String message = "Reply Write Fail";
		
		if(result>0) {
			message ="Reply Write Success";
		}
		
		mv.addObject("msg", message);
		mv.addObject("path", "./qnaList");
		
		mv.setViewName("common/result");
		
		return mv;
	}
	
	@GetMapping("qnaReply")
	public ModelAndView setReply()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardReply");
		mv.addObject("board", "qna");
		return mv;
	}
	
	//--------------------------------------------------------------------
	
	@GetMapping("qnaSelect")
	public ModelAndView getOne(BoardDTO boardDTO)throws Exception{
		ModelAndView mv = new ModelAndView();
		boardDTO = qnaService.getOne(boardDTO);
		
		if(boardDTO != null) {
			mv.setViewName("board/boardSelect");
			mv.addObject("dto", boardDTO);
			mv.addObject("board", "qna");
		}else {
			mv.setViewName("common/result");
			mv.addObject("msg", "No Data");
			mv.addObject("path", "./qnaList");
		}
		
		return mv;
				
	}
	
	//--------------------------------------------------------------------
	
	@PostMapping("qnaWrite")
	public ModelAndView setInsert(BoardDTO boardDTO, MultipartFile [] files, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setInsert(boardDTO, files, session);
		String message="Write Fail";
		if(result>0) {
			message ="Write Success";
		}
		
		mv.addObject("msg", message);
		mv.addObject("path", "./qnaList");
		
		mv.setViewName("common/result");
		
		return mv;
	}
	
	@GetMapping("qnaWrite")
	public ModelAndView setInsert()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardWrite");
		mv.addObject("board", "qna");
		return mv;
	}
	
	//--------------------------------------------------------------------
	
	@GetMapping("qnaList")
	public ModelAndView getList(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> ar = qnaService.getList(pager);
		
		BoardDTO boardDTO  = ar.get(0);
		QnaDTO qnaDTO = (QnaDTO)boardDTO;
		System.out.println(qnaDTO.getDepth());
		mv.addObject("board", "qna");
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");
		return mv;
	}
	
	
	
	

}
