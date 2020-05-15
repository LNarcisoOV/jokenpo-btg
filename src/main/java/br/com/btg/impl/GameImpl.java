package br.com.btg.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.btg.constants.Shot;
import br.com.btg.model.Input;
import br.com.btg.service.GameService;

@Service
public class GameImpl implements GameService{
	
	private Input winner = null;

	@Override
	public Input play(List<Input> inputList) {
		getWinner(inputList);
		return winner;
	}

	private void getWinner(List<Input> inputList) {
		if(inputList.size() == 1) {
			winner = inputList.get(0);
			return;
		}
		applyRules(inputList.get(0), inputList.get(1), inputList);
		getWinner(inputList);
	}

	private void applyRules(Input input1, Input input2, List<Input> inputList) {
//		Removing equal shots because doesn't have any rule for draws
		if(input1.getShot().equalsIgnoreCase(input2.getShot())) {
			inputList.remove(input2);
		} else {
			switch (input1.getShot()) {
			case Shot.SCISSOR:
				switch (input2.getShot()) {
				case Shot.PAPER:
					inputList.remove(input2);
					break;
				case Shot.STONE:
					inputList.remove(input1);
					break;
				case Shot.LIZARD:
					inputList.remove(input2);
					break;
				case Shot.SPOCK:
					inputList.remove(input1);
					break;
				}
				break;
	
			case Shot.PAPER:
				switch (input2.getShot()) {
				case Shot.SCISSOR:
					inputList.remove(input1);
					break;
				case Shot.STONE:
					inputList.remove(input2);
					break;
				case Shot.LIZARD:
					inputList.remove(input1);
					break;
				case Shot.SPOCK:
					inputList.remove(input2);
					break;
				}
				break;
				
			case Shot.STONE:
				switch (input2.getShot()) {
				case Shot.SCISSOR:
					inputList.remove(input2);
					break;
				case Shot.PAPER:
					inputList.remove(input1);
					break;
				case Shot.LIZARD:
					inputList.remove(input2);
					break;
				case Shot.SPOCK:
					inputList.remove(input1);
					break;
				}
				break;
				
			case Shot.LIZARD:
				switch (input2.getShot()) {
				case Shot.SCISSOR:
					inputList.remove(input1);
					break;
				case Shot.PAPER:
					inputList.remove(input2);
					break;
				case Shot.STONE:
					inputList.remove(input1);
					break;
				case Shot.SPOCK:
					inputList.remove(input2);
					break;
				}
				break;
				
			case Shot.SPOCK:
				switch (input2.getShot()) {
				case Shot.SCISSOR:
					inputList.remove(input2);
					break;
				case Shot.PAPER:
					inputList.remove(input1);
					break;
				case Shot.STONE:
					inputList.remove(input2);
					break;
				case Shot.LIZARD:
					inputList.remove(input1);
					break;
				}
				break;
			}
		}

	}

}
