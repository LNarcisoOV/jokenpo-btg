package br.com.btg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.btg.constants.Message;
import br.com.btg.exception.ValidationException;
import br.com.btg.model.Input;
import br.com.btg.service.InputService;
import br.com.btg.util.InputUtil;
import br.com.btg.util.StringUtil;

@RestController
@RequestMapping("/input")
public class InputController {
	
	@Autowired
	private InputService inputService;
	
	@PostMapping
	public ResponseEntity<Object> addInput(@RequestBody Input input) {
		ResponseEntity<Object> response = null;

		if (InputUtil.isAValidInput(input)) {
			try {
				Input inputResponse = inputService.addInput(input);
				response = new ResponseEntity<Object>(inputResponse, HttpStatus.CREATED);

				return response;
			} catch(ValidationException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<Object>(Message.INPUT_HAS_TO_BE_COMPLETE, HttpStatus.OK);
		}

	}
	
	@GetMapping
	public ResponseEntity<List<Input>> getAll(){
		try {
			return new ResponseEntity<List<Input>>(inputService.getAll(), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{playerName}")
	public ResponseEntity<Object> getBy(@PathVariable String playerName){
		if(!StringUtil.isNullOrEmpty(playerName)) {
			try {
				return new ResponseEntity<Object>(inputService.getBy(playerName), HttpStatus.OK);
			} catch(ValidationException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<Object>(Message.INPUT_HAS_TO_BE_COMPLETE, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{playerName}")
	public ResponseEntity<Object> deleteBy(@PathVariable String playerName){
		if(!StringUtil.isNullOrEmpty(playerName)) {
			try {
				inputService.deleteBy(playerName);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} catch(ValidationException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<Object>(Message.INPUT_HAS_TO_BE_COMPLETE, HttpStatus.OK);
		}
	}
}
