package model;

import java.util.Comparator;
/**
 * Aquesta classe permet portar un registre de l'usuari registrat a la competicio.
 */
public class UserRanking implements Comparator<UserRanking>, Comparable<UserRanking>{
	private String nickname;
	private int punctuation;
	/**
	 * indica si l'usuari esta bloquejat o no.
	 */
	private boolean blocked = false;
	
	public UserRanking(String nickname, int punctuation){
		this.nickname = nickname;
		this.punctuation = punctuation;
	}
	public UserRanking() {
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
	/**
	 * Compara la puntuacio dels usuaris per a ordenar l'array en funcio de la puntuaci0(per ordre descendent).
	 */
	@Override
	public int compare(UserRanking o1, UserRanking o2) {
		if (o1.getPunctuation() - o2.getPunctuation() < 0) {
			return 1;
		}
		return -1;
	}
	/**
	 * Compara la puntuacio dels usuaris per a ordenar l'array en funcio de la puntuacio(per ordre descendent).
	 */
	@Override
	public int compareTo(UserRanking o) {
		if (this.getPunctuation() - o.getPunctuation() < 0) {
			return 1;
		}
		return -1;
	}
}
