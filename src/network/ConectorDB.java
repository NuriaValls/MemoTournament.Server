package network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectorDB {
	static String userName;
	static String password;
	static String db;
	static int port;
	static String url = "jdbc:mysql://";
	static Connection conn = null;
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
    
    public static void updateScore(String nickname, int score){
    	String query = new String("UPDATE usuari SET score='"+score+"' WHERE nickname='"+nickname+"'");
    	try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Modificar --> " + ex.getSQLState());
        }
    }
    
    public static void deleteUser(String nickname){
    	String query = new String("DELETE FROM usuari WHERE nickname='"+nickname+"'");
    	try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);
             
        } catch (SQLException ex) {
            System.out.println("Problema al Eliminar --> " + ex.getSQLState());
        }
    	
    }
   
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
    
    public void disconnect(){
    	try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Problema al tancar la connexi� --> " + e.getSQLState());
		}
    }

}
