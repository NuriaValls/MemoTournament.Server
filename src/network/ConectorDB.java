package network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Aquesta classe ens permet establir una connexio amb la base de dades per poder consultar i insrir informacio.
 */
public class ConectorDB {
	/**
	 * nom de l'usuari que vol accedir a la base de dades.
	 */
	static String userName;
	/**
	 * contrasssenya de l'usuari que vol accedir a la base de dades.
	 */
	static String password;
	/**
	 * nom de la base de dades.
	 */
	static String db;
	/**
	 * port de conneio de la base de dades.
	 */
	static int port;
	/**
	 * url de la base de dades.
	 */
	static String url = "jdbc:mysql://";
	/**
	 * indocador de connexio aciva.
	 */
	static Connection conn = null;
	/**
	 * Linia d'interaccio amb la base de dades.
	 */
	static Statement s;
    
	public ConectorDB(String usr, String pass, String db, int port, String ip) {
		ConectorDB.userName = usr;
		ConectorDB.password = pass;
		ConectorDB.db = db;
		ConectorDB.port = port;
		ConectorDB.url += ip;
		ConectorDB.url += ":"+port+"/";
		ConectorDB.url += db;
	}
	/**
	 * estableix la connexio amb la base ded dades.
	 */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = (Connection) DriverManager.getConnection(url, userName, password);
            if (conn != null) {
                System.out.println("Conexi� a base de dades "+url+" ... Ok");
            }
        }
        catch(SQLException ex) {
            System.out.println("Problema al connecta-nos a la BBDD --> "+url);
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex);
        }

    }
    /**
	 * insereix un nou usuari amb eln nom i la contrassenya que rep.
	 */
    public static boolean insertUser(String nickname, String password){
        String query = new String("INSERT INTO usuari (nickname,pasword,score) VALUES ('"+nickname+"','"+password+"','0')");
    	try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Inserir --> " + ex.getSQLState());
            return false;
        }
    	return true;
    }
    /**
	 * actualitza la puntuacio total de l'usuari que rep amb la puntuacio que rep.
	 */
    public static void updateScore(String nickname, int score){
    	String query = new String("UPDATE usuari SET score='"+score+"' WHERE nickname='"+nickname+"'");
    	try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Modificar --> " + ex.getSQLState());
        }
    }
    /**
	 * elimina de la base de dades l'usuari amb el nom corresponent al nom que rep.
	 */
    public static void deleteUser(String nickname){
    	String query = new String("DELETE FROM usuari WHERE nickname='"+nickname+"'");
    	try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);
             
        } catch (SQLException ex) {
            System.out.println("Problema al Eliminar --> " + ex.getSQLState());
        }
    	
    }
    /**
	 * retonra un result set amb tots el susuaris registrats a la base de dades.
	 */
    public static ResultSet selectAllUsers(){
    	ResultSet rs = null;
    	String query = new String("SELECT * FROM usuari");
    	try {
            s =(Statement) conn.createStatement();
            rs = s.executeQuery (query);
            
        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
        }
		return rs;
    }
    /**
	 * retorna un result set de la base de dades amb un usuari amb el nom corresponent al que rep.
	 */
    public static ResultSet selectUser(String nickname){
    	ResultSet rs = null;
    	String query = new String("SELECT * FROM usuari WHERE nickname='"+nickname+"'");
    	try {
            s =(Statement) conn.createStatement();
            rs = s.executeQuery (query);
            
        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
        }
		return rs;
    }
    /**
	 * insereix una nova partida amb el nom de l'usuari la puntuacio i el mode que rep.
	 */
    public static void insertGame(String nickname, String mode, int score){
    	ResultSet consulta = null;
    	int id = 0;
    	consulta = selectAllGames();
    	try {
			while (consulta.next()){
				id = (int) consulta.getObject("id");
			}
			id++;
		} catch (SQLException e) {
			System.out.println("Problema al recuperar les dades... select->insert");
		}
        String query = new String("INSERT INTO game (id,mode,name,score) VALUES ('"+id+"','"+mode+"','"+nickname+"','"+score+"')");
    	try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Inserir --> " + ex.getSQLState());
        }
    }
    /**
	 * selecciona totes les partides registrades a la base de dades.
	 */
    public static ResultSet selectAllGames(){
    	ResultSet rs = null;
    	String query = new String("SELECT * FROM game");
    	try {
            s =(Statement) conn.createStatement();
            rs = s.executeQuery (query);
            
        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
        }
		return rs;
    }
    /**
	 * selecciona totes les partides que hi ha a la base de dades del jugador amb el nom que rep.
	 */
    public static ResultSet selectGames(String name){
    	ResultSet rs = null;
    	String query = new String("SELECT * FROM game WHERE name='"+name+"'");
    	try {
            s =(Statement) conn.createStatement();
            rs = s.executeQuery (query);
            
        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
        }
		return rs;
    }
    /**
	 * tanca la connexio amb la base de dades.
	 */
    public void disconnect(){
    	try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Problema al tancar la connexi� --> " + e.getSQLState());
		}
    }

}
