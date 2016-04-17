package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import network.*;

public class MTMainServer {

	public static void main(String[] args) {
		
		ResultSet consulta;
		ConectorDB conn = new ConectorDB("root", "", "memotournamentdb", 3306);
		conn.connect();
		//conn.insertUser("Nick", "solsona");
		//conn.insertUser("Ruru", ":3");
		//conn.insertGame("Nick", "memoria", 112);
		//conn.deleteUser("Rafa");
		//conn.updateScore("Ruru", 55);
		consulta = conn.selectGames("Nick");
		//consulta = conn.selectMatch("Ruru");
		
		try {
			while (consulta.next())
			{
				System.out.println (consulta.getObject("name") + " " + consulta.getObject("mode")+ " " + consulta.getObject("score"));
			}
		} catch (SQLException e) {
			System.out.println("Problema al recuperar les dades...");
		}
		
		conn.disconnect();
	}
}
