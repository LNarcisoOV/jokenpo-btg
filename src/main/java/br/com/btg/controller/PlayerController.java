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
	
	@GetMapping
	public ResponseEntity<List<Player>> getAll(){
		ResponseEntity<List<Player>> response = null;
		
		try {
			return new ResponseEntity<List<Player>>(playerService.getAll(), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Player> getBy(@PathVariable String name){
		ResponseEntity<Player> response = null;
		
		try {
			return new ResponseEntity<Player>(playerService.getBy(name), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<Player>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<Player> deleteBy(@PathVariable String name){
		ResponseEntity<Player> response = null;
		
		try {
			playerService.deleteBy(name);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<Player>(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
}
