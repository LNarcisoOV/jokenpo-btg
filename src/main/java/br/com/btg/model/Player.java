package br.com.btg.model;

import br.com.btg.constants.Shot;

public class Player {

	private String name;
	private Shot shot;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Shot getShot() {
		return shot;
	}

	public void setShot(Shot shot) {
		this.shot = shot;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", shot=" + shot + "]";
	}

}
