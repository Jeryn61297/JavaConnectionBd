package com.jeska;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jeska.util.ConexionBaseDatos;

public class EjemploJdbc {
	
	public static void main(String[] args) {
		
		try (Connection conn = ConexionBaseDatos.getInstance(); 
			Statement stmt = conn.createStatement();
			ResultSet resultado = stmt.executeQuery("SELECT * FROM productos")) {
			
			while (resultado.next()) {
				System.out.print(resultado.getInt("id"));
				System.out.print(" | ");
				System.out.print(resultado.getString("nombre"));
				System.out.print(" | ");
				System.out.print("$");
				System.out.print(resultado.getInt("precio"));
				System.out.print(" | ");
				System.out.print(resultado.getDate("fecha_registro"));
				System.out.println(" ");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
