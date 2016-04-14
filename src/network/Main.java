package network;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResultSet consulta;
		//Enviem a la nova instància ConectorDB usuari, password, BBDD i port per iniciar els paràmetres de connexió
		ConectorDB conn = new ConectorDB("root", "", "memotournamentdb", 3306);
		//Ens connectem a la BBDD
		conn.connect();
		//Inserim un registre a la BBDD
		conn.insertQuery("INSERT INTO usuari (nickname,pasword,score) VALUES ('Rafa','4255','2')");
		conn.insertQuery("INSERT INTO usuari (nickname,pasword,score) VALUES ('Gonzalo','2244','100')");

		//Modifiquem un registre de la BBDD
		//conn.updateQuery("UPDATE persones SET telefon='222333' WHERE nom='Rafa'");
		//Eliminem un registre de la BBDD
		//conn.deleteQuery("DELETE FROM persones WHERE nom='Gonzalo'");
		//Seleccionem registres de la BBDD
		consulta = conn.selectQuery("SELECT * FROM usuari");
		
		try {
			//Recorrem el ResultSet que ens retorna el selectQuery i agafem els paràmetres desitjats
			while (consulta.next())
			{
			    //Per recuperar el valor utilitzem la funció .getObject() amb el nom del camp a recuperar
				System.out.println (consulta.getObject("nickname") + " " + consulta.getObject("pasword")+ " " + consulta.getObject("score"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problema al recuperar les dades...");
		}
		
		//Ens desconectem de la BBDD una vegada no la necessitem
		conn.disconnect();		

	}

}
