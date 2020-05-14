package br.com.btg.service;

import java.util.List;

import br.com.btg.exception.ValidationException;
import br.com.btg.model.Player;

public interface PlayerService {
	Player getBy(String name) throws ValidationException;
	Player addPlayer(Player player) throws ValidationException;
	List<Player> getAll();
	void deleteBy(String name) throws ValidationException;
}
