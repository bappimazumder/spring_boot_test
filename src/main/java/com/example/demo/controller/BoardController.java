package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.RegistrationForm;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;


@Controller
public class BoardController {
	
	@Autowired
	BoardRepository boardRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/{register}",method=RequestMethod.POST)
	public String registerBoard(@Valid RegistrationForm registrationForm ,BindingResult result, WebRequest request,RedirectAttributes redirectAttribute) {
		
		request.getParameter("boardName");
		request.getParameter("password");
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			redirectAttribute.addAttribute("error","Password more than 3 character and less than 100 character");
			return "redirect:/"+ request.getParameter("boardName");
		}
		Board board = new Board();
		board.setName(request.getParameter("boardName"));
		board.setPassword(request.getParameter("password"));
		boardRepository.save(board);
		
		System.out.println("From Register Board");
		return "home";
	}
	
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
	
	@RequestMapping(value ="/{boardName}/edit", method = RequestMethod.GET)
	public String editBoard(@PathVariable("boardName") String boardName, Model model) {
		LOGGER.debug("Rendering board edit page: " + boardName);
		
		Board board = boardRepository.findByName(boardName);
		
		if( board == null) {
			LOGGER.debug("Board not found: " + boardName);
			
			return "home";
		}
		
		System.out.println("From edit");
		
		return "editboard";
	}
	
	@RequestMapping(value="/{boardName}/edit" ,method=RequestMethod.POST)
	public String doEditBoard(@PathVariable("boardName")String boardName, WebRequest request,RedirectAttributes redirectAttribute,Model model) {
		System.out.println("Rendering Board Edit Page "+boardName);
		
		Board board = boardRepository.findByName(boardName);
	
		if(board == null) {
			LOGGER.debug("Board Not Found :"+ boardName);			
			return "home";
		}	
		if(board.getPassword().equals(request.getParameter("password"))) {
			board.setContents(request.getParameter("content"));
			boardRepository.save(board);
			return "redirect: /" +request.getParameter(boardName);			
		}
		redirectAttribute.addAttribute("error", "Password Mismatch");
		System.out.println("From Edit");		
		return "redirect: /" +request.getParameter(boardName)+"/edit";	
	}	

}
