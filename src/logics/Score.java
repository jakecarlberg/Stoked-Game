package logics;

import java.io.Serializable;

/**
 * This class represents the score to let the user be able to save its score together 
 * with a name on a text file localy stored on the hard drive.
 */

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
