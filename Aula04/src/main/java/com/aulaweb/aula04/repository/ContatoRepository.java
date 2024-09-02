/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aulaweb.aula04.repository;

import com.aulaweb.aula04.model.ContatoModel;
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
    
    private static final String INSERT_CONTATOS_SQL = "INSERT INTO aula04.contatos" + "  (nome, email) VALUES "
			+ " (?, ?);";

    private static final String SELECT_CONTATOS_BY_ID = "select con_id,con_nome,con_email from aula04.contatos where con_id =?";
    private static final String SELECT_ALL_CONTATOS = "select * from aula04.contatos";
    private static final String DELETE_CONTATOS_SQL = "delete from aula04.contatos where con_id = ?;";
    private static final String UPDATE_CONTATOS_SQL = "update aula04.contatos set con_nome = ?,con_email= ? where con_id = ?;";

    public ContatoRepository() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
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
	public void insert(ContatoModel contato) throws SQLException {
		System.out.println(INSERT_CONTATOS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTATOS_SQL)) {
			preparedStatement.setString(1, contato.getNome());
			preparedStatement.setString(2, contato.getEmail());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public ContatoModel select(int id) {
		ContatoModel contato = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTATOS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				contato = new ContatoModel(id, nome, email);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contato;
	}

	public List<ContatoModel> selectAll() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<ContatoModel> contatos = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONTATOS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("con_id");
				String nome = rs.getString("con_nome");
				String email = rs.getString("con_demail");
				contatos.add(new ContatoModel(id, nome, email));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contatos;
	}

	public boolean delete(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CONTATOS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean update(ContatoModel contato) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CONTATOS_SQL);) {
			statement.setString(1, contato.getNome());
			statement.setString(2, contato.getEmail());
			statement.setInt(4, contato.getId());

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
