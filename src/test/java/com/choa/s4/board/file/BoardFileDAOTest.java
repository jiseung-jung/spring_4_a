package com.choa.s4.board.file;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.choa.s4.MyTestCase;
import com.choa.s4.board.notice.NoticeDAO;

public class BoardFileDAOTest extends MyTestCase {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	public void BoardFileDAOTest() throws Exception{
		BoardFileDTO boardFileDTO = new BoardFileDTO();
		boardFileDTO.setNum(2);
		boardFileDTO.setFileName("a.jpg");
		boardFileDTO.setOriName("a_a.jpg");
		
		int result = noticeDAO.setInsertFile(boardFileDTO);
		
		assertEquals(1, result);
	}

}
