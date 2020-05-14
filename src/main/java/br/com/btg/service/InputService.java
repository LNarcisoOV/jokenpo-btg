package br.com.btg.service;

import java.util.List;

import br.com.btg.exception.ValidationException;
import br.com.btg.model.Input;

public interface InputService {
	Input getBy(String playerNmae) throws ValidationException;
	Input addInput(Input input) throws ValidationException;
	List<Input> getAll();
	void deleteBy(String playerNmae) throws ValidationException;
}
