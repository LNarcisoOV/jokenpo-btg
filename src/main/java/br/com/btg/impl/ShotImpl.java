package br.com.btg.impl;

import org.springframework.stereotype.Service;

import br.com.btg.constants.Message;
import br.com.btg.constants.Shot;
import br.com.btg.exception.ValidationException;
import br.com.btg.model.Player;
import br.com.btg.service.ShotService;
import br.com.btg.util.StringUtil;

@Service
public class ShotImpl implements ShotService{

	@Override
	public Player addShotTo(Player playerDB, String shot) {
		if(isAValidShot(shot)) {
			playerDB.setShot(shot.toUpperCase());
		}else {
			throw new ValidationException(Message.INVALID_SHOT);
		}
		
		return playerDB;
	}

	private boolean isAValidShot(String shot) {
		if(StringUtil.isNullOrEmpty(shot)) { 
			throw new ValidationException(Message.SHOT_CANT_BE_EMPTY);
		} else {
			return Shot.getAllKindOfShots().stream().filter(s -> s.equalsIgnoreCase(shot)).count() > 0L;
		}
	}
}
