/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aulaweb.aula04.repository;

import com.aulaweb.aula04.model.Contato;
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
public class ContatoRepository {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/aula04?useSSL=false";
    private static String user = "root";
    private static String password = "1234";
    
    private static final String INSERT_CONTATOS_SQL = "INSERT INTO aula04.contatos(con_nome, con_email) VALUES(?, ?);";
    private static final String SELECT_CONTATOS_BY_ID = "select con_id,con_nome,con_email from aula04.contatos where con_id =?";
    private static final String SELECT_ALL_CONTATOS = "select * from aula04.contatos";
    private static final String DELETE_CONTATOS_SQL = "delete from aula04.contatos where con_id = ?;";
    private static final String UPDATE_CONTATOS_SQL = "update aula04.contatos set con_nome = ?,con_email= ? where con_id = ?;";

    
    public ContatoRepository() {
	}

	protected Connection getConnection() {
            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
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
	public Boolean salvar(Contato contato) throws SQLException {
            Boolean salvo = false;
            System.out.println(INSERT_CONTATOS_SQL);
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTATOS_SQL)) {
                preparedStatement.setString(1, contato.getNome());
                preparedStatement.setString(2, contato.getEmail());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
                salvo = true;
            } catch (SQLException e) {
                printSQLException(e);
            }
            return salvo;
	}

	public Contato selecionarPorId(int id) {
            Contato contato = null;
            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTATOS_BY_ID);) {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    
                    String nome = rs.getString("con_nome");
                    String email = rs.getString("con_email");
                    contato = new Contato(id, nome, email);
                    System.out.println("nome: " + nome);
                }
            } catch (SQLException e) {
                    printSQLException(e);
            }
            return contato;
	}

	public List<Contato> selecionarTodos() {
            List<Contato> contatos = new ArrayList<>();
            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONTATOS);) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                        int id = rs.getInt("con_id");
                        String nome = rs.getString("con_nome");
                        String email = rs.getString("con_email");
                        contatos.add(new Contato(id, nome, email));
                }
            } catch (SQLException e) {
                    printSQLException(e);
            }
            return contatos;
	}

	public boolean deletar(int id) throws SQLException {
            boolean rowDeleted;
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(DELETE_CONTATOS_SQL);) {
                statement.setInt(1, id);
                rowDeleted = statement.executeUpdate() > 0;
            }
            return rowDeleted;
	}

	public boolean alterar(Contato contato) throws SQLException {
            boolean rowUpdated;
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(UPDATE_CONTATOS_SQL);) {
                statement.setString(1, contato.getNome());
                statement.setString(2, contato.getEmail());
                statement.setInt(3, contato.getId());
                rowUpdated = statement.executeUpdate() > 0;
            }
            return rowUpdated;
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
