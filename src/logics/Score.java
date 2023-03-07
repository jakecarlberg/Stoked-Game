package logics;

import java.io.Serializable;

public class Score implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private double score;

	public Score(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}
	
	

}
