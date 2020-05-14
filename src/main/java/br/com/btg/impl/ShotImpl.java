package br.com.btg.impl;

import org.springframework.stereotype.Service;

import br.com.btg.constants.Message;
import br.com.btg.constants.Shot;
import br.com.btg.exception.ValidationException;
import br.com.btg.model.Input;
import br.com.btg.service.ShotService;
import br.com.btg.util.StringUtil;

@Service
public class ShotImpl implements ShotService{

	@Override
	public Input addShotTo(Input inputResponse, String shot) {
		if(isAValidShot(shot)) {
			inputResponse.setShot(shot.toUpperCase());
		}else {
			throw new ValidationException(Message.INVALID_SHOT);
		}
		
		return inputResponse;
	}

	private boolean isAValidShot(String shot) {
		if(StringUtil.isNullOrEmpty(shot)) { 
			throw new ValidationException(Message.SHOT_CANT_BE_EMPTY);
		} else {
			return Shot.getAllKindOfShots().stream().filter(s -> s.equalsIgnoreCase(shot)).count() > 0L;
		}
	}
}
