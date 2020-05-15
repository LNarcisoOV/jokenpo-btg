package br.com.btg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.btg.constants.Message;
import br.com.btg.model.Input;
import br.com.btg.service.GameService;
import br.com.btg.service.InputService;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private InputService inputService;
	
	@Autowired
	private GameService gameService;

	@GetMapping("/play")
	public ResponseEntity<Object> play() {
		if(inputService.getAll() != null && !inputService.getAll().isEmpty()) {
			Input winner = gameService.play(inputService.getAll());
			return new ResponseEntity<Object>(winner, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(Message.EMPTY_INPUT_LIST, HttpStatus.OK);
		}
	}

}
