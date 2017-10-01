package com.example.demo.repository;

import com.example.demo.VNoticeBoardApplication;
import com.example.demo.entity.Board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(VNoticeBoardApplication.class)
@SpringBootTest(classes=VNoticeBoardApplication.class)
//@DataJpaTest

@WebAppConfiguration
public class BoardRepositoryTest {

	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void findByNameTest() {
		
		Board board = new Board(1L, "name1", "password1", "contents1");
		boardRepository.save(board);
		System.out.println(boardRepository.findByName("name1"));
	}
}
