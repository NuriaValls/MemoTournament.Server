package model;

import java.util.Comparator;

public class UserRanking implements Comparator<UserRanking>, Comparable<UserRanking>{
	private String nickname;
	private int punctuation;
	private boolean blocked = false;
	
	public UserRanking(String nickname, int punctuation){
		this.nickname = nickname;
		this.punctuation = punctuation;
	}
	public UserRanking() {
		// TODO Auto-generated constructor stub
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPunctuation() {
		return punctuation;
	}
	public void setPunctuation(int punctuation) {
		this.punctuation = punctuation;
	}
	
	public void setBlocked(boolean blocked){
		this.blocked = blocked;
	}
	
	public boolean getBlocked(){
		return blocked;
	}
	
	
	
	@Override
	public int compare(UserRanking o1, UserRanking o2) {
		if (o1.getPunctuation() - o2.getPunctuation() < 0) {
			return 1;
		}
		return -1;
	}
	@Override
	public int compareTo(UserRanking o) {
		if (this.getPunctuation() - o.getPunctuation() < 0) {
			return 1;
		}
		return -1;
	}
}
