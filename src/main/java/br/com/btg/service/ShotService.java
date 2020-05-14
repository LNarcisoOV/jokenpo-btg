package br.com.btg.service;

import br.com.btg.model.Input;

public interface ShotService {
	Input addShotTo(Input playerDB, String shot);
}
