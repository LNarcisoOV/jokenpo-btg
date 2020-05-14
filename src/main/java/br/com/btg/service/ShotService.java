package br.com.btg.service;

import br.com.btg.model.Player;

public interface ShotService {
	Player addShotTo(Player playerDB, String shot);
}
