/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aulaweb.aula04.repository;

import com.aulaweb.aula04.model.Contato;
import com.aulaweb.aula04.model.Teste;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akio
 */
public class FormRepository {
    
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/aula04?useSSL=false";
    private static String user = "root";
    private static String password = "1234";
    
    private static final String INSERT_CONTATOS_SQL = "INSERT INTO aula04.teste(tes_nome, tes_email) VALUES(?, ?);";
    private static final String SELECT_CONTATOS_BY_ID = "select tes_id,tes_nome,tes_email from aula04.teste where tes_id =?";
    private static final String SELECT_ALL_CONTATOS = "select * from aula04.teste";
    private static final String DELETE_CONTATOS_SQL = "delete from aula04.teste where tes_id = ?;";
    private static final String UPDATE_CONTATOS_SQL = "update aula04.teste set tes_nome = ?,tes_email= ? where tes_id = ?;";

    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    public Boolean salvar(String nome, String email) throws SQLException {
        Boolean salvo = false;
        System.out.println(INSERT_CONTATOS_SQL);
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTATOS_SQL)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            salvo = true;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return salvo;
    }
    
    public List<Teste> selecionarTodos() {
        List<Teste> testes = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONTATOS);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                    int id = rs.getInt("tes_id");
                    String nome = rs.getString("tes_nome");
                    String email = rs.getString("tes_email");
                    testes.add(new Teste(id, nome, email));
            }
        } catch (SQLException e) {
                printSQLException(e);
        }
        return testes;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                }
            }
        }
    }
}
