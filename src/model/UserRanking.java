package model;

import java.util.Comparator;

public class UserRanking implements Comparator<UserRanking>, Comparable<UserRanking>{
	private String nickname;
	private int punctuation;
	
	public UserRanking(String nickname, int punctuation){
		this.nickname = nickname;
		this.punctuation = punctuation;
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
	
	
	@Override
	public int compare(UserRanking o1, UserRanking o2) {
		return o1.getPunctuation() - o2.getPunctuation();
	}
	@Override
	public int compareTo(UserRanking o) {
				return this.getPunctuation() - o.getPunctuation();
	}
}
