package br.com.btg.service;

import br.com.btg.model.Player;

public interface PlayerService {
	Player getBy(String name);
	Player addPlayer(Player player);
}
