package br.com.btg.model;

public class Player {
	
	private String name;
	private String shot;
	
	public Player() {
	}

	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShot() {
		return shot;
	}

	public void setShot(String shot) {
		this.shot = shot;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", shot=" + shot + "]";
	}

}
