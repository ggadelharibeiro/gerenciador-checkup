package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = "root", DB_PASS = "root";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbTeste";

    private static DBConnect dbConnect;

    public static DBConnect getInstance(){
        if(dbConnect == null){
            dbConnect = new DBConnect();
        }
        return dbConnect;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }


    public static void inserir(String nome) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnect.getInstance().getConnection();
        String sql = "INSERT INTO paciente(nome) VALUE(?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nome);
        statement.execute();

        connection.close();

    }

    public static void remover(int id) throws SQLException, ClassNotFoundException{

        Connection connection = DBConnect.getInstance().getConnection();
        String sql = "DELETE FROM paciente WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();

        connection.close();
    }

}
