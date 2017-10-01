package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;


@Controller
public class BoardController {
	
	@Autowired
	BoardRepository boardRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/{boardName}" ,method=RequestMethod.GET)
	public String showBoard(@PathVariable("boardName")String boardName,Model model) {
		System.out.println("+++++++++++++++++++++++++++++");
		
		Board board = boardRepository.findByName(boardName);
	
		if(board == null) {
			LOGGER.debug("Board Not Found :"+ boardName);
			model.addAttribute("boardName", boardName);
			return "newboard";
		}
		
		model.addAttribute("boardName", boardName);
		model.addAttribute("boardContent",board.getContents());
		
		return "board";
	}
	

}
