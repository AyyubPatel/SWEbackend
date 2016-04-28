package org.com.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Ayyub
 *
 */
@Entity
@Table(name="USER_PROFILE")
public class UserProfile {
	
	private String userId;
	private int numberOfMatches;
	private int win;
	private int lose;
	private int rank;
	private float winProbability;
	private String status;
	
	public UserProfile(String userId, int numberOfMatches, int win, int lose,
			int rank, float winProbability, String status) {
		super();
		this.userId = userId;
		this.numberOfMatches = numberOfMatches;
		this.win = win;
		this.lose = lose;
		this.rank = rank;
		this.winProbability = winProbability;
		this.status = status;
	}
	
	public UserProfile() {
		// TODO Auto-generated constructor stub
	}

	@Id
    @Column(name="USER_ID", unique=true, nullable=false, precision = 0)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
    @Column(name="NUMBER_OF_MATCHES", nullable=false)
	public int getNumberOfMatches() {
		return numberOfMatches;
	}
	public void setNumberOfMatches(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}
	
	@Column(name="WIN", nullable=false)
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	
	@Column(name="lose", nullable=false)
	public int getlose() {
		return lose;
	}
	public void setlose(int lose) {
		this.lose = lose;
	}
	
	@Column(name="RANK", nullable=false)
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Column(name="STATUS", nullable=false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="WIN_PROBABILITY", nullable=false)
	public float getWinProbability() {
		return winProbability;
	}
	public void setWinProbability(float winProbability) {
		this.winProbability = winProbability;
	}
}
