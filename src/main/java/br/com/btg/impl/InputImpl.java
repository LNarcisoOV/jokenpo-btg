package br.com.btg.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.btg.constants.Message;
import br.com.btg.exception.ValidationException;
import br.com.btg.model.Input;
import br.com.btg.service.InputService;

@Service
public class InputImpl implements InputService{
	
	private List<Input> inputList = null;
	
	public InputImpl() {
		inputList = new ArrayList<Input>();
	}

	@Override
	public Input getBy(String playerName) throws ValidationException {
		Optional<Input> optionalPlayer = inputList.stream().filter(p -> p.getPlayerName().equalsIgnoreCase(playerName)).findFirst();
		Input input = optionalPlayer.orElse(null);
		
		if(input == null) {
			throw new ValidationException(Message.INPUT_NOT_FOUND);
		}
		
		return input;
	}
	
	@Override
	public Input addInput(Input input) throws ValidationException {
		if(!isPlayerAlreadyExists(input.getPlayerName())) {
			input.setName(input.getPlayerName().toUpperCase());
			input.setShot(input.getShot().toUpperCase());
			inputList.add(input);
			return input;
		} else {
			throw new ValidationException(Message.PLAYER_ALREADY_EXISTS);
		}
	}

	@Override
	public List<Input> getAll() {
		return inputList;
	}

	@Override
	public void deleteBy(String playerName) throws ValidationException {
		Input input = getBy(playerName);
		inputList.remove(input);
	}
	
	private boolean isPlayerAlreadyExists(String playerName) throws ValidationException {
		Optional<Input> optionalInput = inputList.stream().filter(p -> p.getPlayerName().equalsIgnoreCase(playerName)).findFirst();
		Input input = optionalInput.orElse(null);
		
		return input != null;
	}
}
