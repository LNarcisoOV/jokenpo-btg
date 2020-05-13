package br.com.btg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.btg.model.Player;
import br.com.btg.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping
	public ResponseEntity<Player> addPlayer(@RequestBody Player player){
		ResponseEntity<Player> response = null;
		
		try {
			Player playerDB = playerService.getBy(player.getName());
			
			if(playerDB != null) {
				response = new ResponseEntity<Player>(HttpStatus.FOUND);
			} else {
				Player playerResponse = playerService.addPlayer(player);
				response = new ResponseEntity<Player>(playerResponse, HttpStatus.CREATED);
			} 
			
			return response;
		} catch(Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<Player>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
	}
}
