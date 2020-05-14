package br.com.btg.model;

public class Input {
	
	private String playerName;
	private String shot;
	
	public Input() {
	}

	public Input(String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setName(String playerName) {
		this.playerName = playerName;
	}

	public String getShot() {
		return shot;
	}

	public void setShot(String shot) {
		this.shot = shot;
	}

	@Override
	public String toString() {
		return "Input [playerName=" + playerName + ", shot=" + shot + "]";
	}

}
