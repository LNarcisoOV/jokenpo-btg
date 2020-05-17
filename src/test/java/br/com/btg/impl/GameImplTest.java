package br.com.btg.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.btg.model.Input;

@ExtendWith(MockitoExtension.class)
public class GameImplTest {
	
	@InjectMocks
	private InputImpl inputImpl;
	
	@InjectMocks
	private GameImpl gameImpl;

	private Input inputScissor;
	private Input inputPaper;
	private Input inputStone;
	private Input inputLizard;
	private Input inputSpock;
	
	List<Input> inputList;
	
	@BeforeEach
	public void init() {
		inputScissor = new Input();
		inputScissor.setPlayerName("player1");
		inputScissor.setShot("scissor");
		
		inputPaper = new Input();
		inputPaper.setPlayerName("player2");
		inputPaper.setShot("paper");
		
		inputStone = new Input();
		inputStone.setPlayerName("player3");
		inputStone.setShot("stone");
		
		inputLizard = new Input();
		inputLizard.setPlayerName("player4");
		inputLizard.setShot("lizard");
		
		inputSpock = new Input();
		inputSpock.setPlayerName("player5");
		inputSpock.setShot("spock");
	}
	
	@Test
	public void scissorShouldBeTheWinner() {
		inputImpl.addInput(inputStone);
		inputImpl.addInput(inputLizard);
		inputImpl.addInput(inputPaper);
		inputImpl.addInput(inputSpock);
		inputImpl.addInput(inputScissor);
		assertTrue(inputScissor.equals(gameImpl.play(inputImpl.getAll())));
	}
	
	@Test
	public void paperShouldBeTheWinner() {
		inputImpl.addInput(inputStone);
		inputImpl.addInput(inputLizard);
		inputImpl.addInput(inputSpock);
		inputImpl.addInput(inputScissor);
		inputImpl.addInput(inputPaper);
		assertTrue(inputPaper.equals(gameImpl.play(inputImpl.getAll())));
	}
	
	@Test
	public void stoneShouldBeTheWinner() {
		inputImpl.addInput(inputSpock);
		inputImpl.addInput(inputScissor);
		inputImpl.addInput(inputPaper);
		inputImpl.addInput(inputLizard);
		inputImpl.addInput(inputStone);
		assertTrue(inputStone.equals(gameImpl.play(inputImpl.getAll())));
	}
	
	@Test
	public void lizardShouldBeTheWinner() {
		inputImpl.addInput(inputStone);
		inputImpl.addInput(inputPaper);
		inputImpl.addInput(inputScissor);
		inputImpl.addInput(inputSpock);
		inputImpl.addInput(inputLizard);
		assertTrue(inputLizard.equals(gameImpl.play(inputImpl.getAll())));
	}
	
	@Test
	public void spockShouldBeTheWinner() {
		inputImpl.addInput(inputScissor);
		inputImpl.addInput(inputPaper);
		inputImpl.addInput(inputStone);
		inputImpl.addInput(inputLizard);
		inputImpl.addInput(inputSpock);
		assertTrue(inputSpock.equals(gameImpl.play(inputImpl.getAll())));
	}

	@Test
	public void firstInputShouldBeTheWinnerWithRepeatedValues() {
		Input inputWinner = new Input();
		inputWinner.setPlayerName("playerWinner");
		inputWinner.setShot("paper");
		inputImpl.addInput(inputWinner);
		inputImpl.addInput(inputPaper);
		assertTrue(inputWinner.equals(gameImpl.play(inputImpl.getAll())));
	}
}
