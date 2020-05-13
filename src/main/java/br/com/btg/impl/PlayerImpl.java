package br.com.btg.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.btg.model.Player;
import br.com.btg.service.PlayerService;

@Service
public class PlayerImpl implements PlayerService{
	
	private List<Player> playerList = null;
	
	public PlayerImpl() {
		playerList = new ArrayList<Player>();
	}

	@Override
	public Player getBy(String name) {
		if(playerList == null || playerList.isEmpty()) {
			return null;
		}
		
		Optional<Player> optionalPlayer = playerList.stream().filter(p -> p.getName().equals(name)).findFirst();
		return optionalPlayer.orElse(null);
	}

	@Override
	public Player addPlayer(Player player) {
		playerList.add(player);
		return player;
	}

}
