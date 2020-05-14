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
import br.com.btg.model.Player;
import br.com.btg.service.PlayerService;
import br.com.btg.service.ShotService;
import br.com.btg.util.StringUtil;

@RestController  
@RequestMapping("/shot")
public class ShotController {
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private ShotService shotService;
	
	@PostMapping
	public ResponseEntity<Object> addShot(@RequestBody Player playerRequest){
		ResponseEntity<Object> response = null;
		
		if(playerRequest != null && !StringUtil.isNullOrEmpty(playerRequest.getName())) {
			try {
				Player playerResponse = playerService.getBy(playerRequest.getName());
				
				if(playerResponse != null) {
					playerResponse = shotService.addShotTo(playerResponse, playerRequest.getShot());
					response = new ResponseEntity<Object>(playerResponse, HttpStatus.OK);
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
		return new ResponseEntity<Object>(Message.PLAYER_HAS_TO_HAVE_A_NAME, HttpStatus.OK);
	}

}
