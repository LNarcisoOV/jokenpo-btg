package br.com.btg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.btg.constants.Message;
import br.com.btg.exception.ValidationException;
import br.com.btg.model.Input;
import br.com.btg.service.InputService;
import br.com.btg.service.ShotService;
import br.com.btg.util.InputUtil;

@RestController  
@RequestMapping("/shot")
public class ShotController {
	
	@Autowired
	private InputService playerService;
	
	@Autowired
	private ShotService shotService;
	
	@PostMapping
	public ResponseEntity<Object> addShot(@RequestBody Input inputRequest){
		ResponseEntity<Object> response = null;
		
		if(InputUtil.isAValidInput(inputRequest)) {
			try {
				Input inputResponse = playerService.getBy(inputRequest.getPlayerName());
				
				if(inputResponse != null) {
					inputResponse = shotService.addShotTo(inputResponse, inputRequest.getShot());
					response = new ResponseEntity<Object>(inputResponse, HttpStatus.OK);
				} 
				
				return response;
			} catch(ValidationException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				return response;
			}
			
		}
		return new ResponseEntity<Object>(Message.INPUT_HAS_TO_BE_COMPLETE, HttpStatus.OK);
	}

}
