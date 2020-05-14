package br.com.btg.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.btg.constants.Message;
import br.com.btg.exception.ValidationException;
import br.com.btg.model.Player;
import br.com.btg.service.PlayerService;

@Service
public class PlayerImpl implements PlayerService{
	
	private List<Player> playerList = null;
	
	public PlayerImpl() {
		playerList = new ArrayList<Player>();
	}

	@Override
	public Player getBy(String name) throws ValidationException {
		Optional<Player> optionalPlayer = playerList.stream().filter(p -> p.getName().equals(name)).findFirst();
		Player player = optionalPlayer.orElse(null);
		
		if(player == null) {
			throw new ValidationException(Message.PLAYER_NOT_FOUND);
		}
		
		return player;
	}
	
	@Override
	public Player addPlayer(Player player) throws ValidationException {
		if(!isPlayerAlreadyExists(player.getName())) {
			playerList.add(player);
			return player;
		} else {
			throw new ValidationException(Message.PLAYER_ALREADY_EXISTS);
		}
	}

	@Override
	public List<Player> getAll() {
		return playerList;
	}

	@Override
	public void deleteBy(String name) throws ValidationException {
		Player player = getBy(name);
		playerList.remove(player);
	}
	
	private boolean isPlayerAlreadyExists(String name) throws ValidationException {
		Optional<Player> optionalPlayer = playerList.stream().filter(p -> p.getName().equals(name)).findFirst();
		Player player = optionalPlayer.orElse(null);
		
		return player != null;
	}
}
