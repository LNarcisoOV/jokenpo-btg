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
import br.com.btg.model.Player;
import br.com.btg.service.PlayerService;
import br.com.btg.util.StringUtil;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping
	public ResponseEntity<Object> addPlayer(@RequestBody Player player) {
		ResponseEntity<Object> response = null;

		if (player != null && !StringUtil.isNullOrEmpty(player.getName())) {
			try {
				Player playerResponse = playerService.addPlayer(player);
				response = new ResponseEntity<Object>(playerResponse, HttpStatus.CREATED);

				return response;
			} catch(ValidationException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<Object>(Message.PLAYER_HAS_TO_HAVE_A_NAME, HttpStatus.OK);
		}

	}
	
	@GetMapping
	public ResponseEntity<List<Player>> getAll(){
		try {
			return new ResponseEntity<List<Player>>(playerService.getAll(), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Object> getBy(@PathVariable String name){
		if(!StringUtil.isNullOrEmpty(name)) {
			try {
				return new ResponseEntity<Object>(playerService.getBy(name), HttpStatus.OK);
			} catch(ValidationException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<Object>(Message.PLAYER_HAS_TO_HAVE_A_NAME, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<Object> deleteBy(@PathVariable String name){
		if(!StringUtil.isNullOrEmpty(name)) {
			try {
				playerService.deleteBy(name);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} catch(ValidationException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<Object>(Message.PLAYER_HAS_TO_HAVE_A_NAME, HttpStatus.OK);
		}
	}
}
