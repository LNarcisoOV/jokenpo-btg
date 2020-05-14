package br.com.btg.util;

import br.com.btg.model.Input;

public class InputUtil {
	public static boolean isAValidInput(Input input) {
		return input != null && (!StringUtil.isNullOrEmpty(input.getPlayerName()) && !StringUtil.isNullOrEmpty(input.getShot()));
	}
}
