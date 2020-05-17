package br.com.btg.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.btg.constants.Message;
import br.com.btg.exception.ValidationException;
import br.com.btg.model.Input;

@ExtendWith(MockitoExtension.class)
public class InputImplTest {

	@InjectMocks
	private InputImpl inputImpl;

	private Input input1;

	@BeforeEach
	public void init() {
		input1 = new Input();
		input1.setPlayerName("player1");
		input1.setShot("scissor");
	}

	@Test
	public void shouldAddInput() {
		Input inputReturn = inputImpl.addInput(input1);
		assertNotNull(inputReturn);
		assertTrue(inputImpl.getAll().size() > 0);
	}

	@Test
	public void shouldThrowValidationExceptionIfInputAlreadyInList() {
		inputImpl.addInput(input1);

		Assertions.assertThrows(ValidationException.class, () -> {
			inputImpl.addInput(input1);
		});
	}

	@Test
	public void shouldThrowValidationExceptionMessageIfInputAlreadyInList() {
		inputImpl.addInput(input1);

		ValidationException validationException = Assertions.assertThrows(ValidationException.class,
				() -> inputImpl.addInput(input1));

		assertTrue(validationException.getMessage().equals(Message.PLAYER_ALREADY_EXISTS));
	}

	@Test
	public void shouldGetInputBy() {
		inputImpl.addInput(input1);

		Input inputGetBy = inputImpl.getBy("PLAYER1");

		assertNotNull(inputGetBy);
		assertEquals(inputGetBy.getPlayerName(), "PLAYER1");
		assertEquals(inputGetBy.getShot(), "SCISSOR");
	}
	
	@Test
	public void shouldThrowValidationExceptionIfInputNotFound() {
		inputImpl.addInput(input1);

		Assertions.assertThrows(ValidationException.class, () -> {
			inputImpl.getBy("PLAYER2_NOT_FOUND");
		});
	}

	@Test
	public void shouldThrowValidationExceptionMessageIfInputNotFound() {
		inputImpl.addInput(input1);

		ValidationException validationException = Assertions.assertThrows(ValidationException.class,
				() -> inputImpl.getBy("PLAYER2_NOT_FOUND"));

		assertTrue(validationException.getMessage().equals(Message.INPUT_NOT_FOUND));
	}
	
	@Test
	public void shouldDeleteInputBy() {
		inputImpl.addInput(input1);

		inputImpl.deleteBy("PLAYER1");

		assertTrue(inputImpl.getAll().isEmpty());
	}
	
	@Test
	public void shouldThrowValidationExceptionIfDeleteInputNotFound() {

		Assertions.assertThrows(ValidationException.class, () -> {
			inputImpl.deleteBy("PLAYER2_NOT_FOUND");
		});
	}

	@Test
	public void shouldThrowValidationExceptionMessageIfDeleteInputNotFound() {

		ValidationException validationException = Assertions.assertThrows(ValidationException.class,
				() -> inputImpl.deleteBy("PLAYER2_NOT_FOUND"));

		assertTrue(validationException.getMessage().equals(Message.INPUT_NOT_FOUND));
	}

}
