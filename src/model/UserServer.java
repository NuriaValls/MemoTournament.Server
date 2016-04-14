package model;

public class UserServer {
	private String nickName;
	private String password;
	private int punctuation;
	private int ranking;
	private String dateRegister;
	private String timeRegister;
	
	public void userRegistration(){
		//demanar dades pel registre i donar d'alta.
		//actualitzar taula usuaris (Ranking)
	}
	
	public void controlErrors(){
		//controla errors entrats pel client i/o servidor.
		//nom usuari unic.
		//paraula pas 6 caracters(lletres i números).
		//correctament emmagatzemades a la base de dades.
	}
	
	public void showRanking(){
		
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPunctuation() {
		return punctuation;
	}
	public void setPunctuation(int punctuation) {
		this.punctuation = punctuation;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getDateRegister() {
		return dateRegister;
	}
	public void setDateRegister(String dateRegister) {
		this.dateRegister = dateRegister;
	}
	public String getTimeRegister() {
		return timeRegister;
	}
	public void setTimeRegister(String timeRegister) {
		this.timeRegister = timeRegister;
	}
}
