package br.com.btg.impl;

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

public class ShotImplTest {
	
	@InjectMocks
	private InputImpl inputImpl;
	
	@InjectMocks
	private ShotImpl shotImpl;

	private Input input1;
	
	@BeforeEach
	public void init() {
		input1 = new Input();
		input1.setName("player1");
		input1.setShot("scissor");
	}
	
	@Test
	public void shouldAddShot() {
		Input InputAltered = shotImpl.addShotTo(input1, "STONE");
		assertTrue(InputAltered.getShot().equalsIgnoreCase("STONE"));
	}
	
	@Test
	public void shouldThrowValidationExceptionIfInputShotIsInvalid() {
		Assertions.assertThrows(ValidationException.class, () -> {
			shotImpl.addShotTo(input1, "STON");
		});
	}

	@Test
	public void shouldThrowValidationExceptionMessageIfShotIsInvalid() {
		ValidationException validationException = Assertions.assertThrows(ValidationException.class,
				() -> shotImpl.addShotTo(input1, "STON"));

		assertTrue(validationException.getMessage().equals(Message.INVALID_SHOT));
	}
	
	@Test
	public void shouldThrowValidationExceptionIfInputShotIsEmpy() {
		Assertions.assertThrows(ValidationException.class, () -> {
			shotImpl.addShotTo(input1, "");
		});
	}

	@Test
	public void shouldThrowValidationExceptionMessageIfShotIsEmpty() {
		ValidationException validationException = Assertions.assertThrows(ValidationException.class,
				() -> shotImpl.addShotTo(input1, ""));

		assertTrue(validationException.getMessage().equals(Message.SHOT_CANT_BE_EMPTY));
	}
}
