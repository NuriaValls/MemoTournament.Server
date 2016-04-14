package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import network.*;

public class MTMain {

	public static void main(String[] args) {
		
		ResultSet consulta;
		ConectorDB conn = new ConectorDB("root", "", "memotournamentdb", 3306);
		conn.connect();
		conn.insertQuery("INSERT INTO usuari (nickname,pasword,score) VALUES ('Rafa','4255','2')");
		conn.insertQuery("INSERT INTO usuari (nickname,pasword,score) VALUES ('Gonzalo','2244','100')");
		consulta = conn.selectQuery("SELECT * FROM usuari");
		
		try {
			while (consulta.next())
			{
				System.out.println (consulta.getObject("nickname") + " " + consulta.getObject("pasword")+ " " + consulta.getObject("score"));
			}
		} catch (SQLException e) {
			System.out.println("Problema al recuperar les dades...");
		}
		
		conn.disconnect();
	}
}
