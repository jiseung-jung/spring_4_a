package com.choa.s4.board.notice;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choa.s4.board.BoardDTO;
import com.choa.s4.board.BoardService;
import com.choa.s4.board.file.BoardFileDTO;
import com.choa.s4.util.FileSaver;
import com.choa.s4.util.Pager;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	

	@Override
	public int setInsert(BoardDTO boardDTO, MultipartFile [] files, HttpSession session) throws Exception {
		
		//파일을 HDD에 저장
		String path = session.getServletContext().getRealPath("/resources/upload/notice");
		System.out.println(path);
		
		File file = new File(path);
		String fileName="";
		
		//---------Sequence
		//boardDTO.setNum(noticeDAO.getNum());
		
		
		//---------Notice Insert
		int result = noticeDAO.setInsert(boardDTO);
		System.out.println("Num: "+boardDTO.getNum());

		
		//---------Notice File Insert
		for(MultipartFile multipartFile:files) {
			if(multipartFile.getSize() !=0) {
			fileName = fileSaver.saveCopy(file, multipartFile);
			System.out.println(fileName);
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setFileName(fileName);
			boardFileDTO.setOriName(multipartFile.getOriginalFilename());
			boardFileDTO.setNum(boardDTO.getNum());
			noticeDAO.setInsertFile(boardFileDTO);
			}
		}
		
		//----------------------------------------------
		
		
		
		return result;
	}

	@Override
	public int setUpdate(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDelete(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDTO> getList(Pager pager) throws Exception {
		pager.makeRow();
		pager.setTotalCount(noticeDAO.getCount(pager));
		pager.makePage();
		return noticeDAO.getList(pager);
	}

	@Override
	public BoardDTO getOne(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getOne(boardDTO);
	}

}
