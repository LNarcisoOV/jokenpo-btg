package br.com.btg.util;

import java.util.List;

import br.com.btg.constants.Shot;
import br.com.btg.model.Input;

public class InputUtil {

	static List<String> shotList = Shot.getAllKindOfShots();

	public static boolean isAValidInput(Input input) {
		return input != null
				&& (!StringUtil.isNullOrEmpty(input.getPlayerName()) && !StringUtil.isNullOrEmpty(input.getShot()))
				&& shotList.contains(input.getShot().toUpperCase());
	}
}
